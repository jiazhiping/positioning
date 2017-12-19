package model.user.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户登陆信息记录表
 * Created by Myk on 2017/10/30.
 */
@Entity(name = "UserRecord")
@Table(name = "t_user_record")
public class UserRecord {
    private String recordId; // 记录ID 主键
    private String userId; // 用户ID
    private Date recordTime;// 登陆时间
    private String recordIp; // 登陆IP
    private Date createTime;// 创建时间

    @Id
    @Column(name = "record_id", length = 24, nullable = false)
    public String getRecordId() { return recordId; }

    @Column(name = "user_id", length = 24)
    public String getUserId() { return userId; }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "record_time")
    public Date getRecordTime() { return recordTime; }

    @Column(name = "record_ip", length = 24)
    public String getRecordIp() { return recordIp; }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    public Date getCreateTime() { return createTime; }

    public void setRecordId(String recordId) { this.recordId = recordId; }

    public void setUserId(String userId) { this.userId = userId; }

    public void setRecordTime(Date recordTime) { this.recordTime = recordTime; }

    public void setRecordIp(String recordIp) { this.recordIp = recordIp; }

    public void setCreateTime(Date createTime) { this.createTime = createTime; }
}
