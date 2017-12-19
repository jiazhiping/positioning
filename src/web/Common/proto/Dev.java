package web.Common.proto;

import io.netty.channel.Channel;
import web.Common.utils.Aes;

/**
 * Created by Administrator on 2017/10/30.
 */
public class Dev {
    private Channel channel;
    private byte[] aes_key = {97, 99, 97, 100, 101, 109, 121, 111, 115, 99, 105, 101, 110, 99, 101, 115};
    private Aes aes;
    private boolean isAuth, isInit;

    public Dev(Channel channel) {
        this.channel = channel;
        aes = new Aes();
    }

    public byte[] getAes_key() {
        return aes_key;
    }

    public void setAes_key(byte[] aes_key) {
        this.aes_key = aes_key;
    }

    public Aes getAes() {
        return aes;
    }

    public boolean isAuth() {
        return isAuth;
    }

    public void setAuth(boolean isAuth) {
        this.isAuth = isAuth;
    }

    public boolean isInit() {
        return isInit;
    }

    public void setInit(boolean isInit) {
        this.isInit = isInit;
    }
}
