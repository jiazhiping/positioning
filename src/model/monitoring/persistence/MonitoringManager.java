package model.monitoring.persistence;

import model.IMaYueManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 实时监控持久化层
 * Created by Myk on 2017/11/7.
 */
@Repository("monitoringManager")
public class MonitoringManager implements IMonitoringManager {
    @Autowired
    private IMaYueManager maYueManager;


    public IMaYueManager getMaYueManager() { return maYueManager; }

    public void setMaYueManager(IMaYueManager maYueManager) { this.maYueManager = maYueManager; }
}
