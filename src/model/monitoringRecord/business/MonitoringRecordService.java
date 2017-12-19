package model.monitoringRecord.business;

import Common.CommonFunction;
import model.deviceUpLine.business.IDeviceUpLineService;
import model.deviceUpLine.domain.DeviceUpLine;
import model.monitoringRecord.domain.Monitoring;
import model.monitoringRecord.domain.MonitoringView;
import model.monitoringRecord.persistence.IMonitoringRecordManager;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 告警记录业务逻辑层
 * Created by Myk on 2017/11/16.
 */
@Service("monitoringRecordService")
@Transactional
public class MonitoringRecordService implements IMonitoringRecordService {
    @Autowired
    private IMonitoringRecordManager monitoringRecordManager;
    @Autowired
    private IDeviceUpLineService deviceUpLineService;

    @Override
    public String getCondition(String monitoringStatus, String toLocatorName, String locatorName, String terminalName, String monitoringGradeName) {
        String monitoringGrade = "";
        if ("提示 Information".equals(monitoringGradeName)) {
            monitoringGrade = "0";
        } else if ("次要 minor".equals(monitoringGradeName)) {
            monitoringGrade = "1";
        } else if ("主要 major".equals(monitoringGradeName)) {
            monitoringGrade = "2";
        } else if ("严重 critical".equals(monitoringGradeName)) {
            monitoringGrade = "3";
        }

        String Name = "";
        if (!CommonFunction.VerdictNULL(toLocatorName)) {
            Name = toLocatorName;
        }
        if (!CommonFunction.VerdictNULL(locatorName)) {
            Name = locatorName;
        }
        return monitoringRecordManager.getCondition(monitoringStatus, Name, terminalName, monitoringGrade);
    }

    @Override
    public int getMonitoringRecordListCount(String whereHQL) {
        return monitoringRecordManager.getMonitoringRecordListCount(whereHQL);
    }

    @Override
    public List <MonitoringView> getMonitoringRecordViewList(String whereHQL, int start, int pageSize) {
        return monitoringRecordManager.getMonitoringRecordViewList(whereHQL, start, pageSize);
    }

    @Override
    public List <MonitoringView> getMonitoringRecordViewList(String whereHQL) {
        return monitoringRecordManager.getMonitoringRecordViewList(whereHQL);
    }

    @Override
    public Monitoring getMonitoringRecord(String monitoringId) {
        return monitoringRecordManager.getMonitoringRecord(monitoringId);
    }

    @Override
    public String saveMonitoringRecord(Monitoring monitoring) {
        return monitoringRecordManager.saveMonitoringRecord(monitoring);
    }

    @Override
    public boolean updateMonitoringRecord(Monitoring monitoring) {
        getDeviceAlarm(monitoring.getMonitoringId());
        return monitoringRecordManager.updateMonitoringRecord(monitoring);
    }

    /**
     * 更改设备告警状态
     *
     * @param monitoringId 告警Id
     * @return
     */
    private void getDeviceAlarm(String monitoringId) {
        MonitoringView monitoringView = monitoringRecordManager.getMonitoringRecordView(monitoringId);
        List <MonitoringView> monitoringViews = monitoringRecordManager.getMonitoringRecordViewList(monitoringRecordManager.getCondition("0", monitoringView.getLocatorName(), null, null));
        if (monitoringViews.size() > 1) {
            DeviceUpLine deviceUpLine = deviceUpLineService.getDeviceUpLineName(monitoringView.getLocatorName());
            deviceUpLine.setDeviceAlarm(monitoringViews.get(1).getMonitoringGrade() + 1);
            deviceUpLineService.updateDeviceAlarm(deviceUpLine);
        } else {
            DeviceUpLine deviceUpLine = deviceUpLineService.getDeviceUpLineName(monitoringView.getLocatorName());
            deviceUpLine.setDeviceAlarm(0);
            deviceUpLineService.updateDeviceAlarm(deviceUpLine);
        }
    }


    @Override
    public MonitoringView getMonitoringRecordView(String monitoringId) {
        return monitoringRecordManager.getMonitoringRecordView(monitoringId);
    }

    @Override
    public String analyzeCondition(String type_grade, int type) {
        return monitoringRecordManager.analyzeCondition(type_grade, type);
    }

    @JSON(serialize = false)
    public IMonitoringRecordManager getMonitoringRecordManager() { return monitoringRecordManager; }

    public void setMonitoringRecordManager(IMonitoringRecordManager monitoringRecordManager) { this.monitoringRecordManager = monitoringRecordManager; }

    public IDeviceUpLineService getDeviceUpLineService() { return deviceUpLineService; }

    public void setDeviceUpLineService(IDeviceUpLineService deviceUpLineService) { this.deviceUpLineService = deviceUpLineService; }
}
