package model.user.business;

import model.user.domain.User;
import model.user.domain.UserRecord;
import model.user.domain.UserRecordView;
import model.user.domain.UserView;

import java.util.List;

/**
 * 用户业务逻辑层接口
 * Created by Myk on 2017/10/30.
 */
public interface IUserService {

    /**
     * 登陆系统（判断该用户是否存在）
     *
     * @param userLoginName 用户名
     * @return
     */
    UserView Login(String userLoginName);

    /**
     * 保存登陆信息
     *
     * @param userRecord 用户登陆信息记录表
     * @return
     */
    String saveUserRecord(UserRecord userRecord);

    /**
     * 用户查询条件
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
     * 查询用户记录（分页）
     *
     * @param whereHQL 查询条件
     * @param start    开始记录
     * @param pageSize 记录条数
     * @return
     */
    List <UserView> getUserViewList(String whereHQL, int start, int pageSize);

    /**
     * 判断该用户唯一性(唯一:true 不唯一：false)
     *
     * @param userId        用户ID
     * @param userLoginName 用户名
     * @return
     */
    boolean getUserLoginName(String userId, String userLoginName);

    /**
     * 添加用户（视图）
     *
     * @param userView 用户视图
     * @return
     */
    boolean saveUserView(UserView userView);

    /**
     * 根据Id查询用户（视图）
     *
     * @param userId 用户Id
     * @return
     */
    UserView getUserView(String userId);

    /**
     * 修改用户（视图）
     *
     * @param userView 用户视图
     * @return
     */
    boolean updateUserView(UserView userView);

    /**
     * 删除用户
     *
     * @param userId 用户Id
     */
    void deleteUser(String userId);

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

    /**
     * 查询用户信息
     *
     * @param userId 用户Id
     * @return
     */
    User getUser(String userId);

    /**
     * 重置用户密码
     *
     * @param user 用户表
     * @return
     */
    boolean resetPassword(User user);

    /**
     * 更改用户登陆状态
     *
     * @param user
     * @return
     */
    boolean updateState(User user);
}
