package model.monitoringRecord.business;

import model.monitoringRecord.domain.Monitoring;
import model.monitoringRecord.domain.MonitoringView;

import java.util.List;

/**
 * 告警记录业务逻辑层接口
 * Created by Myk on 2017/11/16.
 */
public interface IMonitoringRecordService {
    /**
     * 告警记录查询条件
     *
     * @param monitoringStatus    告警状态 活跃：0 已处理：1
     * @param toLocatorName       定位器序列号
     * @param locatorName         定位器序列号
     * @param terminalName        终端序列号
     * @param monitoringGradeName 告警级别 提示 Information 次要 minor 主要 major 严重 critical
     * @return
     */
    String getCondition(String monitoringStatus, String toLocatorName, String terminalName, String monitoringGradeName, String locatorName);

    /**
     * 根据条件查询告警记录条数
     *
     * @param whereHQL
     * @return
     */
    int getMonitoringRecordListCount(String whereHQL);

    /**
     * 根据条件查询告警记录（视图）
     *
     * @param whereHQL
     * @param start
     * @param pageSize
     * @return
     */
    List <MonitoringView> getMonitoringRecordViewList(String whereHQL, int start, int pageSize);

    /**
     * 根据条件查询告警记录
     *
     * @param whereHQL
     * @return
     */
    List <MonitoringView> getMonitoringRecordViewList(String whereHQL);


    /**
     * 根据Id查询告警记录
     *
     * @param monitoringId
     * @return
     */
    Monitoring getMonitoringRecord(String monitoringId);

    /**
     * 保存告警信息
     *
     * @param monitoring
     * @return
     */
    String saveMonitoringRecord(Monitoring monitoring);

    /**
     * 修改告警信息
     *
     * @param monitoring
     * @return
     */
    boolean updateMonitoringRecord(Monitoring monitoring);

    /**
     * 根据ID查询告警信息（视图）
     *
     * @param monitoringId
     * @return
     */
    MonitoringView getMonitoringRecordView(String monitoringId);

    /**
     * 统计分析查询条件
     *
     * @param type_grade 告警类型/告警级别
     * @param type       类型 告警类型：0 告警级别：1
     * @return
     */
    String analyzeCondition(String type_grade, int type);
}
