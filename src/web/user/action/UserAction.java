package web.user.action;

import Common.CommonFunction;
import Common.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import model.Common.properties.FileAction;
import model.user.business.IUserService;
import model.user.domain.User;
import model.user.domain.UserRecord;
import model.user.domain.UserRecordView;
import model.user.domain.UserView;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import web.user.form.UserForm;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 用户管理Action
 * Created by Myk on 2017/10/30.
 */
@Controller("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport {
    private UserForm userForm = new UserForm();

    @Autowired
    private IUserService userService;
    @Autowired
    private FileAction fileAction;
    private int flag;
    // 分页
    private int pageSize = 10;// 每一页显示的记录条数
    private int curPage = 0;// 当前的页码
    private int totalPage;// 记录总条数
    private int pageMaxNumber = 10;// 分页上的显示最大的页码个数，默认显示10个页码


    /**
     * 退出重新登录
     *
     * @return
     */
    public String exitUser() {
        ActionContext act = ActionContext.getContext();
        act.getSession().remove("currUserId");
        act.getSession().remove("currUserName");
        return "exitUser_" + Constants.STATUS_SUCCESS;
    }

    /**
     * 用户退出系统
     *
     * @return
     */
    public String userExitUser() {
        User user = new User();
        user.setUserId(userForm.getUserId());
        user.setUserState(0);
        userService.updateState(user);
        flag = 0;
        return "userExitUser_" + Constants.STATUS_SUCCESS;
    }

    /**
     * 用户登陆
     *
     * @return
     */
    public String login() {
        ActionContext act = ActionContext.getContext();
        UserView u = userService.Login(userForm.getUserLoginName());
        if (u == null) {
            flag = 1;// 【登录名】不存在
            return "login_" + Constants.STATUS_SUCCESS;
        }
        if (!u.getUserLoginPassword().equals(CommonFunction.SHA256(userForm.getUserLoginPassword()))) {
            flag = 2;// 【密码】有误
            return "login_" + Constants.STATUS_SUCCESS;
        }
        act.getSession().put("currUserId", u.getUserId());// 用户Id
        act.getSession().put("currUserName", u.getUserName());// 用户姓名
        flag = 0;
        User user = new User();
        user.setUserId(u.getUserId());
        user.setUserState(1);
        userService.updateState(user);
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext()
                .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        String ip = CommonFunction.getIpAddr(request);
        System.out.println("登陆IP为：" + ip);
        UserRecord userRecord = new UserRecord();
        userRecord.setUserId(u.getUserId());
        userRecord.setRecordIp(ip);
        userService.saveUserRecord(userRecord);
        return "login_" + Constants.STATUS_SUCCESS;
    }

    /**
     * 进入修改当前用户密码界面
     *
     * @return
     */
    public String UpdateUserPasswordTo() {
        return "UpdateUserPasswordTo_" + Constants.STATUS_SUCCESS;
    }

    /**
     * 修改用户密码
     *
     * @return
     */
    public String updatePassword() {
        ActionContext act = ActionContext.getContext();
        String userId = (String) act.getSession().get("currUserId");// 用户Id
        UserView userView = userService.getUserView(userId);
        if (!userView.getUserLoginPassword().equals(CommonFunction.SHA256(userForm.getUserLoginPassword()))) {
            flag = 1;// 原密码有误
            return "updatePassword_" + Constants.STATUS_SUCCESS;
        }
        userView.setUserLoginPassword(userForm.getUserLoginPasswordNew());
        userView.setUpdateCode((String) act.getSession().get("currLoginName"));
        boolean b = userService.updateUserView(userView);
        if (!b) {
            flag = 2;// 修改有误
            return "updatePassword_" + Constants.STATUS_SUCCESS;
        }
        flag = 0;
        return "updatePassword_" + Constants.STATUS_SUCCESS;
    }

    /**
     * 重置用户密码
     *
     * @return
     */
    public String updateUserPassword() {
        // 根据用户Id，查询对应的用户信息
        User user = userService.getUser(userForm.getUserId());
        boolean f = userService.resetPassword(user);
        if (!f) {
            flag = 2;// 修改失败
            return "updateUserPassword_" + Constants.STATUS_SUCCESS;
        }
        flag = 0;// 修改成功
        return "updateUserPassword_" + Constants.STATUS_SUCCESS;
    }

    /**
     * 进入主界面
     *
     * @return
     */
    public String mainPage() {
        ActionContext act = ActionContext.getContext();
        act.getSession().put("currentTitle", "首页");
        act.getSession().put("currentMenuId", "00_00");
        return "mainPage_" + Constants.STATUS_SUCCESS;
    }

    /**
     * 进入用户管理界面
     *
     * @return
     */
    public String userList() {
        ActionContext act = ActionContext.getContext();
        act.getSession().put("currentTitle", "用户列表");
        act.getSession().put("currentMenuId", "06_0601");
        String whereHQL = getCondition(act, 2);
        totalPage = userService.getUserViewListCount(whereHQL);
        // 重新计算页码
        int count;
        if (totalPage % pageSize == 0) {
            count = totalPage / pageSize;
        } else {
            count = totalPage / pageSize + 1;
        }
        if (curPage > (count - 1)) {
            curPage--;
        }
        int start = curPage * pageSize;
        List <UserView> userViews = userService.getUserViewList(whereHQL, start, pageSize);
        act.put("userViews", userViews);
        return "userList_" + Constants.STATUS_SUCCESS;
    }


    /**
     * 进入添加用户界面
     *
     * @return
     */
    public String toAddUser() {
        ActionContext act = ActionContext.getContext();
        act.getSession().put("currentTitle", "添加用户信息界面");
        act.put("currOpt", "add");
        return "toAddUser_" + Constants.STATUS_SUCCESS;
    }

    /**
     * 添加用户
     *
     * @return
     */
    public String addUser() throws IOException {
        ActionContext act = ActionContext.getContext();
        boolean b = userService.getUserLoginName(null, userForm.getUserLoginName());
        if (!b) {
            flag = 1;//此用户已存在
            return "addUser_" + Constants.STATUS_SUCCESS;
        }
        UserView userView = new UserView();
        try {
            BeanUtils.copyProperties(userView, userForm);
        } catch (Exception e) {
            flag = 2;//Bean赋值有误
            return "addUser_" + Constants.STATUS_SUCCESS;
        }
        String LoginName = (String) act.getSession().get("currLoginName");
        userView.setCreateCode(LoginName);//创建人
        fileAction.setFileFileName(userForm.getMyFileFileName());
        fileAction.setFile(userForm.getMyFile());
        userView.setUserIcon(fileAction.uploadFile("user"));
        boolean bo = userService.saveUserView(userView);
        if (!bo) {
            flag = 3;// 添加失败
            return "addUser_" + Constants.STATUS_SUCCESS;
        }
        flag = 0;// 添加成功
        return "addUser_" + Constants.STATUS_SUCCESS;
    }

    /**
     * 进入修改用户界面
     *
     * @return
     */
    public String toUpdateUser() throws InvocationTargetException, IllegalAccessException {
        ActionContext act = ActionContext.getContext();
        act.getSession().put("currentTitle", "修改用户信息界面");
        act.put("currOpt", "update");
        UserView userView = userService.getUserView(userForm.getUserId());
        BeanUtils.copyProperties(userForm, userView);
        return "toUpdateUser_" + Constants.STATUS_SUCCESS;
    }

    /**
     * 修改用户信息
     *
     * @return
     */
    public String updateUser() throws IOException {
        ActionContext act = ActionContext.getContext();
        boolean b = userService.getUserLoginName(userForm.getUserId(), userForm.getUserLoginName());
        if (!b) {
            flag = 1;//此用户已存在
            return "updateUser_" + Constants.STATUS_SUCCESS;
        }
        UserView userView = new UserView();
        try {
            BeanUtils.copyProperties(userView, userForm);
        } catch (Exception e) {
            flag = 3;// Bean赋值有误
            return "updateUser_" + Constants.STATUS_SUCCESS;
        }
        userView.setUpdateCode((String) act.getSession().get("currLoginName"));
        if (!CommonFunction.VerdictNULL(userForm.getMyFile())) {
            fileAction.setFileFileName(userForm.getMyFileFileName());
            fileAction.setFile(userForm.getMyFile());
            userView.setUserIcon(fileAction.uploadFile("user"));
        }
        boolean bo = userService.updateUserView(userView);
        if (!bo) {
            flag = 2;// 修改失败
            return "updateUser_" + Constants.STATUS_SUCCESS;
        }
        flag = 0;// 修改成功
        return "updateUser_" + Constants.STATUS_SUCCESS;
    }


    /**
     * 删除用户
     *
     * @return
     */
    public String deleteUser() {
        List <String> userIds = CommonFunction.convertStrGather(userForm.getUserIds());
        for (String userId : userIds) {
            userService.deleteUser(userId);
        }
        flag = 0;// 修改成功
        return "deleteUser_" + Constants.STATUS_SUCCESS;
    }

    /**
     * 进入操作日志列表界面
     *
     * @return
     */
    public String userRecordList() {
        ActionContext act = ActionContext.getContext();
        act.getSession().put("currentTitle", "操作日志");
        act.getSession().put("currentMenuId", "06_0602");
        String whereHQL = getCondition(act, 1);
        totalPage = userService.getUserRecordListCount(whereHQL);
        // 重新计算页码
        int count;
        if (totalPage % pageSize == 0) {
            count = totalPage / pageSize;
        } else {
            count = totalPage / pageSize + 1;
        }
        if (curPage > (count - 1)) {
            curPage--;
        }
        int start = curPage * pageSize;
        List <UserRecordView> userRecordViews = userService.getUserRecordViewList(whereHQL, start, pageSize);
        act.put("userRecordViews", userRecordViews);
        return "userRecordList_" + Constants.STATUS_SUCCESS;
    }

    private String getCondition(ActionContext act, int type) {
        if (type == 0) {
            return userService.getUserCondition();
        }

        if (type == 1) {
            return userService.getUserRecordCondition(userForm.getUserId());
        }

        return "";
    }


    public UserForm getUserForm() { return userForm; }

    public void setUserForm(UserForm userForm) { this.userForm = userForm; }

    public IUserService getUserService() { return userService; }

    public void setUserService(IUserService userService) { this.userService = userService; }

    public int getFlag() { return flag; }

    public void setFlag(int flag) { this.flag = flag; }

    public int getPageSize() { return pageSize; }

    public void setPageSize(int pageSize) { this.pageSize = pageSize; }

    public int getCurPage() { return curPage; }

    public void setCurPage(int curPage) { this.curPage = curPage; }

    public int getTotalPage() { return totalPage; }

    public void setTotalPage(int totalPage) { this.totalPage = totalPage; }

    public int getPageMaxNumber() { return pageMaxNumber; }

    public void setPageMaxNumber(int pageMaxNumber) { this.pageMaxNumber = pageMaxNumber; }

    public FileAction getFileAction() { return fileAction; }

    public void setFileAction(FileAction fileAction) { this.fileAction = fileAction; }
}
