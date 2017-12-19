package model.user.persistence;

import model.user.domain.*;

import java.util.List;

/**
 * 用户持久化层接口
 * Created by Myk on 2017/10/30.
 */
public interface IUserManager {
    /**
     * 判断该用户是否存在（登陆系统）
     *
     * @param userId        用户Id
     * @param userLoginName 用户名
     * @return
     */
    List <UserView> getUserLoginName(String userId, String userLoginName);


    /**
     * 保存登陆信息
     *
     * @param userRecord 用户登陆信息记录表
     * @return
     */
    String saveUserRecord(UserRecord userRecord);

    /**
     * 查询条件
     *
     * @return
     */
    String getUserCondition();

    /**
     * 查询记录条数
     *
     * @param whereHQL 查询条件
     * @return
     */
    int getUserViewListCount(String whereHQL);

    /**
     * 查询记录（分页）
     *
     * @param whereHQL 查询条件
     * @param start    开始记录
     * @param pageSize 记录条数
     * @return
     */
    List <UserView> getUserViewList(String whereHQL, int start, int pageSize);

    /**
     * 添加用户信息
     *
     * @param userView 用户信息视图
     * @return
     */
    String saveUserView(UserView userView);

    /**
     * 根据Id查询用户(视图)
     *
     * @param userId 用户Id
     * @return
     */
    UserView getUserView(String userId);

    /**
     * 根据Id查询用户表
     *
     * @param userId 用户Id
     * @return
     */
    User getUser(String userId);

    /**
     * 根据Id查询用户信息表
     *
     * @param userId
     * @return
     */
    UserInformation getUserInformation(String userId);

    /**
     * 根据Id查询用户登陆信息表
     *
     * @return
     */
    UserRecord getUserRecord(String userId);

    /**
     * 修改用户信息
     *
     * @param user            用户表
     * @param userInformation 用户信息表
     * @return
     */
    boolean updateUser(User user, UserInformation userInformation);

    /**
     * 删除用户
     *
     * @param user            用户表
     * @param userInformation 用户信息表
     * @param userRecord      用户登陆信息记录表
     */
    void deleteUser(User user, UserInformation userInformation, UserRecord userRecord);

    /**
     * 操作日志查询条件
     *
     * @param userId 用户Id
     * @return
     */
    String getUserRecordCondition(String userId);

    /**
     * 查询操作日志记录条数
     *
     * @param whereHQL 查询条件
     * @return
     */
    int getUserRecordListCount(String whereHQL);

    /**
     * 查询操作日志记录记录（分页）
     *
     * @param whereHQL 查询条件
     * @param start    开始记录
     * @param pageSize 记录条数
     * @return
     */
    List <UserRecordView> getUserRecordViewList(String whereHQL, int start, int pageSize);

}
