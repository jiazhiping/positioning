package model.historicalRoute.business;

import model.historicalRoute.domain.HistoricalRoute;
import model.historicalRoute.domain.HistoricalRouteView;

import java.util.List;

/**
 * 历史轨迹业务逻辑层接口
 * Created by Myk on 2017/11/10.
 */
public interface IHistoricalRouteService {

    /**
     * 查询条件
     *
     * @param QlocatorName 定位器序列号（查询）
     * @param locatorName  定位器序列号
     * @return
     */
    String getCondition(String QlocatorName, String locatorName);

    /**
     * 查询历史记录条数
     *
     * @param whereHQL
     * @return
     */
    int getHistoricalRouteListCount(String whereHQL);

    /**
     * 查询历史记录(分页)
     *
     * @param whereHQL
     * @param start
     * @param pageSize
     * @return
     */
    List <HistoricalRouteView> getHistoricalRouteList(String whereHQL, int start, int pageSize);

    /**
     * 查询历史记录
     *
     * @param whereHQL
     * @return
     */
    List <HistoricalRouteView> getHistoricalRouteList(String whereHQL);

    /**
     * 保存历史记录
     *
     * @param historicalRoute
     */
    String saveHistoricalRoute(HistoricalRoute historicalRoute);

    /**
     * 根据Id查询历史定位
     *
     * @param historicalRouteId
     * @return
     */
    HistoricalRouteView getHistoricalRouteView(String historicalRouteId);
}
