package web.locator.action;

import Common.CommonFunction;
import Common.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import model.binding.business.IBindingService;
import model.binding.domain.BindingView;
import model.deviceUpLine.business.IDeviceUpLineService;
import model.deviceUpLine.domain.DeviceUpLine;
import model.locator.business.ILocatorService;
import model.locator.domain.Locator;
import model.locator.domain.LocatorView;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import web.locator.form.LocatorForm;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 定位器Action
 * Created by Myk on 2017/11/1.
 */
@Controller("locatorAction")
@Scope("prototype")
public class LocatorAction extends ActionSupport {
    private LocatorForm locatorForm = new LocatorForm();

    @Autowired
    private ILocatorService locatorService;
    @Autowired
    private IBindingService bindingService;
    @Autowired
    private IDeviceUpLineService deviceUpLineService;
    private int flag;
    private String locatorName;
    private String deviceId;//设备ID
    // 分页
    private int pageSize = 10;// 每一页显示的记录条数
    private int curPage = 0;// 当前的页码
    private int totalPage;// 记录总条数
    private int pageMaxNumber = 10;// 分页上的显示最大的页码个数，默认显示10个页码

    /**
     * 进入定位器管理列表界面
     *
     * @return
     */
    public String locatorList() {
        ActionContext act = ActionContext.getContext();
        act.getSession().put("currentTitle", "定位器管理列表");
        act.getSession().put("currentMenuId", "04_0401");
        String whereHQL = getCondition(act);
        totalPage = locatorService.getLocatorListCount(whereHQL);
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
        List <LocatorView> locatorViews = locatorService.getLocatorViewList(whereHQL, start, pageSize);
        act.put("locatorViews", locatorViews);
        return "locatorList_" + Constants.STATUS_SUCCESS;
    }

    /**
     * 进入定位器添加界面
     *
     * @return
     */
    public String toAddLocator() {
        ActionContext act = ActionContext.getContext();
        act.getSession().put("currentTitle", "定位器添加界面");
        act.put("currOpt", "add");
        String[] Frequency = {"15秒", "1分钟", "15分钟", "1小时", "3小时"};
        act.put("Frequency", Frequency);
        return "toAddLocator_" + Constants.STATUS_SUCCESS;
    }

    /**
     * 添加定位器
     *
     * @return
     */
    public String addLocator() {
        ActionContext act = ActionContext.getContext();
        Locator locator = locatorService.getLocatorLocatorName(null, locatorForm.getLocatorName());
        if (!CommonFunction.VerdictNULL(locator)) {
            flag = 1;//此定位器已存在
            return "addLocator_" + Constants.STATUS_SUCCESS;
        }
        locator = new Locator();
        try {
            BeanUtils.copyProperties(locator, locatorForm);
        } catch (Exception e) {
            flag = 2;//Bean赋值有误
            return "addLocator_" + Constants.STATUS_SUCCESS;
        }
        locator.setCreateCode((String) act.getSession().get("currLoginName"));//创建人
        boolean bo = locatorService.saveLocator(locator);
        if (!bo) {
            flag = 3;// 添加失败
            return "addLocator_" + Constants.STATUS_SUCCESS;
        }
        flag = 0;// 添加成功
        return "addLocator_" + Constants.STATUS_SUCCESS;
    }

    /**
     * 进入定位器修改界面
     *
     * @return
     */
    public String toUpdateLocator() throws InvocationTargetException, IllegalAccessException {
        ActionContext act = ActionContext.getContext();
        act.getSession().put("currentTitle", "定位器修改界面");
        act.put("currOpt", "update");
        Locator locator = locatorService.getLocator(locatorForm.getLocatorId());
        BeanUtils.copyProperties(locatorForm, locator);

        String[] Frequency = {"15秒", "1分钟", "15分钟", "1小时", "3小时"};
        act.put("Frequency", Frequency);
        return "toUpdateLocator_" + Constants.STATUS_SUCCESS;
    }

