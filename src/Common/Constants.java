package Common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * 常量
 * Created by Myk on 2017/10/31.
 */
public class Constants {
    public static final String STATUS_SUCCESS = "SUCCESS";

    //日期:年月日
    public static DateFormat date_ymd = new SimpleDateFormat("yyyyMMdd");
    // 日期：年-月-日 时:分:秒
    public static DateFormat date_ymdhms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");




}
