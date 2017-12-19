package model.locator.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 定位器视图
 * Created by Myk on 2017/11/20.
 */
@Entity(name = "LocatorView")
@Table(name = "v_locator")
public class LocatorView {
    private String locatorId; // 定位器Id 主键
    private String locatorName; // 定位器序列号
    private String locatorPositionLng; // 预设位置:经度
    private String locatorPositionLat; // 预设位置:纬度
    private int locatorState; // 绑定状态 未绑定：0  已绑定：1
    private String locatorFrequency;//上报频率
    private String CreateCode;// 创建人员
    private Date CreateTime;// 创建时间
    private String UpdateCode;// 修改人员
    private Date UpdateTime;// 修改时间

    private String bindingId; // 绑定Id
    private String deviceId; // 设备Id

    @Id
    @Column(name = "locator_id")
    public String getLocatorId() { return locatorId; }

    public void setLocatorId(String locatorId) { this.locatorId = locatorId; }

    @Column(name = "locator_name")
    public String getLocatorName() { return locatorName; }

    public void setLocatorName(String locatorName) { this.locatorName = locatorName; }

    @Column(name = "locator_position_lng")
    public String getLocatorPositionLng() { return locatorPositionLng; }

    public void setLocatorPositionLng(String locatorPositionLng) { this.locatorPositionLng = locatorPositionLng; }

    @Column(name = "locator_position_lat")
    public String getLocatorPositionLat() { return locatorPositionLat; }

    public void setLocatorPositionLat(String locatorPositionLat) { this.locatorPositionLat = locatorPositionLat; }

    @Column(name = "locator_state")
    public int getLocatorState() { return locatorState; }

    public void setLocatorState(int locatorState) { this.locatorState = locatorState; }

    @Column(name = "locator_frequency")
    public String getLocatorFrequency() { return locatorFrequency; }

    public void setLocatorFrequency(String locatorFrequency) { this.locatorFrequency = locatorFrequency; }

    @Column(name = "create_code")
    public String getCreateCode() { return CreateCode; }

    public void setCreateCode(String createCode) {CreateCode = createCode; }

    @Column(name = "create_time")
    public Date getCreateTime() { return CreateTime; }

    public void setCreateTime(Date createTime) {CreateTime = createTime; }

    @Column(name = "update_code")
    public String getUpdateCode() { return UpdateCode; }

    public void setUpdateCode(String updateCode) {UpdateCode = updateCode; }

    @Column(name = "update_time")
    public Date getUpdateTime() { return UpdateTime; }

    public void setUpdateTime(Date updateTime) {UpdateTime = updateTime; }

    @Column(name = "binding_id")
    public String getBindingId() { return bindingId; }

    public void setBindingId(String bindingId) { this.bindingId = bindingId; }

    @Column(name = "device_id")
    public String getDeviceId() { return deviceId; }

    public void setDeviceId(String deviceId) { this.deviceId = deviceId; }
}
