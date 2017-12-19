package web.Common.utils;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Administrator on 2017/10/30.
 */
public class PossUtil {
    /**
     * 随机生成aeskey
     *
     * @return
     */
    public static String getAesKey() {
        String aesKey = UUID.randomUUID().toString().replaceAll("-", "")
                .substring(0, 16);
        return aesKey;
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static Integer getCurrentTime() {
        Long currentTimeMillis = new Date().getTime() / 1000;
        System.out.println("当前系统时间为：" + currentTimeMillis.intValue());
        return currentTimeMillis.intValue();
    }

    /**
     * 32位随机id
     *
     * @return
     */
    public static String getCode() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }

    public static void main(String[] args) {
        System.err.println(getCurrentTime().longValue() * 1000);
        System.out.println(new Date(getCurrentTime().longValue() * 1000));
    }
}
