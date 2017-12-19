package model.deviceUpLine.business;

import model.deviceUpLine.domain.DeviceUpLine;

import java.util.List;

/**
 * 已上线设备业务逻辑层接口
 * Created by Myk on 2017/11/8.
 */
public interface IDeviceUpLineService {
    /**
     * 查询该定位器是否已上线
     *
     * @param locatorName 定位器序列号
     * @return
     */
    DeviceUpLine getDeviceUpLineName(String locatorName);

    /**
     * 保存上线设备
     *
     * @param deviceUpLine
     */
    String saveDeviceUpLine(DeviceUpLine deviceUpLine);

    /**
     * 修改上线设备信息
     *
     * @param deviceUpLine
     * @return
     */
    boolean updateDeviceUpLine(DeviceUpLine deviceUpLine);

    /**
     * 根据Id查询设备
     *
     * @param deviceId 设备Id
     * @return
     */
    DeviceUpLine getDeviceUpLine(String deviceId);

    /**
     * 已上线设备查询条件
     *
     * @param locatorName  定位器序列号
     * @param terminalName POS终端序列号
     * @return
     */
    String getCondition(String locatorName, String terminalName);

    /**
     * 根据条件获取所有已上线设备
     *
     * @param whereHQL 查询条件
     * @return
     */
    List <DeviceUpLine> getDeviceUpLineList(String whereHQL);

    /**
     * 查询在线设备记录条数
     *
     * @param whereHQL
     * @return
     */
    int getDeviceUpLineListCount(String whereHQL);

    /**
     * 根据条件查询在线设备（分页）
     *
     * @param whereHQL
     * @param start
     * @param pageSize
     * @return
     */
    List <DeviceUpLine> getDeviceUpLineList(String whereHQL, int start, int pageSize);

    /**
     * 修改上报频率
     *
     * @param deviceUpLine
     * @return
     */
    boolean updateFrequency(DeviceUpLine deviceUpLine);

    /**
     * 修改设备状态
     *
     * @param deviceUpLine
     * @return
     */
    boolean updateDeviceAlarm(DeviceUpLine deviceUpLine);
}
