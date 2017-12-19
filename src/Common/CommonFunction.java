package Common;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 通用的方法类（此类中方法都编写成静态方法）
 * Created by Myk on 2017/10/31.
 */
public class CommonFunction {
    private static double EARTH_RADIUS = 6378.137;//地球旋转轴

    /**
     * 判断内容是否为空（为空：true 不为空：false）
     *
     * @param content 内容
     * @return
     */
    public static boolean VerdictNULL(String content) {
        if (content == null || content.trim().isEmpty() || "null".equals(content)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断内容是否为空（为空：true 不为空：false）
     *
     * @param content 内容
     * @return
     */
    public static boolean VerdictNULL(Object content) {
        if (content == null || "".equals(content)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 将 null 转化为 ""
     *
     * @param content 内容
     * @return
     */
    public static String ConversionNUll(Object content) {
        if (content == null || content == "") {
            return "";
        } else {
            return content.toString();
        }
    }

    /**
     * 删除起始与结尾空格（若为null返回""）
     *
     * @param content 内容
     * @return
     */
    public static String DeleteNull(String content) {
        if (content == null) {
            return "";
        }
        return content.trim();
    }

    /**
     * 获取指定格式的当前时间字符串
     *
     * @param format 时间格式
     * @return 返回指定格式的当前时间字符串
     */
    public static String getCurrentTimestampStr(String format) {
        if (format == null) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        // 获取当前时间
        Timestamp currentDate = new Timestamp(System.currentTimeMillis());
        // 获取格式化日期类
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        // 获取当前时间的字符串格式
        String dateStr = sdf.format(currentDate);
        return dateStr;
    }

    /**
     * 将毫秒数转化为Date
     *
     * @param time
     * @return
     */
    public static Date getDateByTime(Long time) {
        Date date = new Date();
        date.setTime(time);
        return date;
    }

    /**
     * SHA256加密
     *
     * @param content 内容
     * @return
     */
    public static String SHA256(String content) {
        String s = Encrypt(content, "SHA-256");
        return s;
    }

    private static String Encrypt(String strSrc, String encName) {
        MessageDigest md;
        String strDes;
        byte[] bt = strSrc.getBytes();
        try {
            md = MessageDigest.getInstance(encName);
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            System.out.println("签名失败！");
            return null;
        }
        return strDes;
    }

    private static String bytes2Hex(byte[] bts) {
        StringBuilder des = new StringBuilder();
        String tmp;
        for (byte bt : bts) {
            tmp = (Integer.toHexString(bt & 0xFF));
            if (tmp.length() == 1) {
                des.append("0");
            }
            des.append(tmp);
        }
        return des.toString();
    }

    /**
     * 将字符串转化为集合 （以“,”分割）
     *
     * @param str 内容
     * @return
     */
    public static List <String> convertStrGather(String str) {
        List <String> strGather = Arrays.asList(str.split(","));
        return strGather;
    }

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 通过经纬度获取距离(单位：米)
     *
     * @param Longitude0  经度0
     * @param Latitude0   纬度0
     * @param Longitude01 经度1
     * @param Latitude1   纬度1
     * @return
     */
    public static String getDistance(String Longitude0, String Latitude0, String Longitude01, String Latitude1) {
        double radLat1 = rad(Double.parseDouble(Latitude0));
        double radLat2 = rad(Double.parseDouble(Latitude1));
        double a = radLat1 - radLat2;
        double b = rad(Double.parseDouble(Longitude0)) - rad(Double.parseDouble(Longitude01));
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        s = s * 1000;
        System.out.println(s);
        return String.valueOf(formatDouble(s, 1));
    }

    /**
     * @param s
     * @param dotLength 最大值为3，最小值为0
     * @return
     * @Description:格式化小数点后位数（最多到小数点后三位）
     * @Author：马远志
     * @CreateDate：2015-06-22 22:00:21
     * @Update:
     * @UpdateDate:
     * @UpdateDescription:
     */
    public static String formatDouble(double s, int dotLength) {
        DecimalFormat fmt = null;
        if (dotLength == 0) {
            fmt = new DecimalFormat("##0");
        } else if (dotLength == 1) {
            fmt = new DecimalFormat("##0.0");
        } else if (dotLength == 2) {
            fmt = new DecimalFormat("##0.00");
        } else {
            fmt = new DecimalFormat("##0.000");
        }
        return fmt.format(s);
    }

    /**
     * 获取登录用户IP地址
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.equals("0:0:0:0:0:0:0:1")) {
            ip = "本地";
        }
        return ip;
    }

    /**
     * 判断A是否比b大（String转换double）
     *
     * @param big    大值
     * @param little 小值
     * @return
     */
    public static boolean Compares(String big, String little) {
        double a = Double.valueOf(big).doubleValue();
        double b = Double.valueOf(little).doubleValue();
        if (a > b) {
            return true;
        } else {
            return false;
        }
    }


    public static void main(String[] args) {
        System.out.println(SHA256("123456"));
    }
}
