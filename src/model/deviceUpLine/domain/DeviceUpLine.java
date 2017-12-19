package model.deviceUpLine.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * 已上线设备表
 * Created by Myk on 2017/11/8.
 */
@Entity(name = "DeviceUpLine")
@Table(name = "t_deviceupline")
public class DeviceUpLine {
    private String deviceId; // 设备Id  主键
    private String locatorName; // 定位器序列号
    private String terminalName; // POS终端序列号
    private String devicePositionLongitude; // 预设位置:经度
    private String devicePositionLatitude; // 预设位置:纬度
    private Date deviceTime;// 上报时间
    private String deviceCurrentPositionLongitude; // 当前位置:经度
    private String deviceCurrentPositionLatitude; // 当前位置:纬度
    private String deviceDistance; // 偏移距离
    private String deviceFrequency; // 监控器上报频率
    private String deviceBattery;//定位器电量
    private int deviceAlarm;//告警状态 无告警：0 有告警：1
    private Date CreateTime;// 创建时间
    private Date UpdateTime;// 修改时间

    @Id
    @Column(name = "device_id", length = 24, nullable = false)
    public String getDeviceId() { return deviceId; }

    public void setDeviceId(String deviceId) { this.deviceId = deviceId; }

    @Column(name = "locator_name", length = 36)
    public String getLocatorName() { return locatorName; }

    public void setLocatorName(String locatorName) { this.locatorName = locatorName; }

    @Column(name = "terminal_name", length = 36)
    public String getTerminalName() { return terminalName; }

    public void setTerminalName(String terminalName) { this.terminalName = terminalName; }

    @Column(name = "device_position_longitude", length = 24)
    public String getDevicePositionLongitude() { return devicePositionLongitude; }

    public void setDevicePositionLongitude(String devicePositionLongitude) { this.devicePositionLongitude = devicePositionLongitude; }

    @Column(name = "device_position_latitude", length = 24)
    public String getDevicePositionLatitude() { return devicePositionLatitude; }

    public void setDevicePositionLatitude(String devicePositionLatitude) { this.devicePositionLatitude = devicePositionLatitude; }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "device_time")
    public Date getDeviceTime() { return deviceTime; }

    public void setDeviceTime(Date deviceTime) { this.deviceTime = deviceTime; }

    @Column(name = "device_current_position_longitude", length = 24)
    public String getDeviceCurrentPositionLongitude() { return deviceCurrentPositionLongitude; }

    public void setDeviceCurrentPositionLongitude(String deviceCurrentPositionLongitude) { this.deviceCurrentPositionLongitude = deviceCurrentPositionLongitude; }

    @Column(name = "device_current_position_latitude", length = 24)
    public String getDeviceCurrentPositionLatitude() { return deviceCurrentPositionLatitude; }

    public void setDeviceCurrentPositionLatitude(String deviceCurrentPositionLatitude) { this.deviceCurrentPositionLatitude = deviceCurrentPositionLatitude; }

    @Column(name = "device_distance", length = 24)
    public String getDeviceDistance() { return deviceDistance; }

    public void setDeviceDistance(String deviceDistance) { this.deviceDistance = deviceDistance; }

    @Column(name = "device_frequency", length = 24)
    public String getDeviceFrequency() { return deviceFrequency; }

    public void setDeviceFrequency(String deviceFrequency) { this.deviceFrequency = deviceFrequency; }

    @Column(name = "device_battery", length = 24)
    public String getDeviceBattery() { return deviceBattery; }

    public void setDeviceBattery(String deviceBattery) { this.deviceBattery = deviceBattery; }

    @Column(name = "device_alarm", length = 24)
    public int getDeviceAlarm() { return deviceAlarm; }

    public void setDeviceAlarm(int deviceAlarm) { this.deviceAlarm = deviceAlarm; }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {CreateTime = createTime; }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    public Date getUpdateTime() { return UpdateTime; }

    public void setUpdateTime(Date updateTime) {UpdateTime = updateTime; }
}
