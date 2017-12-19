package model.locator.business;

import Common.CommonFunction;
import model.locator.domain.Locator;
import model.locator.domain.LocatorView;
import model.locator.persistence.ILocatorManager;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 定位器业务逻辑层
 * Created by Myk on 2017/11/1.
 */
@Service("locatorService")
@Transactional
public class LocatorService implements ILocatorService {
    @Autowired
    private ILocatorManager locatorManager;

    @Override
    public String getCondition(String locatorState) {
        return locatorManager.getCondition(locatorState);
    }

    @Override
    public int getLocatorListCount(String whereHQL) {
        return locatorManager.getLocatorListCount(whereHQL);
    }

    @Override
    public List <Locator> getLocatorList(String whereHQL, int start, int pageSize) {
        return locatorManager.getLocatorList(whereHQL, start, pageSize);
    }

    @Override
    public List <LocatorView> getLocatorViewList(String whereHQL, int start, int pageSize) {
        return locatorManager.getLocatorViewList(whereHQL, start, pageSize);
    }

    @Override
    public List <Locator> getLocatorList(String whereHQL) {
        return locatorManager.getLocatorList(whereHQL);
    }

    @Override
    public Locator getLocatorLocatorName(String locatorId, String locatorName) {
        List <Locator> locators = locatorManager.getLocatorLocatorName(locatorId, locatorName);
        if (locators.size() > 0) {
            return locators.get(0);//已存在
        } else {
            return null;//不存在
        }
    }

    @Override
    public boolean saveLocator(Locator locator) {
        String locatorId = locatorManager.saveLocator(locator);
        if (!CommonFunction.VerdictNULL(locatorId)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Locator getLocator(String locatorId) {
        return locatorManager.getLocator(locatorId);
    }

    @Override
    public boolean updateLocator(Locator locator) {
        Locator l = locatorManager.getLocator(locator.getLocatorId());
        l.setLocatorName(locator.getLocatorName());
        l.setLocatorPositionLng(locator.getLocatorPositionLng());
        l.setLocatorPositionLat(locator.getLocatorPositionLat());
        l.setLocatorState(locator.getLocatorState());
        l.setLocatorFrequency(locator.getLocatorFrequency());
        l.setUpdateCode(locator.getUpdateCode());
        return locatorManager.updateLocator(l);
    }

    @Override
    public void deleteLocator(Locator locator) {
        locatorManager.deleteLocator(locator);
    }


    @JSON(serialize = false)
    public ILocatorManager getLocatorManager() { return locatorManager; }

    public void setLocatorManager(ILocatorManager locatorManager) { this.locatorManager = locatorManager; }
}
