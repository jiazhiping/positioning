package web.user.form;

import java.io.File;
import java.util.Date;

/**
 * 用户信息Form
 * Created by Myk on 2017/10/30.
 */
public class UserForm {
    private String userId; // 用户ID 主键
    private String userLoginName; // 用户名
    private String userLoginPassword; // 密码
    private int userType; // 用户类型 0管理员 ...
    private String userIcon; // 用户头像
    private String userName; // 用户真实姓名
    private String userPhone; // 用户电话
    private String userEmail; // 用户邮箱
    private String userDepartment; // 用户部门
    private String CreateCode;// 创建人员
    private Date CreateTime;// 创建时间
    private String UpdateCode;// 修改人员
    private Date UpdateTime;// 修改时间

    private File myFile;// 轮播图图片
    private String myFileContentType;// 上传文件的类型
    private String myFileFileName;// 文件上传的名称

    private String userLoginPasswordNew; // 新密码

    private String userIds;//用户Id组


    public String getUserId() { return userId; }

    public void setUserId(String userId) { this.userId = userId; }

    public String getUserLoginName() { return userLoginName; }

    public void setUserLoginName(String userLoginName) { this.userLoginName = userLoginName; }

    public String getUserLoginPassword() { return userLoginPassword; }

    public void setUserLoginPassword(String userLoginPassword) { this.userLoginPassword = userLoginPassword; }

    public int getUserType() { return userType; }

    public void setUserType(int userType) { this.userType = userType; }

    public String getUserIcon() { return userIcon; }

    public void setUserIcon(String userIcon) { this.userIcon = userIcon; }

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

    public String getUserPhone() { return userPhone; }

    public void setUserPhone(String userPhone) { this.userPhone = userPhone; }

    public String getUserEmail() { return userEmail; }

    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }

    public String getUserDepartment() { return userDepartment; }

    public void setUserDepartment(String userDepartment) { this.userDepartment = userDepartment; }

    public String getCreateCode() { return CreateCode; }

    public void setCreateCode(String createCode) {CreateCode = createCode; }

    public Date getCreateTime() { return CreateTime; }

    public void setCreateTime(Date createTime) {CreateTime = createTime; }

    public String getUpdateCode() { return UpdateCode; }

    public void setUpdateCode(String updateCode) {UpdateCode = updateCode; }

    public Date getUpdateTime() { return UpdateTime; }

    public void setUpdateTime(Date updateTime) {UpdateTime = updateTime; }

    public File getMyFile() { return myFile; }

    public void setMyFile(File myFile) { this.myFile = myFile; }

    public String getMyFileContentType() { return myFileContentType; }

    public void setMyFileContentType(String myFileContentType) { this.myFileContentType = myFileContentType; }

    public String getMyFileFileName() { return myFileFileName; }

    public void setMyFileFileName(String myFileFileName) { this.myFileFileName = myFileFileName; }

    public String getUserLoginPasswordNew() { return userLoginPasswordNew; }

    public void setUserLoginPasswordNew(String userLoginPasswordNew) { this.userLoginPasswordNew = userLoginPasswordNew; }

    public String getUserIds() { return userIds; }

    public void setUserIds(String userIds) { this.userIds = userIds; }
}
