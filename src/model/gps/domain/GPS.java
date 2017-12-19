package model.gps.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * GPS表
 * Created by Myk on 2017/11/10.
 */
@Entity(name = "GPS")
@Table(name = "t_gps")
public class GPS {
    private String gpsId;
    private String locatorName;//定位器序列号
    private String terminalName;//终端序列号
    private String longitude; // 经度
    private String latitude; // 纬度
    private String velocity; // 移动速度
    private String course; // 移动距离
    private String altitude; // 海拔
    private Date utc; // 时间
    private String battery; // 电池
    private Date CreateTime;// 创建时间
    private Date UpdateTime;// 修改时间

    @Id
    @Column(name = "gps_id", length = 32, nullable = false)
    public String getGpsId() { return gpsId; }

    @Column(name = "gps_locator_name", nullable = false)
    public String getLocatorName() { return locatorName; }

    @Column(name = "gps_terminal_name", nullable = false)
    public String getTerminalName() { return terminalName; }

    @Column(name = "gps_longitude", nullable = false)
    public String getLongitude() { return longitude; }

    @Column(name = "gps_latitude", nullable = false)
    public String getLatitude() { return latitude; }

    @Column(name = "gps_velocity")
    public String getVelocity() { return velocity; }

    @Column(name = "gps_course")
    public String getCourse() { return course; }

    @Column(name = "gps_altitude")
    public String getAltitude() { return altitude; }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "gps_utc")
    public Date getUtc() { return utc; }

    @Column(name = "gps_battery")
    public String getBattery() { return battery; }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    public Date getCreateTime() { return CreateTime; }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    public Date getUpdateTime() { return UpdateTime; }

    public void setGpsId(String gpsId) { this.gpsId = gpsId; }

    public void setLocatorName(String locatorName) { this.locatorName = locatorName; }

    public void setTerminalName(String terminalName) { this.terminalName = terminalName; }

    public void setLongitude(String longitude) { this.longitude = longitude; }

    public void setLatitude(String latitude) { this.latitude = latitude; }

    public void setVelocity(String velocity) { this.velocity = velocity; }

    public void setCourse(String course) { this.course = course; }

    public void setAltitude(String altitude) { this.altitude = altitude; }

    public void setUtc(Date utc) { this.utc = utc; }

    public void setBattery(String battery) { this.battery = battery; }

    public void setCreateTime(Date createTime) {CreateTime = createTime; }

    public void setUpdateTime(Date updateTime) {UpdateTime = updateTime; }
}
