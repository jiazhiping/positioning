package model.user.persistence;

import Common.CommonFunction;
import Common.Constants;
import model.IMaYueManager;
import model.user.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 用户持久化层
 * Created by Myk on 2017/10/30.
 */
@Repository("userManager")
public class UserManager implements IUserManager {
    @Autowired
    public IMaYueManager maYueManager;

    @Override
    public List <UserView> getUserLoginName(String userId, String userLoginName) {
        String whereHQL = "WHERE 1=1";
        if (!CommonFunction.VerdictNULL(userId)) {
            whereHQL += " AND user_id<>'" + userId + "'";
        }
        if (!CommonFunction.VerdictNULL(userLoginName)) {
            whereHQL += " AND user_login_name='" + userLoginName + "'";
        }
        return maYueManager.getModelList(UserView.class, whereHQL + " ORDER BY create_time");

    }

    @Override
    public String saveUserRecord(UserRecord userRecord) {
        String ct = Constants.date_ymd.format(new Date());
        String maxId = maYueManager.getMaxId("t_user_record", "record_id", ct, 6,
                "where record_id like '" + ct + "%'");
        userRecord.setRecordId(maxId);
        userRecord.setRecordTime(new Date());
        userRecord.setCreateTime(new Date());
        return (String) maYueManager.saveModel(userRecord);
    }

    @Override
    public String getUserCondition() {
        String whereHQL = "WHERE 1=1 ";

        return whereHQL + " ORDER BY create_time";
    }

    @Override
    public int getUserViewListCount(String whereHQL) {
        return maYueManager.getModelListCount(UserView.class, whereHQL);
    }

    @Override
    public List <UserView> getUserViewList(String whereHQL, int start, int pageSize) {
        return maYueManager.getModelList(UserView.class, whereHQL, start, pageSize);
    }

    @Override
    public String saveUserView(UserView userView) {
        String ct = Constants.date_ymd.format(new Date());
        String maxId = maYueManager.getMaxId("t_user", "user_id", ct, 6,
                "where user_id like '" + ct + "%'");
        User user = new User();
        user.setUserId(maxId);
        user.setUserLoginName(userView.getUserLoginName());
        user.setUserLoginPassword(CommonFunction.SHA256(userView.getUserLoginPassword()));
        user.setUserType(0);
        user.setCreateCode(userView.getCreateCode());
        user.setCreateTime(new Date());
        String userId = (String) maYueManager.saveModel(user);
        UserInformation userInformation = new UserInformation();
        userInformation.setUserId(userId);
        userInformation.setUserIcon(CommonFunction.ConversionNUll(userView.getUserIcon()));
        userInformation.setUserName(CommonFunction.ConversionNUll(userView.getUserName()));
        userInformation.setUserPhone(CommonFunction.ConversionNUll(userView.getUserPhone()));
        userInformation.setUserEmail(CommonFunction.ConversionNUll(userView.getUserEmail()));
        userInformation.setUserDepartment(CommonFunction.ConversionNUll(userView.getUserDepartment()));
        userInformation.setCreateCode(userView.getCreateCode());
        userInformation.setCreateTime(new Date());
        String userInformationId = (String) maYueManager.saveModel(userInformation);
        return userInformationId;
    }

    @Override
    public UserView getUserView(String userId) {
        return maYueManager.getModel(UserView.class, userId);
    }

    @Override
    public User getUser(String userId) {
        return maYueManager.getModel(User.class, userId);
    }

    @Override
    public UserInformation getUserInformation(String userId) {
        return maYueManager.getModel(UserInformation.class, userId);
    }

    @Override
    public UserRecord getUserRecord(String userId) {
        return maYueManager.getModel(UserRecord.class, userId);
    }

    @Override
    public boolean updateUser(User user, UserInformation userInformation) {
        boolean userB = true;
        boolean userInformationB = true;
        if (!CommonFunction.VerdictNULL(user)) {
            user.setUpdateTime(new Date());
            boolean b = maYueManager.updateModel(user);
            if (b) {
                userB = true;
            } else {
                userB = false;
            }
        }

        if (!CommonFunction.VerdictNULL(userInformation)) {
            userInformation.setUpdateTime(new Date());
            boolean b = maYueManager.updateModel(userInformation);
            if (!b) {
                userInformationB = false;
            } else {
                userInformationB = true;
            }
        }

        if (!userB && !userInformationB) {
            return false;
        } else {
            return true;
        }

    }

    @Override
    public void deleteUser(User user, UserInformation userInformation, UserRecord userRecord) {
        if (!CommonFunction.VerdictNULL(user)) {
            maYueManager.deleteModel(user);
        }

        if (!CommonFunction.VerdictNULL(userInformation)) {
            maYueManager.deleteModel(userInformation);
        }

        if (!CommonFunction.VerdictNULL(userRecord)) {
            maYueManager.deleteModel(userRecord);
        }

    }

    @Override
    public String getUserRecordCondition(String userId) {
        String whereHQL = "WHERE 1=1 ";

        if (!CommonFunction.VerdictNULL(userId)) {
            whereHQL += " AND user_id='" + userId + "'";
        }

        return whereHQL + " ORDER BY create_time DESC";
    }

    @Override
    public int getUserRecordListCount(String whereHQL) {
        return maYueManager.getModelListCount(UserRecord.class, whereHQL);
    }

    @Override
    public List <UserRecordView> getUserRecordViewList(String whereHQL, int start, int pageSize) {
        return maYueManager.getModelList(UserRecordView.class, whereHQL, start, pageSize);
    }

    public IMaYueManager getMaYueManager() { return maYueManager; }

    public void setMaYueManager(IMaYueManager maYueManager) { this.maYueManager = maYueManager; }
}
