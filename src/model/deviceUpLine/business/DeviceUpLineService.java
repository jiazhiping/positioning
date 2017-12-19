package model.deviceUpLine.business;

import model.deviceUpLine.domain.DeviceUpLine;
import model.deviceUpLine.persistence.IDeviceUpLineManager;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 已上线设备业务逻辑层
 * Created by Myk on 2017/11/8.
 */
@Service("deviceUpLineService")
@Transactional
public class DeviceUpLineService implements IDeviceUpLineService {
    @Autowired
    private IDeviceUpLineManager deviceUpLineManager;

    @Override
    public DeviceUpLine getDeviceUpLineName(String locatorName) {
        List <DeviceUpLine> deviceUpLines = deviceUpLineManager.getDeviceUpLineName(locatorName);
        if (deviceUpLines.size() > 0) {
            return deviceUpLines.get(0);
        } else {
            return null;
        }

    }

    @Override
    public String saveDeviceUpLine(DeviceUpLine deviceUpLine) {
        return deviceUpLineManager.saveDeviceUpLine(deviceUpLine);
    }

    @Override
    public boolean updateDeviceUpLine(DeviceUpLine deviceUpLine) {
        return deviceUpLineManager.updateDeviceUpLine(deviceUpLine);
    }

    @Override
    public DeviceUpLine getDeviceUpLine(String deviceId) {
        return deviceUpLineManager.getDeviceUpLine(deviceId);
    }

    @Override
    public String getCondition(String locatorName, String terminalName) {
        return deviceUpLineManager.getCondition(locatorName,terminalName);
    }

    @Override
    public List <DeviceUpLine> getDeviceUpLineList(String whereHQL) {
        return deviceUpLineManager.getDeviceUpLineList(whereHQL);
    }

    @Override
    public int getDeviceUpLineListCount(String whereHQL) {
        return deviceUpLineManager.getDeviceUpLineListCount(whereHQL);
    }

    @Override
    public List <DeviceUpLine> getDeviceUpLineList(String whereHQL, int start, int pageSize) {
        return deviceUpLineManager.getDeviceUpLineList(whereHQL, start, pageSize);
    }

    @Override
    public boolean updateFrequency(DeviceUpLine deviceUpLine) {
        DeviceUpLine d = deviceUpLineManager.getDeviceUpLine(deviceUpLine.getDeviceId());
        d.setDeviceFrequency(deviceUpLine.getDeviceFrequency());
        return deviceUpLineManager.updateFrequency(d);
    }

    @Override
    public boolean updateDeviceAlarm(DeviceUpLine deviceUpLine) {
        return deviceUpLineManager.updateDeviceAlarm(deviceUpLine);
    }

    @JSON(serialize = false)
    public IDeviceUpLineManager getDeviceUpLineManager() { return deviceUpLineManager; }

    public void setDeviceUpLineManager(IDeviceUpLineManager deviceUpLineManager) { this.deviceUpLineManager = deviceUpLineManager; }


}
