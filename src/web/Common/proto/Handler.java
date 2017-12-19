package web.Common.proto;


import Common.CommonFunction;
import com.google.protobuf.ByteString;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import model.gps.business.IGPSService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import web.Common.utils.MyApplicationContextUtil;
import web.Common.utils.PossUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.zip.CRC32;

public class Handler extends SimpleChannelInboundHandler <Protocol.Packet> {

    private static Logger logger = Logger.getLogger(Handler.class);
    private static CRC32 crc32 = new CRC32();
    private static IGPSService gpsService = null;
    private static String snId = null;
    private static String reqDev = null;

    static {
        gpsService = (IGPSService) MyApplicationContextUtil.getContext().getBean("gpsService");
    }

    @Autowired
    private HttpServletRequest request;

    /**
     * 封装gps返回数据
     *
     * @return
     * @throws Exception
     */
    private static Protocol.Packet respGPSData(Protocol.Scoped scoped, Dev dev) throws Exception {
    	logger.info("接收到socped ======================== >" + scoped.toString());
        Protocol.Scoped.Descriptor descriptor = scoped.getDescribed();
        //创建ack包
        Protocol.Scoped.PlatformAck.Builder ackBuilder = Protocol.Scoped.PlatformAck.newBuilder();
        ackBuilder.setCode(0);
        Protocol.Scoped.PlatformAck ack = ackBuilder.build();
        //创建返回Descriptor包
        Protocol.Scoped.Descriptor.Builder descBuilder = Protocol.Scoped.Descriptor.newBuilder();
        descBuilder.setVersion(0x100);
        descBuilder.setKind(Protocol.PacketKind.kPositionKind);
        descBuilder.setOp(Protocol.OpKind.kAckKind);
        descBuilder.setFrame(descriptor.getFrame());
        descBuilder.setSeq(descriptor.getSeq() + 1);
        logger.info("返回builder =============================== >" + descBuilder.toString());
        //创建返回scoped包
        Protocol.Scoped.Builder scopedbBuilder = Protocol.Scoped.newBuilder();
        scopedbBuilder.setAck(ackBuilder);
        scopedbBuilder.setDescribed(descBuilder);
        Protocol.Scoped resScoped = scopedbBuilder.build();
        logger.info("返回resScoped =============================== >" + resScoped.toString());
        //加密
        byte[] scopedByte = dev.getAes().encrypt(resScoped.toByteArray());
        //创建返回header包
        Protocol.Header.Builder headerBuilder = Protocol.Header.newBuilder();
        headerBuilder.setLength(scopedByte.length);
        headerBuilder.setEncrypto(Protocol.Decrypt.kDecryptAes128_CBC_PKCS7);
        crc32.reset();
        crc32.update(scopedByte);
        headerBuilder.setCrc32((int) crc32.getValue());
        Protocol.Header header2 = headerBuilder.build();
        //创建返回Packet包
        Protocol.Packet.Builder packBuilder = Protocol.Packet.newBuilder();
        packBuilder.setHeader(headerBuilder);
        packBuilder.setData(ByteString.copyFrom(scopedByte));
        return packBuilder.build();
    }

