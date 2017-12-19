package model.user.business;

import Common.CommonFunction;
import model.user.domain.*;
import model.user.persistence.IUserManager;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户业务逻辑层
 * Created by Myk on 2017/10/30.
 */
@Service("userService")
@Transactional
public class UserService implements IUserService {
    @Autowired
    private IUserManager userManager;

    @Override
    public UserView Login(String userLoginName) {
        List <UserView> users = userManager.getUserLoginName(null, userLoginName);
        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }

    @Override
    public String saveUserRecord(UserRecord userRecord) {
        String UserRecordId = userManager.saveUserRecord(userRecord);
        return CommonFunction.ConversionNUll(UserRecordId);
    }

    @Override
    public String getUserCondition() {
        return userManager.getUserCondition();
    }


    @Override
    public int getUserViewListCount(String whereHQL) {
        return userManager.getUserViewListCount(whereHQL);
    }

    @Override
    public List <UserView> getUserViewList(String whereHQL, int start, int pageSize) {
        return userManager.getUserViewList(whereHQL, start, pageSize);
    }

    @Override
    public boolean getUserLoginName(String userId, String userLoginName) {
        List <UserView> userViews = userManager.getUserLoginName(userId, userLoginName);
        if (userViews.size() > 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean saveUserView(UserView userView) {
        String userId = userManager.saveUserView(userView);
        if (!CommonFunction.VerdictNULL(userId)) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public UserView getUserView(String userId) {
        return userManager.getUserView(userId);
    }

    @Override
    public boolean updateUserView(UserView userView) {
        User user = userManager.getUser(userView.getUserId());
        UserInformation userInformation = userManager.getUserInformation(userView.getUserId());

        user.setUserLoginName(userView.getUserLoginName());
        if (!CommonFunction.VerdictNULL(userView.getUserLoginPassword())) {
            user.setUserLoginPassword(CommonFunction.SHA256(userView.getUserLoginPassword()));
        }
        user.setUserType(userView.getUserType());
        user.setUpdateCode(userView.getUpdateCode());

        if (!CommonFunction.VerdictNULL(userView.getUserIcon())) {
            userInformation.setUserIcon(userView.getUserIcon());
        }
        userInformation.setUserName(userView.getUserName());
        userInformation.setUserPhone(userView.getUserPhone());
        userInformation.setUserEmail(userView.getUserEmail());
        userInformation.setUserDepartment(userView.getUserDepartment());
        userInformation.setUpdateCode(userView.getUpdateCode());

        return userManager.updateUser(user, userInformation);
    }

    @Override
    public void deleteUser(String userId) {
        User user = userManager.getUser(userId);
        UserInformation userInformation = userManager.getUserInformation(userId);
        UserRecord userRecord = userManager.getUserRecord(userId);
        userManager.deleteUser(user, userInformation, userRecord);
    }

    @Override
    public String getUserRecordCondition(String userId) {
        return userManager.getUserRecordCondition(userId);
    }

    @Override
    public int getUserRecordListCount(String whereHQL) {
        return userManager.getUserRecordListCount(whereHQL);
    }

    @Override
    public List <UserRecordView> getUserRecordViewList(String whereHQL, int start, int pageSize) {
        return userManager.getUserRecordViewList(whereHQL, start, pageSize);
    }

    @Override
    public User getUser(String userId) {
        return userManager.getUser(userId);
    }

    @Override
    public boolean resetPassword(User user) {
        user.setUserLoginPassword(CommonFunction.SHA256("123456"));
        return userManager.updateUser(user, null);
    }

    @Override
    public boolean updateState(User user) {
        User u = userManager.getUser(user.getUserId());
        u.setUserState(user.getUserState());
        return userManager.updateUser(u, null);
    }

    @JSON(serialize = false)
    public IUserManager getUserManager() { return userManager; }

    public void setUserManager(IUserManager userManager) { this.userManager = userManager; }
}
