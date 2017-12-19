package model.deviceUpLine.persistence;

import Common.CommonFunction;
import model.IMaYueManager;
import model.deviceUpLine.domain.DeviceUpLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.Common.utils.PossUtil;

import java.util.Date;
import java.util.List;

/**
 * 已上线设备持久化层接口
 * Created by Myk on 2017/11/8.
 */
@Repository("deviceUpLineManager")
public class DeviceUpLineManager implements IDeviceUpLineManager {
    @Autowired
    private IMaYueManager maYueManager;

    @Override
    public List <DeviceUpLine> getDeviceUpLineName(String locatorName) {
        String whereHQL = "WHERE 1=1 ";
        if (!CommonFunction.VerdictNULL(locatorName)) {
            whereHQL += " AND locator_name='" + locatorName + "'";
        }
        return maYueManager.getModelList(DeviceUpLine.class, whereHQL + " ORDER BY create_time");
    }

    @Override
    public String saveDeviceUpLine(DeviceUpLine deviceUpLine) {
//        String ct = Constants.date_ymd.format(new Date());
//        String maxId = maYueManager.getMaxId("t_deviceUpLine", "device_id", ct, 6,
//                "where device_id like '" + ct + "%'");
//        deviceUpLine.setDeviceId(maxId);
        deviceUpLine.setDeviceId(PossUtil.getAesKey());
        deviceUpLine.setDeviceBattery("");//定位器电量
        deviceUpLine.setDeviceTime(new Date());
        deviceUpLine.setCreateTime(new Date());
        return (String) maYueManager.saveModel(deviceUpLine);
    }

    @Override
    public boolean updateDeviceUpLine(DeviceUpLine deviceUpLine) {
        deviceUpLine.setDeviceTime(new Date());
        deviceUpLine.setUpdateTime(new Date());
        return maYueManager.updateModel(deviceUpLine);
    }

    @Override
    public DeviceUpLine getDeviceUpLine(String deviceId) {
        return maYueManager.getModel(DeviceUpLine.class, deviceId);
    }

    @Override
    public String getCondition(String locatorName, String terminalName) {
        String whereHQL = "WHERE 1=1 ";
        if (!CommonFunction.VerdictNULL(locatorName)) {
            whereHQL += " AND locator_name='" + locatorName + "'";
        }
        if (!CommonFunction.VerdictNULL(terminalName)) {
            whereHQL += " AND terminal_name='" + terminalName + "'";
        }
        return whereHQL + "ORDER BY create_time";
    }

    @Override
    public List <DeviceUpLine> getDeviceUpLineList(String whereHQL) {
        return maYueManager.getModelList(DeviceUpLine.class, whereHQL);
    }

    @Override
    public int getDeviceUpLineListCount(String whereHQL) {
        return maYueManager.getModelListCount(DeviceUpLine.class, whereHQL);
    }

    @Override
    public List <DeviceUpLine> getDeviceUpLineList(String whereHQL, int start, int pageSize) {
        return maYueManager.getModelList(DeviceUpLine.class, whereHQL, start, pageSize);
    }

    @Override
    public boolean updateFrequency(DeviceUpLine deviceUpLine) {
        return maYueManager.updateModel(deviceUpLine);
    }

    @Override
    public boolean updateDeviceAlarm(DeviceUpLine deviceUpLine) {
        deviceUpLine.setUpdateTime(new Date());
        return maYueManager.updateModel(deviceUpLine);
    }

    public IMaYueManager getMaYueManager() { return maYueManager; }

    public void setMaYueManager(IMaYueManager maYueManager) { this.maYueManager = maYueManager; }


}
