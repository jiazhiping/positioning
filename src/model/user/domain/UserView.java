package model.user.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 用户信息视图
 * Created by Myk on 2017/10/31.
 */
@Entity(name = "userView")
@Table(name = "v_user")
public class UserView {
    private String userId; // 用户ID 主键
    private String userLoginName; // 用户名
    private String userLoginPassword; // 密码
    private int userType; // 用户类型 管理员：0
    private int userState;// 用户登陆状态 未登录：0 已登录：1
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
    @Column(name = "user_id")
    public String getUserId() { return userId; }

    public void setUserId(String userId) { this.userId = userId; }

    @Column(name = "user_login_name")
    public String getUserLoginName() { return userLoginName; }

    public void setUserLoginName(String userLoginName) { this.userLoginName = userLoginName; }

    @Column(name = "user_login_password")
    public String getUserLoginPassword() { return userLoginPassword; }

    public void setUserLoginPassword(String userLoginPassword) { this.userLoginPassword = userLoginPassword; }

    @Column(name = "user_type")
    public int getUserType() { return userType; }

    public void setUserType(int userType) { this.userType = userType; }

    @Column(name = "user_state")
    public int getUserState() { return userState; }

    public void setUserState(int userState) { this.userState = userState; }

    @Column(name = "user_icon")
    public String getUserIcon() { return userIcon; }

    public void setUserIcon(String userIcon) { this.userIcon = userIcon; }

    @Column(name = "user_name")
    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

    @Column(name = "user_phone")
    public String getUserPhone() { return userPhone; }

    public void setUserPhone(String userPhone) { this.userPhone = userPhone; }

    @Column(name = "user_email")
    public String getUserEmail() { return userEmail; }

    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }

    @Column(name = "user_department")
    public String getUserDepartment() { return userDepartment; }

    public void setUserDepartment(String userDepartment) { this.userDepartment = userDepartment; }

    @Column(name = "create_code")
    public String getCreateCode() { return CreateCode; }

    public void setCreateCode(String createCode) {CreateCode = createCode; }

    @Column(name = "create_time")
    public Date getCreateTime() { return CreateTime; }

    public void setCreateTime(Date createTime) {CreateTime = createTime; }

    @Column(name = "update_code")
    public String getUpdateCode() { return UpdateCode; }

    public void setUpdateCode(String updateCode) {UpdateCode = updateCode; }

    @Column(name = "update_time")
    public Date getUpdateTime() { return UpdateTime; }

    public void setUpdateTime(Date updateTime) {UpdateTime = updateTime; }


}
