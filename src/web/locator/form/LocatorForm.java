package web.locator.form;

/**
 * 定位器实体类
 * Created by Myk on 2017/11/1.
 */
public class LocatorForm {
    private String locatorId; // 定位器Id 主键
    private String locatorName; // 定位器序列号
    private String locatorPositionLng; // 预设位置:经度
    private String locatorPositionLat; // 预设位置:纬度
    private int locatorState; // 绑定状态
    private String locatorFrequency;//上报频率

    private String locatorIds;//定位器组

    private String bindingId; // 绑定Id
    private String deviceId; // 设备Id

    public String getLocatorId() { return locatorId; }

    public void setLocatorId(String locatorId) { this.locatorId = locatorId; }

    public String getLocatorName() { return locatorName; }

    public void setLocatorName(String locatorName) { this.locatorName = locatorName; }

    public String getLocatorPositionLng() { return locatorPositionLng; }

    public void setLocatorPositionLng(String locatorPositionLng) { this.locatorPositionLng = locatorPositionLng; }

    public String getLocatorPositionLat() { return locatorPositionLat; }

    public void setLocatorPositionLat(String locatorPositionLat) { this.locatorPositionLat = locatorPositionLat; }

    public int getLocatorState() { return locatorState; }

    public void setLocatorState(int locatorState) { this.locatorState = locatorState; }

    public String getLocatorIds() { return locatorIds; }

    public void setLocatorIds(String locatorIds) { this.locatorIds = locatorIds; }

    public String getLocatorFrequency() { return locatorFrequency; }

    public void setLocatorFrequency(String locatorFrequency) { this.locatorFrequency = locatorFrequency; }

    public String getBindingId() { return bindingId; }

    public void setBindingId(String bindingId) { this.bindingId = bindingId; }

    public String getDeviceId() { return deviceId; }

    public void setDeviceId(String deviceId) { this.deviceId = deviceId; }
}
