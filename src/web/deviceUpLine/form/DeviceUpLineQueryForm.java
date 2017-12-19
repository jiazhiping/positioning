package web.deviceUpLine.form;

/**
 * 已上线设备查询Form
 * Created by Myk on 2017/11/17.
 */
public class DeviceUpLineQueryForm {
    private String locatorName; // 定位器序列号
    private String terminalName; // POS终端序列号

    public String getLocatorName() { return locatorName; }

    public void setLocatorName(String locatorName) { this.locatorName = locatorName; }

    public String getTerminalName() { return terminalName; }

    public void setTerminalName(String terminalName) { this.terminalName = terminalName; }
}