    /**
     * 修改定位器
     *
     * @return
     */
    public String updateLocator() {
        ActionContext act = ActionContext.getContext();
        Locator locator = locatorService.getLocatorLocatorName(locatorForm.getLocatorId(), locatorForm.getLocatorName());
        if (!CommonFunction.VerdictNULL(locator)) {
            flag = 1;//此定位器已存在
            return "updateLocator_" + Constants.STATUS_SUCCESS;
        }
        locator = new Locator();
        try {
            BeanUtils.copyProperties(locator, locatorForm);
        } catch (Exception e) {
            flag = 3;// Bean赋值有误
            return "updateUser_" + Constants.STATUS_SUCCESS;
        }
        locator.setUpdateCode((String) act.getSession().get("currLoginName"));
        boolean bo = locatorService.updateLocator(locator);
        if (!bo) {
            flag = 2;// 修改失败
            return "updateLocator_" + Constants.STATUS_SUCCESS;
        }
        flag = 0;// 修改成功
        return "updateLocator_" + Constants.STATUS_SUCCESS;
    }

    /**
     * 删除定位器
     *
     * @return
     */
    public String deleteLocator() {
        List <String> LocatorIds = CommonFunction.convertStrGather(locatorForm.getLocatorIds());
        for (String locatorId : LocatorIds) {
            BindingView bindingView = bindingService.getBindingViewStatus(locatorId, null);
            if (bindingView != null) {
                locatorName = bindingView.getLocatorName();
                flag = 1;//POS终端已绑定
                return "deleteLocator_" + Constants.STATUS_SUCCESS;
            }
            Locator locator = locatorService.getLocator(locatorId);
            locatorService.deleteLocator(locator);
        }
        flag = 0;// 删除成功
        return "deleteLocator_" + Constants.STATUS_SUCCESS;
    }


    /**
     * 进入选择定位器界面
     *
     * @return
     */
    public String locatorSelect() {
        ActionContext act = ActionContext.getContext();
        List <Locator> locators = locatorService.getLocatorList(locatorService.getCondition("未绑定"));
        act.put("locators", locators);
        return "locatorSelect_" + Constants.STATUS_SUCCESS;
    }

    /**
     * 查询该设备是否上线
     *
     * @return
     */
    public String toMapLocator() {
        BindingView bindingView = bindingService.getBindingView(locatorForm.getBindingId());
        DeviceUpLine deviceUpLine = deviceUpLineService.getDeviceUpLineName(bindingView.getLocatorName());
        if (deviceUpLine != null) {
            deviceId = deviceUpLine.getDeviceId();
            flag = 0;
        } else {
            flag = 1;
        }
        return "toMapLocator_" + Constants.STATUS_SUCCESS;
    }

    private String getCondition(ActionContext act) {
        return locatorService.getCondition(null);
    }


    public LocatorForm getLocatorForm() { return locatorForm; }

    public void setLocatorForm(LocatorForm locatorForm) { this.locatorForm = locatorForm; }

    public ILocatorService getLocatorService() { return locatorService; }

    public void setLocatorService(ILocatorService locatorService) { this.locatorService = locatorService; }

    public IBindingService getBindingService() { return bindingService; }

    public void setBindingService(IBindingService bindingService) { this.bindingService = bindingService; }

    public int getFlag() { return flag; }

    public void setFlag(int flag) { this.flag = flag; }

    public String getLocatorName() { return locatorName; }

    public void setLocatorName(String locatorName) { this.locatorName = locatorName; }

    public int getPageSize() { return pageSize; }

    public void setPageSize(int pageSize) { this.pageSize = pageSize; }

    public int getCurPage() { return curPage; }

    public void setCurPage(int curPage) { this.curPage = curPage; }

    public int getTotalPage() { return totalPage; }

    public void setTotalPage(int totalPage) { this.totalPage = totalPage; }

    public int getPageMaxNumber() { return pageMaxNumber; }

    public void setPageMaxNumber(int pageMaxNumber) { this.pageMaxNumber = pageMaxNumber; }

    public IDeviceUpLineService getDeviceUpLineService() { return deviceUpLineService; }

    public void setDeviceUpLineService(IDeviceUpLineService deviceUpLineService) { this.deviceUpLineService = deviceUpLineService; }

    public String getDeviceId() { return deviceId; }

    public void setDeviceId(String deviceId) { this.deviceId = deviceId; }
}
