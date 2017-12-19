package model.foundation.persistence;

import model.IMaYueManager;
import model.foundation.domain.Foundation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * 系统默认值持久化层
 * Created by Myk on 2017/11/15.
 */
@Repository("foundationManager")
public class FoundationManager implements IFoundationManager {
    @Autowired
    private IMaYueManager maYueManager;

    @Override
    public Foundation getFoundation() {
        return maYueManager.getModel(Foundation.class, "001");
    }

    @Override
    public boolean updateFoundation(Foundation foundation) {
        foundation.setUpdateTime(new Date());
        return maYueManager.updateModel(foundation);
    }

    public IMaYueManager getMaYueManager() { return maYueManager; }

    public void setMaYueManager(IMaYueManager maYueManager) { this.maYueManager = maYueManager; }


}
