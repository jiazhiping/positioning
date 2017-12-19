package model.terminal.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * 终端表
 * Created by Myk on 2017/11/1.
 */
@Entity(name = "Terminal")
@Table(name = "t_terminal")
public class Terminal {
    private String terminalId; // 终端Id  主键
    private String terminalName; // 终端序列号
    private int terminalState; // 绑定状态 未绑定：0 已绑定：1
    private String CreateCode;// 创建人员
    private Date CreateTime;// 创建时间
    private String UpdateCode;// 修改人员
    private Date UpdateTime;// 修改时间

    @Id
    @Column(name = "terminal_id", length = 24, nullable = false)
    public String getTerminalId() { return terminalId; }

    @Column(name = "terminal_name", length = 24)
    public String getTerminalName() { return terminalName; }

    @Column(name = "terminal_state", length = 24)
    public int getTerminalState() { return terminalState; }

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

    public void setTerminalId(String terminalId) { this.terminalId = terminalId; }

    public void setTerminalName(String terminalName) { this.terminalName = terminalName; }

    public void setTerminalState(int terminalState) { this.terminalState = terminalState; }

    public void setCreateCode(String createCode) {CreateCode = createCode; }

    public void setCreateTime(Date createTime) {CreateTime = createTime; }

    public void setUpdateCode(String updateCode) {UpdateCode = updateCode; }

    public void setUpdateTime(Date updateTime) {UpdateTime = updateTime; }
}
