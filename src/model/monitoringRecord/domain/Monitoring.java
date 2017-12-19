package model.monitoringRecord.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * 告警表
 * Created by Myk on 2017/11/7.
 */
@Entity(name = "Monitoring")
@Table(name = "t_monitoring")
public class Monitoring {
    private String monitoringId; // 告警Id  主键
    private String gpsId;//定位Id
    private String bindingId; // 绑定Id
    private String monitoringType; // 告警类型
    private int monitoringGrade; // 告警级别 提示：0 次要：1 主要：2 严重：3
    private String monitoringRemarks; // 告警备注
    private int monitoringStatus;//告警状态 活跃：0 已处理：1
    private Date CreateTime;// 创建时间
    private Date UpdateTime;// 修改时间

    @Id
    @Column(name = "monitoring_id", length = 24, nullable = false)
    public String getMonitoringId() { return monitoringId; }

    @Column(name = "gps_id", length = 24)
    public String getGpsId() { return gpsId; }

    @Column(name = "binding_id", length = 24)
    public String getBindingId() { return bindingId; }

    @Column(name = "monitoring_type", length = 24)
    public String getMonitoringType() { return monitoringType; }

    @Column(name = "monitoring_grade")
    public int getMonitoringGrade() { return monitoringGrade; }

    @Column(name = "monitoring_remarks", length = 1024)
    public String getMonitoringRemarks() { return monitoringRemarks; }

    @Column(name = "monitoring_status", length = 24)
    public int getMonitoringStatus() { return monitoringStatus; }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    public Date getCreateTime() {
        return CreateTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    public Date getUpdateTime() { return UpdateTime; }

    public void setMonitoringId(String monitoringId) { this.monitoringId = monitoringId; }

    public void setGpsId(String gpsId) { this.gpsId = gpsId; }

    public void setBindingId(String bindingId) { this.bindingId = bindingId; }

    public void setMonitoringType(String monitoringType) { this.monitoringType = monitoringType; }

    public void setMonitoringGrade(int monitoringGrade) { this.monitoringGrade = monitoringGrade; }

    public void setMonitoringRemarks(String monitoringRemarks) { this.monitoringRemarks = monitoringRemarks; }

    public void setMonitoringStatus(int monitoringStatus) { this.monitoringStatus = monitoringStatus; }

    public void setCreateTime(Date createTime) {CreateTime = createTime; }

    public void setUpdateTime(Date updateTime) {UpdateTime = updateTime; }
}

