package model.historicalRoute.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * 历史定位信息视图
 * Created by Myk on 2017/11/10.
 */
@Entity(name = "historicalRouteView")
@Table(name = "v_historicalroute")
public class HistoricalRouteView {
    private String historicalRouteId;//历史定位Id
    private String distance;//该定位偏移距离

    private String deviceId; // 设备Id  主键
    private String locatorName; // 定位器序列号
    private String terminalName; // POS终端序列号
    private String devicePositionLongitude; // 预设位置:经度
    private String devicePositionLatitude; // 预设位置:纬度

    private String gpsId;
    private String longitude; // 经度
    private String latitude; // 纬度

    private Date CreateTime;// 创建时间
    private Date UpdateTime;// 修改时间

    @Id
    @Column(name = "historical_id")
    public String getHistoricalRouteId() { return historicalRouteId; }

    @Column(name = "historical_distance")
    public String getDistance() { return distance; }

    @Column(name = "device_id")
    public String getDeviceId() { return deviceId; }

    @Column(name = "locator_name")
    public String getLocatorName() { return locatorName; }

    @Column(name = "terminal_name")
    public String getTerminalName() { return terminalName; }

    @Column(name = "device_position_longitude")
    public String getDevicePositionLongitude() { return devicePositionLongitude; }

    @Column(name = "device_position_latitude")
    public String getDevicePositionLatitude() { return devicePositionLatitude; }

    @Column(name = "gps_id")
    public String getGpsId() { return gpsId; }

    @Column(name = "gps_longitude")
    public String getLongitude() { return longitude; }

    @Column(name = "gps_latitude")
    public String getLatitude() { return latitude; }

    @Column(name = "create_time")
    public Date getCreateTime() { return CreateTime; }

    @Column(name = "update_time")
    public Date getUpdateTime() { return UpdateTime; }

    public void setHistoricalRouteId(String historicalRouteId) { this.historicalRouteId = historicalRouteId; }

    public void setDistance(String distance) { this.distance = distance; }

    public void setDeviceId(String deviceId) { this.deviceId = deviceId; }

    public void setLocatorName(String locatorName) { this.locatorName = locatorName; }

    public void setTerminalName(String terminalName) { this.terminalName = terminalName; }

    public void setDevicePositionLongitude(String devicePositionLongitude) { this.devicePositionLongitude = devicePositionLongitude; }

    public void setDevicePositionLatitude(String devicePositionLatitude) { this.devicePositionLatitude = devicePositionLatitude; }

    public void setGpsId(String gpsId) { this.gpsId = gpsId; }

    public void setLongitude(String longitude) { this.longitude = longitude; }

    public void setLatitude(String latitude) { this.latitude = latitude; }

    public void setCreateTime(Date createTime) {CreateTime = createTime; }

    public void setUpdateTime(Date updateTime) {UpdateTime = updateTime; }
}
