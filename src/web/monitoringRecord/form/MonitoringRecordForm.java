package web.monitoringRecord.form;

/**
 * 告警记录实体类
 * Created by Myk on 2017/11/9.
 */
public class MonitoringRecordForm {
    private String monitoringId; // 告警Id
    private String locatorName; // 定位器序列号

    private String monitoringIds;// 告警Id组


    public String getMonitoringIds() { return monitoringIds; }

    public void setMonitoringIds(String monitoringIds) { this.monitoringIds = monitoringIds; }

    public String getLocatorName() { return locatorName; }

    public void setLocatorName(String locatorName) { this.locatorName = locatorName; }

    public String getMonitoringId() { return monitoringId; }

    public void setMonitoringId(String monitoringId) { this.monitoringId = monitoringId; }
}
