package model.historicalRoute.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * 历史定位信息表
 * Created by Myk on 2017/11/10.
 */
@Entity(name = "HistoricalRoute")
@Table(name = "t_historicalroute")
public class HistoricalRoute {
    private String historicalRouteId;//历史定位Id
    private String gpsId;//GpsId
    private String deviceId; // 设备Id
    private String distance;//该定位偏移距离
    private Date CreateTime;// 创建时间
    private Date UpdateTime;// 修改时间

    @Id
    @Column(name = "historical_id", length = 32, nullable = false)
    public String getHistoricalRouteId() { return historicalRouteId; }

    public void setHistoricalRouteId(String historicalRouteId) { this.historicalRouteId = historicalRouteId; }

    @Column(name = "gps_id", length = 32)
    public String getGpsId() { return gpsId; }

    public void setGpsId(String gpsId) { this.gpsId = gpsId; }

    @Column(name = "device_id", length = 24)
    public String getDeviceId() { return deviceId; }

    public void setDeviceId(String deviceId) { this.deviceId = deviceId; }

    @Column(name = "historical_distance", length = 24)
    public String getDistance() { return distance; }

    public void setDistance(String distance) { this.distance = distance; }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    public Date getCreateTime() { return CreateTime; }

    public void setCreateTime(Date createTime) {CreateTime = createTime; }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    public Date getUpdateTime() { return UpdateTime; }

    public void setUpdateTime(Date updateTime) {UpdateTime = updateTime; }
}
