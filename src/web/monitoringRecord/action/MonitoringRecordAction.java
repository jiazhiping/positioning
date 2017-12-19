package web.monitoringRecord.action;

import Common.CommonFunction;
import Common.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import model.monitoringRecord.business.IMonitoringRecordService;
import model.monitoringRecord.domain.Monitoring;
import model.monitoringRecord.domain.MonitoringView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import web.monitoringRecord.form.MonitoringRecordForm;
import web.monitoringRecord.form.MonitoringRecordQueryForm;

import java.util.List;

/**
 * 告警记录Action
 * Created by Myk on 2017/11/9.
 */
@Controller("monitoringRecordAction")
@Scope("prototype")
public class MonitoringRecordAction extends ActionSupport {
    private MonitoringRecordForm monitoringRecordForm = new MonitoringRecordForm();
    private MonitoringRecordQueryForm monitoringRecordQueryForm = new MonitoringRecordQueryForm();
    @Autowired
    private IMonitoringRecordService monitoringRecordService;
    private int flag;
    private String mapLongitude;//历史位置 经度
    private String mapLatitude;//历史位置 纬度
    // 分页
    private int pageSize = 10;// 每一页显示的记录条数
    private int curPage = 0;// 当前的页码
    private int totalPage;// 记录总条数
    private int pageMaxNumber = 10;// 分页上的显示最大的页码个数，默认显示10个页码

    /**
     * 进入告警记录列表
     *
     * @return
     */
    public String monitoringRecordList() {
        ActionContext act = ActionContext.getContext();
        act.getSession().put("currentTitle", "告警记录管理");
        act.getSession().put("currentMenuId", "03_0301");
        String whereHQL = getCondition(act);
        totalPage = monitoringRecordService.getMonitoringRecordListCount(whereHQL);
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
        List <MonitoringView> monitoringViews = monitoringRecordService.getMonitoringRecordViewList(whereHQL, start, pageSize);
        act.put("monitoringViews", monitoringViews);
        String[] alarm = {"提示 Information", "次要 minor", "主要 major", "严重 critical"};
        act.put("alarm", alarm);
        return "monitoringRecordList_" + Constants.STATUS_SUCCESS;
    }

    /**
     * 告警信息处理
     *
     * @return
     */
    public String updateMonitoringRecord() {
        List <String> monitoringIds = CommonFunction.convertStrGather(monitoringRecordForm.getMonitoringIds());
        for (String monitoringId : monitoringIds) {
            Monitoring monitoring = monitoringRecordService.getMonitoringRecord(monitoringId);
            monitoring.setMonitoringStatus(1);
            monitoringRecordService.updateMonitoringRecord(monitoring);
        }
        flag = 0;
        return "updateMonitoringRecord_" + Constants.STATUS_SUCCESS;
    }

    /**
     * 进入告警地图
     *
     * @return
     */
    public String toMonitoringRecordMap() {
        ActionContext act = ActionContext.getContext();
        act.getSession().put("currentTitle", "告警信息地图");
        act.getSession().put("currentMenuId", "03_0301");
        String whereHQL = getCondition(act);
        List <MonitoringView> monitoringViews = monitoringRecordService.getMonitoringRecordViewList(whereHQL);
        act.put("monitoringViews", monitoringViews);
        return "toMonitoringRecordMap_" + Constants.STATUS_SUCCESS;
    }

    /**
     * 定位该告警
     *
     * @return
     */
    public String MonitoringRecordMap() {
        MonitoringView monitoringView = monitoringRecordService.getMonitoringRecordView(monitoringRecordForm.getMonitoringId());
        mapLongitude = monitoringView.getLongitude();
        mapLatitude = monitoringView.getLatitude();
        flag = 0;
        System.out.println("位置_经纬度:" + mapLongitude + "," + mapLatitude);
        return "MonitoringRecordMap_" + Constants.STATUS_SUCCESS;
    }

    private String getCondition(ActionContext act) {
        return monitoringRecordService.getCondition(null, monitoringRecordForm.getLocatorName(), monitoringRecordQueryForm.getLocatorName(), monitoringRecordQueryForm.getTerminalName(), monitoringRecordQueryForm.getMonitoringGradeName());
    }


    public MonitoringRecordForm getMonitoringRecordForm() { return monitoringRecordForm; }

    public void setMonitoringRecordForm(MonitoringRecordForm monitoringRecordForm) { this.monitoringRecordForm = monitoringRecordForm; }

    public MonitoringRecordQueryForm getMonitoringRecordQueryForm() { return monitoringRecordQueryForm; }

    public void setMonitoringRecordQueryForm(MonitoringRecordQueryForm monitoringRecordQueryForm) { this.monitoringRecordQueryForm = monitoringRecordQueryForm; }

    public IMonitoringRecordService getMonitoringRecordService() { return monitoringRecordService; }

    public void setMonitoringRecordService(IMonitoringRecordService monitoringRecordService) { this.monitoringRecordService = monitoringRecordService; }

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

    public String getMapLongitude() { return mapLongitude; }

    public void setMapLongitude(String mapLongitude) { this.mapLongitude = mapLongitude; }

    public String getMapLatitude() { return mapLatitude; }

    public void setMapLatitude(String mapLatitude) { this.mapLatitude = mapLatitude; }
}
