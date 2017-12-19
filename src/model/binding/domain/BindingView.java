package model.binding.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 绑定视图
 * Created by Myk on 2017/11/2.
 */
@Entity(name = "BindingView")
@Table(name = "v_binding")
public class BindingView {
    private String bindingId; // 绑定Id  主键
    private String locatorId; // 定位器Id
    private String terminalId; // 终端Id
    private String CreateCode;// 创建人员
    private Date CreateTime;// 创建时间
    private String UpdateCode;// 修改人员
    private Date UpdateTime;// 修改时间

    private String locatorName; // 定位器序列号
    private String terminalName; // 终端序列号

    private String deviceId; // 设备Id

    @Id
    @Column(name = "binding_id")
    public String getBindingId() { return bindingId; }

    public void setBindingId(String bindingId) { this.bindingId = bindingId; }

    @Column(name = "locator_id")
    public String getLocatorId() { return locatorId; }

    public void setLocatorId(String locatorId) { this.locatorId = locatorId; }

    @Column(name = "terminal_id")
    public String getTerminalId() { return terminalId; }

    public void setTerminalId(String terminalId) { this.terminalId = terminalId; }

    @Column(name = "create_code")
    public String getCreateCode() { return CreateCode; }

    public void setCreateCode(String createCode) {CreateCode = createCode; }

    @Column(name = "create_time")
    public Date getCreateTime() { return CreateTime; }

    public void setCreateTime(Date createTime) {CreateTime = createTime; }

    @Column(name = "update_code")
    public String getUpdateCode() { return UpdateCode; }

    public void setUpdateCode(String updateCode) {UpdateCode = updateCode; }

    @Column(name = "update_time")
    public Date getUpdateTime() { return UpdateTime; }

    public void setUpdateTime(Date updateTime) {UpdateTime = updateTime; }

    @Column(name = "locator_name")
    public String getLocatorName() { return locatorName; }

    public void setLocatorName(String locatorName) { this.locatorName = locatorName; }

    @Column(name = "terminal_name")
    public String getTerminalName() { return terminalName; }

    public void setTerminalName(String terminalName) { this.terminalName = terminalName; }

    @Column(name = "device_id")
    public String getDeviceId() { return deviceId; }

    public void setDeviceId(String deviceId) { this.deviceId = deviceId; }
}
