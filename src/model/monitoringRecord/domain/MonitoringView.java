package model.monitoringRecord.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * 告警视图
 * Created by Myk on 2017/11/7.
 */
@Entity(name = "monitoringView")
@Table(name = "v_monitoring")
public class MonitoringView {
    private String monitoringId; // 告警Id  主键
    private String gpsId;//定位Id
    private String bindingId; // 绑定Id
    private String monitoringType; // 告警类型
    private int monitoringGrade; // 告警级别 提示：0 次要：1 主要：2 严重：3
    private String monitoringRemarks; // 告警备注
    private int monitoringStatus;//告警状态 活跃：0 已处理：1
    private Date CreateTime;// 创建时间
    private Date UpdateTime;// 修改时间

    private String locatorName;//定位器序列号
    private String terminalName;//终端序列号
    private String longitude; // 经度
    private String latitude; // 纬度
    private String velocity; // 移动速度
    private String course; // 移动距离
    private String altitude; // 海拔
    private Date utc; // 时间
    private String battery; // 电池

    @Id
    @Column(name = "monitoring_id")
    public String getMonitoringId() { return monitoringId; }

    @Column(name = "gps_id")
    public String getGpsId() { return gpsId; }

    @Column(name = "binding_id")
    public String getBindingId() { return bindingId; }

    @Column(name = "monitoring_type")
    public String getMonitoringType() { return monitoringType; }

    @Column(name = "monitoring_grade")
    public int getMonitoringGrade() { return monitoringGrade; }

    @Column(name = "monitoring_remarks")
    public String getMonitoringRemarks() { return monitoringRemarks; }

    @Column(name = "monitoring_status")
    public int getMonitoringStatus() { return monitoringStatus; }

    @Column(name = "create_time")
    public Date getCreateTime() {
        return CreateTime;
    }

    @Column(name = "update_time")
    public Date getUpdateTime() { return UpdateTime; }

    @Column(name = "gps_locator_name")
    public String getLocatorName() { return locatorName; }

    @Column(name = "gps_terminal_name")
    public String getTerminalName() { return terminalName; }

    @Column(name = "gps_longitude")
    public String getLongitude() { return longitude; }

    @Column(name = "gps_latitude")
    public String getLatitude() { return latitude; }

    @Column(name = "gps_velocity")
    public String getVelocity() { return velocity; }

    @Column(name = "gps_course")
    public String getCourse() { return course; }

    @Column(name = "gps_altitude")
    public String getAltitude() { return altitude; }

    @Column(name = "gps_utc")
    public Date getUtc() { return utc; }

    @Column(name = "gps_battery")
    public String getBattery() { return battery; }

    public void setMonitoringId(String monitoringId) { this.monitoringId = monitoringId; }

    public void setGpsId(String gpsId) { this.gpsId = gpsId; }

    public void setBindingId(String bindingId) { this.bindingId = bindingId; }

    public void setMonitoringType(String monitoringType) { this.monitoringType = monitoringType; }

    public void setMonitoringGrade(int monitoringGrade) { this.monitoringGrade = monitoringGrade; }

    public void setMonitoringRemarks(String monitoringRemarks) { this.monitoringRemarks = monitoringRemarks; }

    public void setMonitoringStatus(int monitoringStatus) { this.monitoringStatus = monitoringStatus; }

    public void setCreateTime(Date createTime) {CreateTime = createTime; }

    public void setUpdateTime(Date updateTime) {UpdateTime = updateTime; }

    public void setLocatorName(String locatorName) { this.locatorName = locatorName; }

    public void setTerminalName(String terminalName) { this.terminalName = terminalName; }

    public void setLongitude(String longitude) { this.longitude = longitude; }

    public void setLatitude(String latitude) { this.latitude = latitude; }

    public void setVelocity(String velocity) { this.velocity = velocity; }

    public void setCourse(String course) { this.course = course; }

    public void setAltitude(String altitude) { this.altitude = altitude; }

    public void setUtc(Date utc) { this.utc = utc; }

    public void setBattery(String battery) { this.battery = battery; }
}
