package model.monitoring.business;

import model.monitoring.persistence.IMonitoringManager;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 实时监控业务逻辑层
 * Created by Myk on 2017/11/7.
 */
@Service("monitoringService")
@Transactional
public class MonitoringService implements IMonitoringService {
    @Autowired
    private IMonitoringManager monitoringManager;


    @JSON(serialize = false)
    public IMonitoringManager getMonitoringManager() { return monitoringManager; }

    public void setMonitoringManager(IMonitoringManager monitoringManager) { this.monitoringManager = monitoringManager; }
}
