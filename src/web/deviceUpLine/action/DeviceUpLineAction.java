package web.deviceUpLine.action;

import Common.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import model.deviceUpLine.business.IDeviceUpLineService;
import model.deviceUpLine.domain.DeviceUpLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import web.deviceUpLine.form.DeviceUpLineForm;
import web.deviceUpLine.form.DeviceUpLineQueryForm;

import java.util.List;

/**
 * 在线设备Action
 * Created by Myk on 2017/11/8.
 */
@Controller("deviceUpLineAction")
@Scope("prototype")
public class DeviceUpLineAction extends ActionSupport {
    private DeviceUpLineForm deviceUpLineForm = new DeviceUpLineForm();
    private DeviceUpLineQueryForm deviceUpLineQueryForm = new DeviceUpLineQueryForm();
    @Autowired
    private IDeviceUpLineService deviceUpLineService;
    private int flag;
    // 分页
    private int pageSize = 10;// 每一页显示的记录条数
    private int curPage = 0;// 当前的页码
    private int totalPage;// 记录总条数
    private int pageMaxNumber = 10;// 分页上的显示最大的页码个数，默认显示10个页码

    /**
     * 进入已上线设备管理界面
     *
     * @return
     */
    public String deviceUpLineList() {
        ActionContext act = ActionContext.getContext();
        act.getSession().put("currentTitle", "在线设备");
        act.getSession().put("currentMenuId", "01_0101");
        String whereHQL = getCondition(act);
        totalPage = deviceUpLineService.getDeviceUpLineListCount(whereHQL);
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
        List <DeviceUpLine> deviceUpLines = deviceUpLineService.getDeviceUpLineList(whereHQL, start, pageSize);
        act.put("deviceUpLines", deviceUpLines);
        return "deviceUpLineList_" + Constants.STATUS_SUCCESS;
    }

    private String getCondition(ActionContext act) {
        return deviceUpLineService.getCondition(deviceUpLineQueryForm.getLocatorName(),deviceUpLineQueryForm.getTerminalName());
    }


    public DeviceUpLineForm getDeviceUpLineForm() { return deviceUpLineForm; }

    public void setDeviceUpLineForm(DeviceUpLineForm deviceUpLineForm) { this.deviceUpLineForm = deviceUpLineForm; }

    public DeviceUpLineQueryForm getDeviceUpLineQueryForm() { return deviceUpLineQueryForm; }

    public void setDeviceUpLineQueryForm(DeviceUpLineQueryForm deviceUpLineQueryForm) { this.deviceUpLineQueryForm = deviceUpLineQueryForm; }

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
}
