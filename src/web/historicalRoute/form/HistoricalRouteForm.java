package web.historicalRoute.form;

/**
 * 历史轨迹实体类
 * Created by Myk on 2017/11/10.
 */
public class HistoricalRouteForm {
    private String locatorName; // 定位器序列号
    private String devicePositionLongitude; // 预设位置:经度
    private String devicePositionLatitude; // 预设位置:纬度
    private String historicalRouteId;//历史定位Id

    public String getLocatorName() { return locatorName; }

    public void setLocatorName(String locatorName) { this.locatorName = locatorName; }

    public String getDevicePositionLongitude() { return devicePositionLongitude; }

    public void setDevicePositionLongitude(String devicePositionLongitude) { this.devicePositionLongitude = devicePositionLongitude; }

    public String getDevicePositionLatitude() { return devicePositionLatitude; }

    public void setDevicePositionLatitude(String devicePositionLatitude) { this.devicePositionLatitude = devicePositionLatitude; }

    public String getHistoricalRouteId() { return historicalRouteId; }

    public void setHistoricalRouteId(String historicalRouteId) { this.historicalRouteId = historicalRouteId; }
}
