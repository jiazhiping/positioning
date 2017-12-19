package model.monitoringRecord.persistence;

import Common.CommonFunction;
import model.IMaYueManager;
import model.monitoringRecord.domain.Monitoring;
import model.monitoringRecord.domain.MonitoringView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.Common.utils.PossUtil;

import java.util.Date;
import java.util.List;

/**
 * 告警记录持久化层
 * Created by Myk on 2017/11/16.
 */
@Repository("monitoringRecordManager")
public class MonitoringRecordManager implements IMonitoringRecordManager {
    @Autowired
    private IMaYueManager maYueManager;

    @Override
    public String getCondition(String monitoringStatus, String locatorName, String terminalName, String monitoringGrade) {
        String whereHQL = "WHERE 1=1 ";

        if (!CommonFunction.VerdictNULL(monitoringStatus)) {
            whereHQL += " AND monitoring_status=" + monitoringStatus;
        }

        if (!CommonFunction.VerdictNULL(locatorName)) {
            whereHQL += " AND gps_locator_name='" + locatorName + "'";
        }

        if (!CommonFunction.VerdictNULL(terminalName)) {
            whereHQL += " AND gps_terminal_name='" + terminalName + "'";
        }

        if (!CommonFunction.VerdictNULL(monitoringGrade)) {
            whereHQL += " AND monitoring_grade=" + monitoringGrade;
        }

        return whereHQL + " ORDER BY create_time DESC";
    }

    @Override
    public int getMonitoringRecordListCount(String whereHQL) {
        return maYueManager.getModelListCount(MonitoringView.class, whereHQL);
    }

    @Override
    public List <MonitoringView> getMonitoringRecordViewList(String whereHQL, int start, int pageSize) {
        return maYueManager.getModelList(MonitoringView.class, whereHQL, start, pageSize);
    }

    @Override
    public List <MonitoringView> getMonitoringRecordViewList(String whereHQL) {
        return maYueManager.getModelList(MonitoringView.class, whereHQL);
    }

    @Override
    public Monitoring getMonitoringRecord(String monitoringId) {
        return maYueManager.getModel(Monitoring.class, monitoringId);
    }

    @Override
    public String saveMonitoringRecord(Monitoring monitoring) {
//        String ct = Constants.date_ymd.format(new Date());
//        String maxId = maYueManager.getMaxId("t_monitoring", "monitoring_id", ct, 6,
//                "where monitoring_id like '" + ct + "%'");
//        monitoring.setMonitoringId(maxId);
        monitoring.setMonitoringId(PossUtil.getAesKey());
        monitoring.setMonitoringRemarks("");
        monitoring.setMonitoringStatus(0);
        monitoring.setCreateTime(new Date());
        return (String) maYueManager.saveModel(monitoring);
    }

    @Override
    public boolean updateMonitoringRecord(Monitoring monitoring) {
        monitoring.setUpdateTime(new Date());
        return maYueManager.updateModel(monitoring);
    }

    @Override
    public MonitoringView getMonitoringRecordView(String monitoringId) {
        return maYueManager.getModel(MonitoringView.class, monitoringId);
    }

    @Override
    public String analyzeCondition(String type_grade, int type) {
        if (type == 0) {
            return "WHERE monitoring_type='" + type_grade + "' ORDER BY create_time";
        } else if (type == 1) {
            return "WHERE monitoringGrade=" + type_grade + " ORDER BY create_time";
        }
        return null;
    }


    public IMaYueManager getMaYueManager() { return maYueManager; }

    public void setMaYueManager(IMaYueManager maYueManager) { this.maYueManager = maYueManager; }
}
