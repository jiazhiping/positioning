package model.terminal.persistence;

import model.terminal.domain.Terminal;

import java.util.List;

/**
 * POS终端持久化层接口
 * Created by Myk on 2017/11/1.
 */
public interface ITerminalManager {
    /**
     * POS终端查询条件
     *
     * @param terminalState 绑定状态 未绑定||已绑定||NULL
     * @return
     */
    String getCondition(String terminalState);

    /**
     * 根据条件查询POS终端记录条数
     *
     * @param whereHQL 查询条件
     * @return
     */
    int getTerminalListCount(String whereHQL);

    /**
     * 根据条件查询POS终端记录列表（分页）
     *
     * @param whereHQL 查询条件
     * @param start    开始条数
     * @param pageSize 记录条数
     * @return
     */
    List <Terminal> getTerminalList(String whereHQL, int start, int pageSize);

    /**
     * 根据条件查询POS终端记录列表
     *
     * @param whereHQL 查询条件
     * @return
     */
    List <Terminal> getTerminalList(String whereHQL);

    /**
     * 查询POS终端唯一性
     *
     * @param terminalId   POS终端Id
     * @param terminalName POS终端序列号
     * @return
     */
    List <Terminal> getTerminalTerminalName(String terminalId, String terminalName);

    /**
     * 添加POS终端
     *
     * @param terminal POS终端表
     * @return
     */
    String saveTerminal(Terminal terminal);

    /**
     * 查询POS终端记录
     *
     * @param terminalId POS终端Id
     * @return
     */
    Terminal getTerminal(String terminalId);

    /**
     * 修改POS终端
     *
     * @param terminal POS终端表
     * @return
     */
    boolean updateTerminal(Terminal terminal);

    /**
     * 删除POS终端
     *
     * @param terminal POS终端表
     */
    void deleteTerminal(Terminal terminal);

}
