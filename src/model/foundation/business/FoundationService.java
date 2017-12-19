package model.foundation.business;

import model.foundation.domain.Foundation;
import model.foundation.persistence.IFoundationManager;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统默认值业务逻辑层
 * Created by Myk on 2017/11/15.
 */
@Service("foundationService")
@Transactional
public class FoundationService implements IFoundationService {
    @Autowired
    private IFoundationManager foundationManager;

    @Override
    public Foundation getFoundation() {
        return foundationManager.getFoundation();
    }

    @Override
    public boolean updateFoundation(Foundation foundation) {
        Foundation f = foundationManager.getFoundation();
        f.setThreshold(foundation.getThreshold());
        f.setBattery(foundation.getBattery());
        return foundationManager.updateFoundation(f);
    }

    @JSON(serialize = false)
    public IFoundationManager getFoundationManager() { return foundationManager; }

    public void setFoundationManager(IFoundationManager foundationManager) { this.foundationManager = foundationManager; }
}
