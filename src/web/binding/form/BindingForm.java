package web.binding.form;

/**
 * 关系实体类
 * Created by Myk on 2017/11/2.
 */
public class BindingForm {
    private String bindingId; // 绑定Id  主键
    private String locatorId; // 定位器Id
    private String terminalId; // 终端Id

    private String locatorName; // 定位器序列号
    private String terminalName; // 终端序列号

    private String bindingIds;//绑定Id组

    public String getBindingId() { return bindingId; }

    public void setBindingId(String bindingId) { this.bindingId = bindingId; }

    public String getLocatorId() { return locatorId; }

    public void setLocatorId(String locatorId) { this.locatorId = locatorId; }

    public String getTerminalId() { return terminalId; }

    public void setTerminalId(String terminalId) { this.terminalId = terminalId; }

    public String getLocatorName() { return locatorName; }

    public void setLocatorName(String locatorName) { this.locatorName = locatorName; }

    public String getTerminalName() { return terminalName; }

    public void setTerminalName(String terminalName) { this.terminalName = terminalName; }

    public String getBindingIds() { return bindingIds; }

    public void setBindingIds(String bindingIds) { this.bindingIds = bindingIds; }
}
