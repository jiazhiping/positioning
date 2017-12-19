package model.historicalRoute.business;

import Common.CommonFunction;
import model.historicalRoute.domain.HistoricalRoute;
import model.historicalRoute.domain.HistoricalRouteView;
import model.historicalRoute.persistence.IHistoricalRouteManager;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 历史轨迹业务逻辑层
 * Created by Myk on 2017/11/10.
 */
@Service("historicalRouteService")
@Transactional
public class HistoricalRouteService implements IHistoricalRouteService {
    @Autowired
    private IHistoricalRouteManager historicalRouteManager;

    @Override
    public String getCondition(String QlocatorName, String locatorName) {
        String l = "";
        if (!CommonFunction.VerdictNULL(QlocatorName)) {
            l = QlocatorName;
        }

        if (!CommonFunction.VerdictNULL(locatorName)) {
            l = locatorName;
        }
        return historicalRouteManager.getCondition(l);
    }

    @Override
    public int getHistoricalRouteListCount(String whereHQL) {
        return historicalRouteManager.getHistoricalRouteListCount(whereHQL);
    }

    @Override
    public List <HistoricalRouteView> getHistoricalRouteList(String whereHQL, int start, int pageSize) {
        return historicalRouteManager.getHistoricalRouteList(whereHQL, start, pageSize);
    }

    @Override
    public List <HistoricalRouteView> getHistoricalRouteList(String whereHQL) {
        return historicalRouteManager.getHistoricalRouteList(whereHQL);
    }

    @Override
    public String saveHistoricalRoute(HistoricalRoute historicalRoute) {
        return historicalRouteManager.saveHistoricalRoute(historicalRoute);
    }

    @Override
    public HistoricalRouteView getHistoricalRouteView(String historicalRouteId) {
        return historicalRouteManager.getHistoricalRouteView(historicalRouteId);
    }

    @JSON(serialize = false)
    public IHistoricalRouteManager getHistoricalRouteManager() { return historicalRouteManager; }

    public void setHistoricalRouteManager(IHistoricalRouteManager historicalRouteManager) { this.historicalRouteManager = historicalRouteManager; }


}