    /**
     * 封装返回Auth数据
     *
     * @param scoped
     * @param dev
     * @return
     * @throws Exception
     */
    private static Protocol.Packet respAuthData(Protocol.Scoped scoped, Dev dev) throws Exception {
        Protocol.Scoped.Descriptor descriptor = scoped.getDescribed();
        byte[] new_aes_key;
        Protocol.AuthReq req = Protocol.AuthReq.parseFrom(scoped.getData());
        dev.setAuth(true);
        System.out.println("Dev sn:" + req.getSn() + ",dev:" + req.getDev() + ", auth" + ",utc:" + PossUtil.getCurrentTime());
        snId = req.getSn();
        reqDev = req.getDev();
        //创建返回authrsp包
        Protocol.AuthRsp.Builder authRspBuilder = Protocol.AuthRsp.newBuilder();
        new_aes_key = PossUtil.getAesKey().getBytes();
        byte[] encr_key = dev.getAes().encrypt(ByteString.copyFrom(new_aes_key).toByteArray());
        authRspBuilder.setAesKey(ByteString.copyFrom(encr_key));
        authRspBuilder.setUtc(PossUtil.getCurrentTime());
        Protocol.AuthRsp authRsp = authRspBuilder.build();
        //创建ack包
        Protocol.Scoped.PlatformAck.Builder ackBuilder = Protocol.Scoped.PlatformAck.newBuilder();
        ackBuilder.setCode(0);
        Protocol.Scoped.PlatformAck ack = ackBuilder.build();
        //创建返回Descriptor包
        Protocol.Scoped.Descriptor.Builder descBuilder = Protocol.Scoped.Descriptor.newBuilder();
        descBuilder.setVersion(0x100);
        descBuilder.setKind(Protocol.PacketKind.kAuthKind);
        descBuilder.setOp(Protocol.OpKind.kAckKind);
        descBuilder.setFrame(descriptor.getFrame());
        descBuilder.setSeq(descriptor.getSeq() + 1);
        //创建返回scoped包
        Protocol.Scoped.Builder scopedbBuilder = Protocol.Scoped.newBuilder();
        scopedbBuilder.setAck(ackBuilder);
        scopedbBuilder.setDescribed(descBuilder);
        scopedbBuilder.setData(authRsp.toByteString());
        Protocol.Scoped resScoped = scopedbBuilder.build();
        //加密
        byte[] scopedByte = dev.getAes().encrypt(resScoped.toByteArray());
        //创建返回header包
        Protocol.Header.Builder headerBuilder = Protocol.Header.newBuilder();
        headerBuilder.setLength(scopedByte.length);
        headerBuilder.setEncrypto(Protocol.Decrypt.kDecryptAes128_CBC_PKCS7);
        crc32.reset();
        crc32.update(scopedByte);
        headerBuilder.setCrc32((int) crc32.getValue());
        Protocol.Header header2 = headerBuilder.build();
        dev.setAes_key(new_aes_key);
        //创建返回Packet包
        Protocol.Packet.Builder packBuilder = Protocol.Packet.newBuilder();
        packBuilder.setHeader(headerBuilder);
        packBuilder.setData(ByteString.copyFrom(scopedByte));
        return packBuilder.build();
    }

