package web.terminal.form;

/**
 * POS终端实体类
 * Created by Myk on 2017/11/2.
 */
public class TerminalForm {
    private String terminalId; // 终端Id  主键
    private String terminalName; // 终端序列号
    private int terminalState; // 绑定状态

    private String terminalIds;//终端Id组

    public String getTerminalId() { return terminalId; }

    public void setTerminalId(String terminalId) { this.terminalId = terminalId; }

    public String getTerminalName() { return terminalName; }

    public void setTerminalName(String terminalName) { this.terminalName = terminalName; }

    public int getTerminalState() { return terminalState; }

    public void setTerminalState(int terminalState) { this.terminalState = terminalState; }

    public String getTerminalIds() { return terminalIds; }

    public void setTerminalIds(String terminalIds) { this.terminalIds = terminalIds; }
}
