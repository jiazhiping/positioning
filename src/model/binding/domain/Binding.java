package model.binding.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * 绑定表
 * Created by Myk on 2017/11/1.
 */
@Entity(name = "Binding")
@Table(name = "t_binding")
public class Binding {
    private String bindingId; // 绑定Id  主键
    private String locatorId; // 定位器Id
    private String terminalId; // 终端Id
    private String CreateCode;// 创建人员
    private Date CreateTime;// 创建时间
    private String UpdateCode;// 修改人员
    private Date UpdateTime;// 修改时间

    @Id
    @Column(name = "binding_id", length = 24, nullable = false)
    public String getBindingId() { return bindingId; }

    @Column(name = "locator_id", length = 24)
    public String getLocatorId() { return locatorId; }

    @Column(name = "terminal_id", length = 24)
    public String getTerminalId() { return terminalId; }

    @Column(name = "create_code", length = 32)
    public String getCreateCode() {
        return CreateCode;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    public Date getCreateTime() {
        return CreateTime;
    }

    @Column(name = "update_code", length = 32)
    public String getUpdateCode() {
        return UpdateCode;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    public Date getUpdateTime() { return UpdateTime; }

    public void setBindingId(String bindingId) { this.bindingId = bindingId; }

    public void setLocatorId(String locatorId) { this.locatorId = locatorId; }

    public void setTerminalId(String terminalId) { this.terminalId = terminalId; }

    public void setCreateCode(String createCode) {CreateCode = createCode; }

    public void setCreateTime(Date createTime) {CreateTime = createTime; }

    public void setUpdateCode(String updateCode) {UpdateCode = updateCode; }

    public void setUpdateTime(Date updateTime) {UpdateTime = updateTime; }
}
