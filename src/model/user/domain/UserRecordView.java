package model.user.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户登陆信息记录表
 * Created by Myk on 2017/10/30.
 */
@Entity(name = "UserRecordView")
@Table(name = "v_user_record")
public class UserRecordView {
    private String recordId; // 记录ID 主键
    private String userId; // 用户ID
    private Date recordTime;// 登陆时间
    private String recordIp; // 登陆IP
    private Date createTime;// 创建时间

    private String userLoginName; // 用户名
    private String userName; // 用户真实姓名


    @Id
    @Column(name = "record_id")
    public String getRecordId() { return recordId; }

    @Column(name = "user_id")
    public String getUserId() { return userId; }

    @Column(name = "record_time")
    public Date getRecordTime() { return recordTime; }

    @Column(name = "record_ip")
    public String getRecordIp() { return recordIp; }

    @Column(name = "create_time")
    public Date getCreateTime() { return createTime; }

    @Column(name = "user_login_name")
    public String getUserLoginName() { return userLoginName; }

    @Column(name = "user_name")
    public String getUserName() { return userName; }

    public void setRecordId(String recordId) { this.recordId = recordId; }

    public void setUserId(String userId) { this.userId = userId; }

    public void setRecordTime(Date recordTime) { this.recordTime = recordTime; }

    public void setRecordIp(String recordIp) { this.recordIp = recordIp; }

    public void setCreateTime(Date createTime) { this.createTime = createTime; }

    public void setUserLoginName(String userLoginName) { this.userLoginName = userLoginName; }

    public void setUserName(String userName) { this.userName = userName; }
}
