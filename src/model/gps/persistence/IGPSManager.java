package model.gps.persistence;

import model.gps.domain.GPS;

import java.util.List;

/**
 * GPS持久化层接口
 */
public interface IGPSManager {

    /**
     * 保存GPS信息
     *
     * @param gps
     */
    String saveGPS(GPS gps);

    /**
     * 根据Id查询GPS表
     *
     * @param gpsId gpsId
     * @return
     */
    GPS getGPS(String gpsId);

    /**
     * 根据Name查询该定位器是否已上线
     *
     * @param locatorName 定位器序列号
     * @return
     */
    List <GPS> getGPSName(String locatorName);

    /**
     * 修改Gps信息
     *
     * @param gps
     * @return
     */
    boolean updateGps(GPS gps);
}
