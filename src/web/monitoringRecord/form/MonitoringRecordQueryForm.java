package web.monitoringRecord.form;

/**
 * 告警记录查询实体类
 * Created by Myk on 2017/11/16.
 */
public class MonitoringRecordQueryForm {
    private String monitoringGradeName;//告警级别
    private String locatorName;//定位器序列号
    private String terminalName;//终端序列号

    public String getMonitoringGradeName() { return monitoringGradeName; }

    public void setMonitoringGradeName(String monitoringGradeName) { this.monitoringGradeName = monitoringGradeName; }

    public String getLocatorName() { return locatorName; }

    public void setLocatorName(String locatorName) { this.locatorName = locatorName; }

    public String getTerminalName() { return terminalName; }

    public void setTerminalName(String terminalName) { this.terminalName = terminalName; }
}
