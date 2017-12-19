package model.locator.persistence;

import Common.CommonFunction;
import Common.Constants;
import model.IMaYueManager;
import model.locator.domain.Locator;
import model.locator.domain.LocatorView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 定位器持久化层
 * Created by Myk on 2017/11/1.
 */
@Repository("locatorManager")
public class LocatorManager implements ILocatorManager {
    @Autowired
    private IMaYueManager maYueManager;


    @Override
    public String getCondition(String locatorState) {
        String whereHQL = "WHERE 1=1 ";

        if (!CommonFunction.VerdictNULL(locatorState)) {
            if ("未绑定".equals(locatorState)) {
                whereHQL += " AND locator_state=0";
            }
            if ("已绑定".equals(locatorState)) {
                whereHQL += " AND locator_state=1";
            }
        }

        return whereHQL + " ORDER BY create_time";
    }

    @Override
    public int getLocatorListCount(String whereHQL) {
        return maYueManager.getModelListCount(Locator.class, whereHQL);
    }

    @Override
    public List <Locator> getLocatorList(String whereHQL, int start, int pageSize) {
        return maYueManager.getModelList(Locator.class, whereHQL, start, pageSize);
    }

    @Override
    public List <LocatorView> getLocatorViewList(String whereHQL, int start, int pageSize) {
        return maYueManager.getModelList(LocatorView.class, whereHQL, start, pageSize);
    }

    @Override
    public List <Locator> getLocatorList(String whereHQL) {
        return maYueManager.getModelList(Locator.class, whereHQL);
    }

    @Override
    public List <Locator> getLocatorLocatorName(String locatorId, String locatorName) {
        String whereHQL = "WHERE 1=1 ";

        if (!CommonFunction.VerdictNULL(locatorId)) {
            whereHQL += " AND locator_id<>'" + locatorId + "'";
        }

        if (!CommonFunction.VerdictNULL(locatorName)) {
            whereHQL += " AND locator_name='" + locatorName + "'";
        }
        return maYueManager.getModelList(Locator.class, whereHQL + " ORDER BY create_time");
    }

    @Override
    public String saveLocator(Locator locator) {
        String ct = Constants.date_ymd.format(new Date());
        String maxId = maYueManager.getMaxId("t_locator", "locator_id", ct, 6,
                "where locator_id like '" + ct + "%'");
        locator.setLocatorId(maxId);
        locator.setLocatorState(0);
        locator.setCreateTime(new Date());
        return (String) maYueManager.saveModel(locator);
    }

    @Override
    public Locator getLocator(String locatorId) {
        return maYueManager.getModel(Locator.class, locatorId);
    }

    @Override
    public boolean updateLocator(Locator locator) {
        locator.setUpdateTime(new Date());
        return maYueManager.updateModel(locator);
    }

    @Override
    public void deleteLocator(Locator locator) {
        maYueManager.deleteModel(locator);
    }


    public IMaYueManager getMaYueManager() { return maYueManager; }

    public void setMaYueManager(IMaYueManager maYueManager) { this.maYueManager = maYueManager; }
}
