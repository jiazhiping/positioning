package web.terminal.action;

import Common.CommonFunction;
import Common.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import model.binding.business.IBindingService;
import model.binding.domain.Binding;
import model.binding.domain.BindingView;
import model.terminal.business.ITerminalService;
import model.terminal.domain.Terminal;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import web.terminal.form.TerminalForm;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * POS终端Action
 * Created by Myk on 2017/11/2.
 */
@Controller("terminalAction")
@Scope("prototype")
public class TerminalAction extends ActionSupport {
    private TerminalForm terminalForm = new TerminalForm();

    @Autowired
    private ITerminalService terminalService;
    @Autowired
    private IBindingService bindingService;
    private int flag;
    private String terminalName;
    // 分页
    private int pageSize = 10;// 每一页显示的记录条数
    private int curPage = 0;// 当前的页码
    private int totalPage;// 记录总条数
    private int pageMaxNumber = 10;// 分页上的显示最大的页码个数，默认显示10个页码

    /**
     * 进入POS终端管理列表界面
     *
     * @return
     */
    public String terminalList() {
        ActionContext act = ActionContext.getContext();
        act.getSession().put("currentTitle", "POS终端管理列表");
        act.getSession().put("currentMenuId", "04_0402");
        String whereHQL = getCondition(act);
        totalPage = terminalService.getTerminalListCount(whereHQL);
        // 重新计算页码
        int count;
        if (totalPage % pageSize == 0) {
            count = totalPage / pageSize;
        } else {
            count = totalPage / pageSize + 1;
        }
        if (curPage > (count - 1)) {
            curPage--;
        }
        int start = curPage * pageSize;
        List <Terminal> terminals = terminalService.getTerminalList(whereHQL, start, pageSize);
        act.put("terminals", terminals);
        return "terminalList_" + Constants.STATUS_SUCCESS;
    }

    /**
     * 进入添加POS终端界面
     *
     * @return
     */
    public String toAddTerminal() {
        ActionContext act = ActionContext.getContext();
        act.getSession().put("currentTitle", "POS终端添加界面");
        act.put("currOpt", "add");
        return "toAddTerminal_" + Constants.STATUS_SUCCESS;
    }

    /**
     * 添加POS终端
     *
     * @return
     */
    public String addTerminal() {
        ActionContext act = ActionContext.getContext();
        Terminal terminal = terminalService.getTerminalTerminalName(null, terminalForm.getTerminalName());
        if (!CommonFunction.VerdictNULL(terminal)) {
            flag = 1;//此POS终端已存在
            return "addTerminal_" + Constants.STATUS_SUCCESS;
        }
        terminal = new Terminal();
        try {
            BeanUtils.copyProperties(terminal, terminalForm);
        } catch (Exception e) {
            flag = 2;// Bean赋值有误
            return "addTerminal_" + Constants.STATUS_SUCCESS;
        }
        terminal.setUpdateCode((String) act.getSession().get("currLoginName"));
        boolean bo = terminalService.saveTerminal(terminal);
        if (!bo) {
            flag = 3;// 添加失败
            return "addTerminal_" + Constants.STATUS_SUCCESS;
        }
        flag = 0;// 添加成功
        return "addTerminal_" + Constants.STATUS_SUCCESS;
    }

    /**
     * 进入修改POS终端界面
     *
     * @return
     */
    public String toUpdateTerminal() throws InvocationTargetException, IllegalAccessException {
        ActionContext act = ActionContext.getContext();
        act.getSession().put("currentTitle", "POS终端修改界面");
        act.put("currOpt", "update");
        Terminal terminal = terminalService.getTerminal(terminalForm.getTerminalId());
        BeanUtils.copyProperties(terminalForm, terminal);
        return "toUpdateTerminal_" + Constants.STATUS_SUCCESS;
    }

    /**
     * 修改POS终端
     *
     * @return
     */
    public String updateTerminal() {
        ActionContext act = ActionContext.getContext();
        Terminal terminal = terminalService.getTerminalTerminalName(terminalForm.getTerminalId(), terminalForm.getTerminalName());
        if (!CommonFunction.VerdictNULL(terminal)) {
            flag = 1;//此POS终端已存在
            return "updateTerminal_" + Constants.STATUS_SUCCESS;
        }
        terminal = new Terminal();
        try {
            BeanUtils.copyProperties(terminal, terminalForm);
        } catch (Exception e) {
            flag = 3;// Bean赋值有误
            return "updateTerminal_" + Constants.STATUS_SUCCESS;
        }
        terminal.setUpdateCode((String) act.getSession().get("currLoginName"));
        boolean bo = terminalService.updateTerminal(terminal);
        if (!bo) {
            flag = 2;// 修改失败
            return "updateTerminal_" + Constants.STATUS_SUCCESS;
        }
        flag = 0;// 修改成功
        return "updateTerminal_" + Constants.STATUS_SUCCESS;
    }

    /**
     * 删除POS终端
     *
     * @return
     */
    public String deleteTerminal() {
        List <String> TerminalIds = CommonFunction.convertStrGather(terminalForm.getTerminalIds());
        for (String terminalId : TerminalIds) {
            BindingView bindingView = bindingService.getBindingViewStatus(null, terminalId);
            if (bindingView != null) {
                terminalName = bindingView.getTerminalName();
                flag = 1;//POS终端已绑定
                return "deleteTerminal_" + Constants.STATUS_SUCCESS;
            }
            Terminal terminal = terminalService.getTerminal(terminalId);
            terminalService.deleteTerminal(terminal);
        }
        flag = 0;// 删除成功
        return "deleteTerminal_" + Constants.STATUS_SUCCESS;
    }

    /**
     * 进入POS终端选择界面
     *
     * @return
     */
    public String terminalSelect() {
        ActionContext act = ActionContext.getContext();
        List <Terminal> terminals = terminalService.getTerminalList(terminalService.getCondition("未绑定"));
        act.put("terminals", terminals);
        return "terminalSelect_" + Constants.STATUS_SUCCESS;
    }

    private String getCondition(ActionContext act) {
        return terminalService.getCondition(null);
    }

    public TerminalForm getTerminalForm() { return terminalForm; }

    public void setTerminalForm(TerminalForm terminalForm) { this.terminalForm = terminalForm; }

    public ITerminalService getTerminalService() { return terminalService; }

    public void setTerminalService(ITerminalService terminalService) { this.terminalService = terminalService; }

    public int getFlag() { return flag; }

    public void setFlag(int flag) { this.flag = flag; }

    public String getTerminalName() { return terminalName; }

    public void setTerminalName(String terminalName) { this.terminalName = terminalName; }

    public IBindingService getBindingService() { return bindingService; }

    public void setBindingService(IBindingService bindingService) { this.bindingService = bindingService; }

    public int getPageSize() { return pageSize; }

    public void setPageSize(int pageSize) { this.pageSize = pageSize; }

    public int getCurPage() { return curPage; }

    public void setCurPage(int curPage) { this.curPage = curPage; }

    public int getTotalPage() { return totalPage; }

    public void setTotalPage(int totalPage) { this.totalPage = totalPage; }

    public int getPageMaxNumber() { return pageMaxNumber; }

    public void setPageMaxNumber(int pageMaxNumber) { this.pageMaxNumber = pageMaxNumber; }
}
