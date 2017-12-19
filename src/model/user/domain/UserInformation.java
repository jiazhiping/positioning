package model.user.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户信息表
 * Created by Myk on 2017/10/30.
 */
@Entity(name = "UserInformation")
@Table(name = "t_user_information")
public class UserInformation {
    private String userId; // 用户ID 主键
    private String userIcon; // 用户头像
    private String userName; // 用户真实姓名
    private String userPhone; // 用户电话
    private String userEmail; // 用户邮箱
    private String userDepartment; // 用户部门
    private String CreateCode;// 创建人员
    private Date CreateTime;// 创建时间
    private String UpdateCode;// 修改人员
    private Date UpdateTime;// 修改时间

    @Id
    @Column(name = "user_id", length = 24, nullable = false)
    public String getUserId() { return userId; }

    @Column(name = "user_icon", length = 128)
    public String getUserIcon() { return userIcon; }

    @Column(name = "user_name", length = 24)
    public String getUserName() { return userName; }

    @Column(name = "user_phone", length = 24)
    public String getUserPhone() { return userPhone; }

    @Column(name = "user_email", length = 24)
    public String getUserEmail() { return userEmail; }

    @Column(name = "user_department", length = 24)
    public String getUserDepartment() { return userDepartment; }

    @Column(name = "create_code", length = 32)
    public String getCreateCode() {
        return CreateCode;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    public Date getCreateTime() {
        return CreateTime;
    }

    @Column(name = "update_code", length = 32)
    public String getUpdateCode() {
        return UpdateCode;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    public Date getUpdateTime() { return UpdateTime; }

    public void setUserId(String userId) { this.userId = userId; }

    public void setUserIcon(String userIcon) { this.userIcon = userIcon; }

    public void setUserName(String userName) { this.userName = userName; }

    public void setUserPhone(String userPhone) { this.userPhone = userPhone; }

    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }

    public void setUserDepartment(String userDepartment) { this.userDepartment = userDepartment; }

    public void setCreateCode(String createCode) {CreateCode = createCode; }

    public void setCreateTime(Date createTime) {CreateTime = createTime; }

    public void setUpdateCode(String updateCode) {UpdateCode = updateCode; }

    public void setUpdateTime(Date updateTime) {UpdateTime = updateTime; }
}
