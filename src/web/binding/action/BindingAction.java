package web.binding.action;

import Common.CommonFunction;
import Common.Constants;
import com.opensymphony.xwork2.ActionContext;
import model.binding.business.IBindingService;
import model.binding.domain.Binding;
import model.binding.domain.BindingView;
import model.deviceUpLine.business.IDeviceUpLineService;
import model.deviceUpLine.domain.DeviceUpLine;
import model.locator.business.ILocatorService;
import model.locator.domain.Locator;
import model.terminal.business.ITerminalService;
import model.terminal.domain.Terminal;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import web.binding.form.BindingForm;

import java.util.List;

/**
 * 关系Action
 * Created by Myk on 2017/11/2.
 */
public class BindingAction {
    private BindingForm bindingForm = new BindingForm();

    @Autowired
    private IBindingService bindingService;
    @Autowired
    private ILocatorService locatorService;
    @Autowired
    private ITerminalService terminalService;
    @Autowired
    private IDeviceUpLineService deviceUpLineService;
    private int flag;
    private String deviceId;//设备ID
    // 分页
    private int pageSize = 10;// 每一页显示的记录条数
    private int curPage = 0;// 当前的页码
    private int totalPage;// 记录总条数
    private int pageMaxNumber = 10;// 分页上的显示最大的页码个数，默认显示10个页码

    /**
     * 进入关系管理列表界面
     *
     * @return
     */
    public String bindingList() {
        ActionContext act = ActionContext.getContext();
        act.getSession().put("currentTitle", "关系管理列表");
        act.getSession().put("currentMenuId", "04_0403");
        String whereHQL = getCondition(act);
        totalPage = bindingService.getBindingViewListCount(whereHQL);
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
        List <BindingView> bindingViews = bindingService.getBindingViewList(whereHQL, start, pageSize);
        act.put("bindingViews", bindingViews);
        return "bindingList_" + Constants.STATUS_SUCCESS;
    }

    /**
     * 进入添加关系界面
     *
     * @return
     */
    public String toAddBinding() {
        ActionContext act = ActionContext.getContext();
        act.getSession().put("currentTitle", "绑定界面");
        act.put("currOpt", "add");
        return "toAddBinding_" + Constants.STATUS_SUCCESS;
    }

    /**
     * 绑定定位器与POS终端
     *
     * @return
     */
    public String addBinding() {
        ActionContext act = ActionContext.getContext();
        Binding binding = new Binding();
        try {
            BeanUtils.copyProperties(binding, bindingForm);
        } catch (Exception e) {
            flag = 2;//Bean赋值有误
            return "addBinding_" + Constants.STATUS_SUCCESS;
        }
        binding.setCreateCode((String) act.getSession().get("currLoginName"));//创建人
        boolean bo = bindingService.saveBinding(binding);
        if (!bo) {
            flag = 1;// 绑定失败
            return "addBinding_" + Constants.STATUS_SUCCESS;
        }
        flag = 0;// 绑定成功
        Locator locator = locatorService.getLocator(binding.getLocatorId());
        locator.setUpdateCode((String) act.getSession().get("currLoginName"));
        locator.setLocatorState(1);
        locatorService.updateLocator(locator);
        Terminal terminal = terminalService.getTerminal(binding.getTerminalId());
        terminal.setUpdateCode((String) act.getSession().get("currLoginName"));
        terminal.setTerminalState(1);
        terminalService.updateTerminal(terminal);
        return "addBinding_" + Constants.STATUS_SUCCESS;
    }

    /**
     * 解除绑定
     *
     * @return
     */
    public String deleteBinding() {
        List <String> BindingIds = CommonFunction.convertStrGather(bindingForm.getBindingIds());
        for (String bindingId : BindingIds) {
            ActionContext act = ActionContext.getContext();
            Binding binding = bindingService.getBinding(bindingId);
            Locator locator = locatorService.getLocator(binding.getLocatorId());
            locator.setUpdateCode((String) act.getSession().get("currLoginName"));
            locator.setLocatorState(0);
            locatorService.updateLocator(locator);
            Terminal terminal = terminalService.getTerminal(binding.getTerminalId());
            terminal.setUpdateCode((String) act.getSession().get("currLoginName"));
            terminal.setTerminalState(0);
            terminalService.updateTerminal(terminal);
            bindingService.deleteBinding(binding);
        }
        return "deleteBinding_" + Constants.STATUS_SUCCESS;
    }

    /**
     * 查询该设备是否上线
     *
     * @return
     */
    public String toMapBinding() {
        BindingView bindingView = bindingService.getBindingView(bindingForm.getBindingId());
        DeviceUpLine deviceUpLine = deviceUpLineService.getDeviceUpLineName(bindingView.getLocatorName());
        if (deviceUpLine != null) {
            deviceId = deviceUpLine.getDeviceId();
            flag = 0;
        } else {
            flag = 1;
        }
        return "toMapBinding_" + Constants.STATUS_SUCCESS;
    }

    private String getCondition(ActionContext act) {
        return bindingService.getCondition();
    }

    public BindingForm getBindingForm() { return bindingForm; }

    public void setBindingForm(BindingForm bindingForm) { this.bindingForm = bindingForm; }

    public IBindingService getBindingService() { return bindingService; }

    public void setBindingService(IBindingService bindingService) { this.bindingService = bindingService; }

    public ILocatorService getLocatorService() { return locatorService; }

    public void setLocatorService(ILocatorService locatorService) { this.locatorService = locatorService; }

    public ITerminalService getTerminalService() { return terminalService; }

    public void setTerminalService(ITerminalService terminalService) { this.terminalService = terminalService; }

    public IDeviceUpLineService getDeviceUpLineService() { return deviceUpLineService; }

    public void setDeviceUpLineService(IDeviceUpLineService deviceUpLineService) { this.deviceUpLineService = deviceUpLineService; }

    public int getFlag() { return flag; }

    public void setFlag(int flag) { this.flag = flag; }

    public int getPageSize() { return pageSize; }

    public void setPageSize(int pageSize) { this.pageSize = pageSize; }

    public int getCurPage() { return curPage; }

    public void setCurPage(int curPage) { this.curPage = curPage; }

    public int getTotalPage() { return totalPage; }

    public void setTotalPage(int totalPage) { this.totalPage = totalPage; }

    public int getPageMaxNumber() { return pageMaxNumber; }

    public void setPageMaxNumber(int pageMaxNumber) { this.pageMaxNumber = pageMaxNumber; }

    public String getDeviceId() { return deviceId; }

    public void setDeviceId(String deviceId) { this.deviceId = deviceId; }
}
