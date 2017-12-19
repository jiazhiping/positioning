package web.monitoring.form;

/**
 * 实时监控Form
 * Created by Myk on 2017/11/7.
 */
public class MonitoringForm {
    private String deviceId; // 设备Id
    private String deviceFrequency; // 监控器上报频率
    private String monitoringId; // 告警Id  主键

    private String locatorName; // 定位器序列号


    public String getDeviceId() { return deviceId; }

    public void setDeviceId(String deviceId) { this.deviceId = deviceId; }

    public String getDeviceFrequency() { return deviceFrequency; }

    public void setDeviceFrequency(String deviceFrequency) { this.deviceFrequency = deviceFrequency; }

    public String getMonitoringId() { return monitoringId; }

    public void setMonitoringId(String monitoringId) { this.monitoringId = monitoringId; }

    public String getLocatorName() { return locatorName; }

    public void setLocatorName(String locatorName) { this.locatorName = locatorName; }
}
