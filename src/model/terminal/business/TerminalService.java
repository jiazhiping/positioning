package model.terminal.business;

import Common.CommonFunction;
import model.terminal.domain.Terminal;
import model.terminal.persistence.ITerminalManager;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * POS终端业务逻辑层
 * Created by Myk on 2017/11/1.
 */
@Service("terminalService")
@Transactional
public class TerminalService implements ITerminalService {
    @Autowired
    private ITerminalManager terminalManager;

    @Override
    public String getCondition(String terminalState) {
        return terminalManager.getCondition(terminalState);
    }

    @Override
    public int getTerminalListCount(String whereHQL) {
        return terminalManager.getTerminalListCount(whereHQL);
    }

    @Override
    public List <Terminal> getTerminalList(String whereHQL, int start, int pageSize) {
        return terminalManager.getTerminalList(whereHQL, start, pageSize);
    }

    @Override
    public List <Terminal> getTerminalList(String whereHQL) {
        return terminalManager.getTerminalList(whereHQL);
    }

    @Override
    public Terminal getTerminalTerminalName(String terminalId, String terminalName) {
        List <Terminal> terminals = terminalManager.getTerminalTerminalName(terminalId, terminalName);
        if (terminals.size() > 0) {
            return terminals.get(0);//不唯一
        } else {
            return null;//唯一
        }
    }

    @Override
    public boolean saveTerminal(Terminal terminal) {
        String terminalId = terminalManager.saveTerminal(terminal);
        if (!CommonFunction.VerdictNULL(terminalId)) {
            return true;//成功
        } else {
            return false;//失败
        }
    }

    @Override
    public Terminal getTerminal(String terminalId) {
        return terminalManager.getTerminal(terminalId);
    }

    @Override
    public boolean updateTerminal(Terminal terminal) {
        Terminal t = terminalManager.getTerminal(terminal.getTerminalId());
        t.setTerminalName(terminal.getTerminalName());
        t.setTerminalState(terminal.getTerminalState());
        t.setUpdateCode(terminal.getUpdateCode());
        t.setUpdateTime(new Date());
        return terminalManager.updateTerminal(t);
    }

    @Override
    public void deleteTerminal(Terminal terminal) {
        terminalManager.deleteTerminal(terminal);
    }

    @JSON(serialize = false)
    public ITerminalManager getTerminalManager() { return terminalManager; }

    public void setTerminalManager(ITerminalManager terminalManager) { this.terminalManager = terminalManager; }
}
