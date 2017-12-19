package model.locator.business;

import model.locator.domain.Locator;
import model.locator.domain.LocatorView;

import java.util.List;

/**
 * 定位器业务逻辑层接口
 * Created by Myk on 2017/11/1.
 */
public interface ILocatorService {
    /**
     * 定位器查询条件
     *
     * @param locatorState 绑定状态 未绑定||已绑定||NULL
     * @return
     */
    String getCondition(String locatorState);

    /**
     * 查询定位器记录个数
     *
     * @param whereHQL 查询条件
     * @return
     */
    int getLocatorListCount(String whereHQL);

    /**
     * 查询定位器记录列表（分页）
     *
     * @param whereHQL 查询条件
     * @param start    开始记录
     * @param pageSize 记录条数
     * @return
     */
    List <Locator> getLocatorList(String whereHQL, int start, int pageSize);

    /**
     * 查询定位器记录列表（分页）(视图)
     *
     * @param whereHQL 查询条件
     * @param start    开始记录
     * @param pageSize 记录条数
     * @return
     */
    List <LocatorView> getLocatorViewList(String whereHQL, int start, int pageSize);

    /**
     * 查询定位器记录列表
     *
     * @param whereHQL 查询条件
     * @return
     */
    List <Locator> getLocatorList(String whereHQL);

    /**
     * 判断该定位器唯一性(唯一:null 不唯一：Locator)
     *
     * @param locatorId   定位器Id
     * @param locatorName 定位器序列号
     * @return
     */
    Locator getLocatorLocatorName(String locatorId, String locatorName);

    /**
     * 添加定位器
     *
     * @param locator 定位器表
     * @return
     */
    boolean saveLocator(Locator locator);

    /**
     * 根据定位器Id查询记录
     *
     * @param locatorId 定位器Id
     * @return
     */
    Locator getLocator(String locatorId);

    /**
     * 修改定位器
     *
     * @param locator 定位器表
     * @return
     */
    boolean updateLocator(Locator locator);

    /**
     * 删除定位器
     *
     * @param locator 定位器表
     */
    void deleteLocator(Locator locator);


}
