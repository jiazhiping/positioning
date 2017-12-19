package model.historicalRoute.persistence;

import Common.CommonFunction;
import model.IMaYueManager;
import model.historicalRoute.domain.HistoricalRoute;
import model.historicalRoute.domain.HistoricalRouteView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.Common.utils.PossUtil;

import java.util.Date;
import java.util.List;

/**
 * 历史轨迹持久化层
 * Created by Myk on 2017/11/10.
 */
@Repository("historicalRouteManager")
public class HistoricalRouteManager implements IHistoricalRouteManager {
    @Autowired
    private IMaYueManager maYueManager;

    @Override
    public String getCondition(String locatorName) {
        String whereHQL = "WHERE 1=1 ";
        if (!CommonFunction.VerdictNULL(locatorName)) {
            whereHQL += " AND locator_name='" + locatorName + "'";
        }
        return whereHQL + " ORDER BY create_time DESC";
    }

    @Override
    public int getHistoricalRouteListCount(String whereHQL) {
        return maYueManager.getModelListCount(HistoricalRouteView.class, whereHQL);
    }

    @Override
    public List <HistoricalRouteView> getHistoricalRouteList(String whereHQL, int start, int pageSize) {
        return maYueManager.getModelList(HistoricalRouteView.class, whereHQL, start, pageSize);
    }

    @Override
    public List <HistoricalRouteView> getHistoricalRouteList(String whereHQL) {
        return maYueManager.getModelList(HistoricalRouteView.class, whereHQL);
    }

    @Override
    public String saveHistoricalRoute(HistoricalRoute historicalRoute) {
        int i = getHistoricalRouteListCount(getCondition(null));
        if (i == 0) {
            historicalRoute.setHistoricalRouteId("0");
        } else {
            historicalRoute.setHistoricalRouteId(PossUtil.getAesKey());
        }
        historicalRoute.setCreateTime(new Date());
        return (String) maYueManager.saveModel(historicalRoute);
    }

    @Override
    public HistoricalRouteView getHistoricalRouteView(String historicalRouteId) {
        return maYueManager.getModel(HistoricalRouteView.class, historicalRouteId);
    }

    public IMaYueManager getMaYueManager() { return maYueManager; }

    public void setMaYueManager(IMaYueManager maYueManager) { this.maYueManager = maYueManager; }
}
