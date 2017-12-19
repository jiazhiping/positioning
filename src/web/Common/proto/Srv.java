package web.Common.proto;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Created by Administrator on 2017/10/19.
 */
public class Srv extends Thread{
	static private final int SRV_PORT = 18092;
	static private final int BLOCK_SIZE = 128;
	
	public Srv() {

	}

	public void run() {
		// EventLoopGroup是用来处理IO操作的多线程事件循环器
		// bossGroup 用来接收进来的连接
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		// workerGroup 用来处理已经被接收的连接
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			// 启动 NIO 服务的辅助启动类
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.option(ChannelOption.SO_BACKLOG, BLOCK_SIZE)
					.handler(new LoggingHandler(LogLevel.INFO))
					.childHandler(new Initializer())
					.childOption(ChannelOption.SO_KEEPALIVE, true);
			// 绑定端口，开始接收进来的连接
			ChannelFuture f = b.bind(SRV_PORT).sync();
			// 等待服务器 socket 关闭 。
			f.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}

	private class Initializer extends ChannelInitializer<SocketChannel> {
		@Override
		protected void initChannel(SocketChannel ch) throws Exception {
			ch.pipeline().addLast("encode", new ProtobufEncoder());
			ch.pipeline().addLast("decoder",
					new ProtobufDecoder(Protocol.Packet.getDefaultInstance()));
			ch.pipeline().addLast(new Handler());
		}
	}

}
