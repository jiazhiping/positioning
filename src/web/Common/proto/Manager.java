package web.Common.proto;

import io.netty.channel.Channel;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/10/30.
 */
public class Manager {
    private static Manager inst;
    private HashMap <Channel, Dev> devs = new HashMap <Channel, Dev>();

    private Manager() {
    }

    public static Manager getInstance() {
        if (inst == null) {
            inst = new Manager();
        }
        return inst;
    }

    public HashMap <Channel, Dev> getDevs() {
        return devs;
    }
}
