package web.monitoring.action;

import Common.CommonFunction;
import Common.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import model.deviceUpLine.business.IDeviceUpLineService;
import model.deviceUpLine.domain.DeviceUpLine;
import model.gps.business.IGPSService;
import model.gps.domain.GPS;
import model.monitoring.business.IMonitoringService;
import model.monitoringRecord.business.IMonitoringRecordService;
import model.monitoringRecord.domain.Monitoring;
import model.monitoringRecord.domain.MonitoringView;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import web.monitoring.form.MonitoringForm;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 实时监控Action
 * Created by Myk on 2017/10/30.
 */
@Controller("monitoringAction")
@Scope("prototype")
public class MonitoringAction extends ActionSupport {
    private MonitoringForm monitoringForm = new MonitoringForm();
    @Autowired
    private IMonitoringService monitoringService;
    @Autowired
    private IMonitoringRecordService monitoringRecordService;
    @Autowired
    private IDeviceUpLineService deviceUpLineService;
    @Autowired
    private IGPSService gpsService;
    private int flag;
    private String mapLongitude;//经度
    private String mapLatitude;//纬度
    private String mapLevel;//级别
    private int location;//不定位：0 定位：1
    // 分页
    private int pageSize = 10;// 每一页显示的记录条数
    private int curPage = 0;// 当前的页码
    private int totalPage;// 记录总条数
    private int pageMaxNumber = 10;// 分页上的显示最大的页码个数，默认显示10个页码


    /**
     * 进入实时监控界面
     *
     * @return
     */
    public String monitoringList() {
        ActionContext act = ActionContext.getContext();
        act.getSession().put("currentTitle", "实时监控列表");
        act.getSession().put("currentMenuId", "01_0102");
        String MonitoringRecordWHERE = monitoringRecordService.getCondition("0", null, null, null, null);
        totalPage = monitoringRecordService.getMonitoringRecordListCount(MonitoringRecordWHERE);
        List <MonitoringView> monitoringViews = monitoringRecordService.getMonitoringRecordViewList(MonitoringRecordWHERE, 0, pageSize);
        act.put("monitoringViews", monitoringViews);

        String DeviceUpLineWHERE = deviceUpLineService.getCondition(null, null);
        List <DeviceUpLine> deviceUpLines = deviceUpLineService.getDeviceUpLineList(DeviceUpLineWHERE);
        act.put("deviceUpLines", deviceUpLines);

        if (!CommonFunction.VerdictNULL(monitoringForm.getDeviceId())) {
            DeviceUpLine deviceUpLine = deviceUpLineService.getDeviceUpLine(monitoringForm.getDeviceId());
            mapLongitude = deviceUpLine.getDeviceCurrentPositionLongitude();
            mapLatitude = deviceUpLine.getDeviceCurrentPositionLatitude();
            mapLevel = "14";
            location = 1;
        }
        return "monitoringList_" + Constants.STATUS_SUCCESS;
    }

    /**
     * 进入修改上报频率界面
     *
     * @return
     */
    public String updateFrequencyTo() throws InvocationTargetException, IllegalAccessException {
        ActionContext act = ActionContext.getContext();
        act.getSession().put("currentTitle", "上报频率信息");
        DeviceUpLine deviceUpLine = deviceUpLineService.getDeviceUpLine(monitoringForm.getDeviceId());
        BeanUtils.copyProperties(monitoringForm, deviceUpLine);
        String[] Frequency = {"15秒", "1分钟", "15分钟", "1小时", "3小时"};
        act.put("Frequency", Frequency);
        return "updateFrequencyTo_" + Constants.STATUS_SUCCESS;
    }

    /**
     * 修改上报频率
     *
     * @return
     */
    public String updateFrequency() {
        DeviceUpLine deviceUpLine = new DeviceUpLine();
        try {
            BeanUtils.copyProperties(deviceUpLine, monitoringForm);
        } catch (Exception e) {
            flag = 2;// Bean赋值有误
            return "updateFrequency_" + Constants.STATUS_SUCCESS;
        }
        boolean b = deviceUpLineService.updateFrequency(deviceUpLine);
        if (!b) {
            flag = 1;// 修改失败
            return "updateFrequency_" + Constants.STATUS_SUCCESS;
        }
        flag = 0;// 修改成功
        return "updateFrequency_" + Constants.STATUS_SUCCESS;
    }

    /**
     * 定位告警对应的设备
     *
     * @return
     */
    public String monitoring() {
        Monitoring monitoring = monitoringRecordService.getMonitoringRecord(monitoringForm.getMonitoringId());
        GPS gps = gpsService.getGPS(monitoring.getGpsId());
        DeviceUpLine deviceUpLine = deviceUpLineService.getDeviceUpLineName(gps.getLocatorName());
        mapLongitude = deviceUpLine.getDeviceCurrentPositionLongitude();
        mapLatitude = deviceUpLine.getDeviceCurrentPositionLatitude();
        flag = 0;
        System.out.println("位置_经纬度:" + mapLongitude + "," + mapLatitude);
        return "monitoring_" + Constants.STATUS_SUCCESS;
    }

    /**
     * 查询定位器
     *
     * @return
     */
    public String mapQuery() {
        DeviceUpLine deviceUpLine = deviceUpLineService.getDeviceUpLineName(monitoringForm.getLocatorName());
        if (!CommonFunction.VerdictNULL(deviceUpLine)) {
            mapLongitude = deviceUpLine.getDeviceCurrentPositionLongitude();
            mapLatitude = deviceUpLine.getDeviceCurrentPositionLatitude();
            flag = 0;
        } else {
            flag = 1;
        }
        return "mapQuery_" + Constants.STATUS_SUCCESS;
    }

    public MonitoringForm getMonitoringForm() { return monitoringForm; }

    public void setMonitoringForm(MonitoringForm monitoringForm) { this.monitoringForm = monitoringForm; }

    public IMonitoringRecordService getMonitoringRecordService() { return monitoringRecordService; }

    public void setMonitoringRecordService(IMonitoringRecordService monitoringRecordService) { this.monitoringRecordService = monitoringRecordService; }

    public IMonitoringService getMonitoringService() { return monitoringService; }

    public void setMonitoringService(IMonitoringService monitoringService) { this.monitoringService = monitoringService; }

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

    public String getMapLongitude() { return mapLongitude; }

    public void setMapLongitude(String mapLongitude) { this.mapLongitude = mapLongitude; }

    public String getMapLatitude() { return mapLatitude; }

    public void setMapLatitude(String mapLatitude) { this.mapLatitude = mapLatitude; }

    public IGPSService getGpsService() { return gpsService; }

    public void setGpsService(IGPSService gpsService) { this.gpsService = gpsService; }

    public String getMapLevel() { return mapLevel; }

    public void setMapLevel(String mapLevel) { this.mapLevel = mapLevel; }

    public int getLocation() { return location; }

    public void setLocation(int location) { this.location = location; }
}