    /**
     * 封装返回的init
     *
     * @param scoped
     * @param dev
     * @return
     * @throws Exception
     */
    private static Protocol.Packet respInitData(Protocol.Scoped scoped, Dev dev) throws Exception {
        Protocol.Scoped.Descriptor descriptor = scoped.getDescribed();
        Protocol.InitReq req = Protocol.InitReq.parseFrom(scoped.getData());
        System.out.println("Dev period:" + req.getReportPeriod() + ", battery:" + req.getBattery());
        ByteString byteString = req.getChallenge();
        crc32.reset();
        crc32.update(byteString.toByteArray());
        Protocol.InitRsp.Builder initBuilder = Protocol.InitRsp.newBuilder();
        initBuilder.setChallengeAnswer((int) crc32.getValue());
        initBuilder.setUpdateReportPeriod(60 * 3);
        logger.info("返回init=============================================>" + initBuilder.toString());
        //创建ack包
        Protocol.Scoped.PlatformAck.Builder ackBuilder = Protocol.Scoped.PlatformAck.newBuilder();
        ackBuilder.setCode(0);
        logger.info("返回ackBuilder=============================================>" + ackBuilder.toString());
        Protocol.Scoped.PlatformAck ack = ackBuilder.build();
        //创建返回Descriptor包
        Protocol.Scoped.Descriptor.Builder descBuilder = Protocol.Scoped.Descriptor.newBuilder();
        descBuilder.setVersion(0x100);
        descBuilder.setKind(Protocol.PacketKind.kInitKind);
        descBuilder.setOp(Protocol.OpKind.kAckKind);
        descBuilder.setFrame(descriptor.getFrame());
        descBuilder.setSeq(descriptor.getSeq() + 1);
        logger.info("返回descBuilder=============================================>" + descBuilder.toString());
        //创建返回scoped包
        Protocol.Scoped.Builder scopedbBuilder = Protocol.Scoped.newBuilder();
        scopedbBuilder.setAck(ackBuilder);
        scopedbBuilder.setDescribed(descBuilder);
        scopedbBuilder.setData(initBuilder.build().toByteString());
        Protocol.Scoped resScoped = scopedbBuilder.build();
        logger.info("返回resScoped=============================================>" + resScoped.toString());
        //加密
        byte[] scopedByte = dev.getAes().encrypt(resScoped.toByteArray());
        //创建返回header包
        Protocol.Header.Builder headerBuilder = Protocol.Header.newBuilder();
        headerBuilder.setLength(scopedByte.length);
        headerBuilder.setEncrypto(Protocol.Decrypt.kDecryptAes128_CBC_PKCS7);
        crc32.reset();
        crc32.update(scopedByte);
        headerBuilder.setCrc32((int) crc32.getValue());
        logger.info("返回headerBuilder=============================================>" + headerBuilder.toString());
        Protocol.Header header2 = headerBuilder.build();
        //创建返回Packet包
        Protocol.Packet.Builder packBuilder = Protocol.Packet.newBuilder();
        packBuilder.setHeader(headerBuilder);
        packBuilder.setData(ByteString.copyFrom(scopedByte));
        logger.info("返回packBuilder=============================================>" + packBuilder.build().toString());
        return packBuilder.build();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Protocol.Packet packet) throws Exception {
        System.out.println("packet:" + packet.toString() + ":channel:" + ctx.channel().toString());
        HashMap <Channel, Dev> devs = Manager.getInstance().getDevs();
        Dev dev;
        synchronized (devs) {
            dev = devs.get(ctx.channel());
            if (dev == null) {
                dev = new Dev(ctx.channel());
                devs.put(ctx.channel(), dev);
            }
        }
        logger.info("dev =========================================== >" + dev.toString());
        if (!CommonFunction.VerdictNULL(packet)) {
            byte[] bytes = packet.getData().toByteArray();
            Protocol.Header header = packet.getHeader();
            logger.info("接收 header =========================================== >" + header.toString());
            crc32.update(bytes);
            int crc = (int) crc32.getValue();
            if (crc != header.getCrc32()) {
                System.out.println("crc32 invalid:" + header.getCrc32() + "," + crc32);
            }
            if (header.getEncrypto() == Protocol.Decrypt.kDecryptAes128_CBC_PKCS7) {
                //aes descropty and assign to bytes
                dev.getAes().setKey(dev.getAes_key());
                dev.getAes().setIv(dev.getAes_key());
                bytes = dev.getAes().decrypt(bytes);
            }
            Protocol.Scoped scoped = Protocol.Scoped.parseFrom(bytes);
            logger.info("接收 scoped =========================================== >" + scoped.toString());
            Protocol.Scoped.Descriptor descriptor = scoped.getDescribed();
            if (descriptor.hasOp()) {
                if (descriptor.getOp() == Protocol.OpKind.kReqKind) {
                    //Req
                    Protocol.Scoped.PlatformReq req = scoped.getReq();
                } else {
                    //Ack
                    Protocol.Scoped.PlatformAck ack = scoped.getAck();
                    System.out.println("ack code:=" + ack.getCode() + ",msg:=" + ack.getMsg());
                }
            }
            System.err.println(descriptor.getKind().getNumber());
            switch (descriptor.getKind().getNumber()) {
                case Protocol.PacketKind.kAuthKind_VALUE: {
                    if (dev.isAuth()) {
                        System.out.println("dev reauth");
                        return;
                    }
                    logger.info("dev ===================================== >" + dev.toString());
                    Protocol.Packet respPacket = respAuthData(scoped, dev);
                    logger.info("respPacket ===================================== >" + respPacket.toString());
                    ctx.writeAndFlush(respPacket);
                }
                break;
                case Protocol.PacketKind.kInitKind_VALUE: {
                    if (dev.isInit()) {
                    	logger.info("dev ===================================== >" + dev.toString());
                        System.out.println("dev reinit");
                        return;
                    }
                    dev.setInit(true);
                    Protocol.Packet respPacket = respInitData(scoped, dev);
                    logger.info("respPacket ===================================== >" + respPacket.toString());
                    ctx.writeAndFlush(respPacket);
                }
                break;
                case Protocol.PacketKind.kHeartbeatKind_VALUE:

                    break;
                case Protocol.PacketKind.kPositionKind_VALUE:
                    logger.info("成功接收定位数据 =================================== >");
                    gpsService.getGPS(snId, reqDev, scoped);
                    snId = null;
                    reqDev = null;
                    Protocol.Packet respPacket = respGPSData(scoped, dev);
                    ctx.writeAndFlush(respPacket);
                    break;
                case Protocol.PacketKind.kReportKind_VALUE:
                    break;
                case Protocol.PacketKind.kAnyKind_VALUE:
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        HashMap <Channel, Dev> devs = Manager.getInstance().getDevs();
        synchronized (devs) {
            devs.remove(ctx.channel());
        }
        logger.info("socket 关闭 ··············································");
        ctx.close();
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
}
