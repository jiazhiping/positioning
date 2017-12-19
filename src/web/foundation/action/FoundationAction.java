package web.foundation.action;

import Common.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import model.foundation.business.IFoundationService;
import model.foundation.domain.Foundation;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import web.foundation.form.FoundationForm;

import java.lang.reflect.InvocationTargetException;

/**
 * 系统默认值Action
 * Created by Myk on 2017/11/15.
 */
@Controller("foundationAction")
@Scope("prototype")
public class FoundationAction extends ActionSupport {
    private FoundationForm foundationForm = new FoundationForm();
    @Autowired
    private IFoundationService foundationService;

    private int flag;
    // 分页
    private int pageSize = 10;// 每一页显示的记录条数
    private int curPage = 0;// 当前的页码
    private int totalPage;// 记录总条数
    private int pageMaxNumber = 10;// 分页上的显示最大的页码个数，默认显示10个页码

    /**
     * 进入修改默认阈值界面
     *
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public String foundationList() throws InvocationTargetException, IllegalAccessException {
        ActionContext act = ActionContext.getContext();
        act.getSession().put("currentTitle", "告警阈值设置");
        act.getSession().put("currentMenuId", "07_0701");
        Foundation foundation = foundationService.getFoundation();
        BeanUtils.copyProperties(foundationForm, foundation);
        return "foundationList_" + Constants.STATUS_SUCCESS;
    }

    /**
     * 修改默认阈值
     *
     * @return
     */
    public String updateFoundation() {
        Foundation foundation = new Foundation();
        try {
            BeanUtils.copyProperties(foundation, foundationForm);
        } catch (Exception e) {
            flag = 2;// Bean赋值有误
            return "updateFoundation_" + Constants.STATUS_SUCCESS;
        }
        boolean b = foundationService.updateFoundation(foundation);
        if (!b) {
            flag = 1;// 修改失败
            return "updateLocator_" + Constants.STATUS_SUCCESS;
        }
        flag = 0;// 修改成功
        return "updateFoundation_" + Constants.STATUS_SUCCESS;
    }

    public FoundationForm getFoundationForm() { return foundationForm; }

    public void setFoundationForm(FoundationForm foundationForm) { this.foundationForm = foundationForm; }

    public IFoundationService getFoundationService() { return foundationService; }

    public void setFoundationService(IFoundationService foundationService) { this.foundationService = foundationService; }

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
}
