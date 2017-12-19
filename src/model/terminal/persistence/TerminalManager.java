package model.terminal.persistence;

import Common.CommonFunction;
import Common.Constants;
import model.IMaYueManager;
import model.terminal.domain.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 终端持久化层
 * Created by Myk on 2017/11/1.
 */
@Repository("terminalManager")
public class TerminalManager implements ITerminalManager {
    @Autowired
    private IMaYueManager maYueManager;

    @Override
    public String getCondition(String terminalState) {
        String whereHQL = "WHERE 1=1 ";

        if (!CommonFunction.VerdictNULL(terminalState)){
            if ("未绑定".equals(terminalState)){
                whereHQL += " AND terminal_state=0";
            }
            if ("已绑定".equals(terminalState)){
                whereHQL += " AND terminal_state=1";
            }
        }

        return whereHQL + " ORDER BY create_time";
    }

    @Override
    public int getTerminalListCount(String whereHQL) {
        return maYueManager.getModelListCount(Terminal.class, whereHQL);
    }

    @Override
    public List <Terminal> getTerminalList(String whereHQL, int start, int pageSize) {
        return maYueManager.getModelList(Terminal.class, whereHQL, start, pageSize);
    }

    @Override
    public List <Terminal> getTerminalList(String whereHQL) {
        return maYueManager.getModelList(Terminal.class, whereHQL);
    }

    @Override
    public List <Terminal> getTerminalTerminalName(String terminalId, String terminalName) {
        String whereHQL = "WHERE 1=1 ";
        if (!CommonFunction.VerdictNULL(terminalId)) {
            whereHQL += " AND terminal_id<>'" + terminalId + "'";
        }
        if (!CommonFunction.VerdictNULL(terminalName)) {
            whereHQL += " AND terminal_name='" + terminalName + "'";
        }
        return maYueManager.getModelList(Terminal.class, whereHQL + " ORDER BY create_time");
    }

    @Override
    public String saveTerminal(Terminal terminal) {
        String ct = Constants.date_ymd.format(new Date());
        String maxId = maYueManager.getMaxId("t_terminal", "terminal_id", ct, 6,
                "where terminal_id like '" + ct + "%'");
        terminal.setTerminalId(maxId);
        terminal.setTerminalState(0);
        terminal.setCreateTime(new Date());
        return (String) maYueManager.saveModel(terminal);
    }

    @Override
    public Terminal getTerminal(String terminalId) {
        return maYueManager.getModel(Terminal.class, terminalId);
    }

    @Override
    public boolean updateTerminal(Terminal terminal) {
        return maYueManager.updateModel(terminal);
    }

    @Override
    public void deleteTerminal(Terminal terminal) {
        maYueManager.deleteModel(terminal);
    }

    public IMaYueManager getMaYueManager() { return maYueManager; }

    public void setMaYueManager(IMaYueManager maYueManager) { this.maYueManager = maYueManager; }
}
