package model.gps.business;

import model.gps.domain.GPS;
import web.Common.proto.Protocol;

public interface IGPSService {
    /**
     * GPS记录
     *
     * @param snId
     * @param reqDev
     * @param scoped
     */
    void getGPS(String snId, String reqDev, Protocol.Scoped scoped);

    /**
     * 根据序列号查询
     *
     * @param locatorName 定位器序列号
     * @return
     */
    GPS getGPSName(String locatorName);

    /**
     * 根据Id查询GPS记录
     *
     * @param gpsId GPSId
     * @return
     */
    GPS getGPS(String gpsId);
}
