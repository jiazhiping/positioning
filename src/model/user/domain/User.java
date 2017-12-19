package model.user.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户表
 * Created by Myk on 2017/10/30.
 */
@Entity(name = "User")
@Table(name = "t_user")
public class User {
    private String userId; // 用户ID 主键
    private String userLoginName; // 用户名
    private String userLoginPassword; // 密码
    private int userType; // 用户类型 管理员：0
    private int userState;// 用户登陆状态 未登录：0 已登录：1
    private String CreateCode;// 创建人员
    private Date CreateTime;// 创建时间
    private String UpdateCode;// 修改人员
    private Date UpdateTime;// 修改时间

    @Id
    @Column(name = "user_id", length = 24, nullable = false)
    public String getUserId() { return userId; }

    public void setUserId(String userId) { this.userId = userId; }

    @Column(name = "user_login_name", length = 32, nullable = false)
    public String getUserLoginName() { return userLoginName; }

    public void setUserLoginName(String userLoginName) { this.userLoginName = userLoginName; }

    @Column(name = "user_login_password", length = 128, nullable = false)
    public String getUserLoginPassword() { return userLoginPassword; }

    public void setUserLoginPassword(String userLoginPassword) { this.userLoginPassword = userLoginPassword; }

    @Column(name = "user_type")
    public int getUserType() { return userType; }

    public void setUserType(int userType) { this.userType = userType; }

    @Column(name = "user_state")
    public int getUserState() { return userState; }

    public void setUserState(int userState) { this.userState = userState; }

    @Column(name = "create_code", length = 32)
    public String getCreateCode() {
        return CreateCode;
    }

    public void setCreateCode(String createCode) {CreateCode = createCode; }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {CreateTime = createTime; }

    @Column(name = "update_code", length = 32)
    public String getUpdateCode() {
        return UpdateCode;
    }

    public void setUpdateCode(String updateCode) {UpdateCode = updateCode; }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    public Date getUpdateTime() { return UpdateTime; }

    public void setUpdateTime(Date updateTime) {UpdateTime = updateTime; }
}
