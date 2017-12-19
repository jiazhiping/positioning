package model.foundation.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * 系统默认值表
 * Created by Myk on 2017/11/15.
 */
@Entity(name = "Foundation")
@Table(name = "t_foundation")
public class Foundation {
    private String systemId;//系统编号 001
    private String threshold;//告警阈值
    private String battery;//告警电量
    private Date CreateTime;// 创建时间
    private Date UpdateTime;// 修改时间

    @Id
    @Column(name = "system_id", length = 24)
    public String getSystemId() { return systemId; }

    public void setSystemId(String systemId) { this.systemId = systemId; }

    @Column(name = "foundation_threshold", length = 16)
    public String getThreshold() { return threshold; }

    public void setThreshold(String threshold) { this.threshold = threshold; }

    @Column(name = "foundation_battery", length = 16)
    public String getBattery() { return battery; }

    public void setBattery(String battery) { this.battery = battery; }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    public Date getCreateTime() { return CreateTime; }

    public void setCreateTime(Date createTime) {CreateTime = createTime; }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    public Date getUpdateTime() { return UpdateTime; }

    public void setUpdateTime(Date updateTime) {UpdateTime = updateTime; }
}
