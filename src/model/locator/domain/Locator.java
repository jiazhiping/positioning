package model.locator.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * 定位器表
 * Created by Myk on 2017/11/1.
 */
@Entity(name = "Locator")
@Table(name = "t_locator")
public class Locator {
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

    @Id
    @Column(name = "locator_id", length = 24, nullable = false)
    public String getLocatorId() { return locatorId; }

    @Column(name = "locator_name", length = 24)
    public String getLocatorName() { return locatorName; }

    @Column(name = "locator_position_lng", length = 64)
    public String getLocatorPositionLng() { return locatorPositionLng; }

    @Column(name = "locator_position_lat", length = 64)
    public String getLocatorPositionLat() { return locatorPositionLat; }

    @Column(name = "locator_state", length = 24)
    public int getLocatorState() { return locatorState; }

    @Column(name = "locator_frequency", length = 24)
    public String getLocatorFrequency() { return locatorFrequency; }

    @Column(name = "create_code", length = 32)
    public String getCreateCode() {
        return CreateCode;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    public Date getCreateTime() {
        return CreateTime;
    }

    @Column(name = "update_code", length = 32)
    public String getUpdateCode() {
        return UpdateCode;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    public Date getUpdateTime() { return UpdateTime; }

    public void setLocatorId(String locatorId) { this.locatorId = locatorId; }

    public void setLocatorName(String locatorName) { this.locatorName = locatorName; }

    public void setLocatorPositionLng(String locatorPositionLng) { this.locatorPositionLng = locatorPositionLng; }

    public void setLocatorPositionLat(String locatorPositionLat) { this.locatorPositionLat = locatorPositionLat; }

    public void setLocatorState(int locatorState) { this.locatorState = locatorState; }

    public void setLocatorFrequency(String locatorFrequency) { this.locatorFrequency = locatorFrequency; }

    public void setCreateCode(String createCode) {CreateCode = createCode; }

    public void setCreateTime(Date createTime) {CreateTime = createTime; }

    public void setUpdateCode(String updateCode) {UpdateCode = updateCode; }

    public void setUpdateTime(Date updateTime) {UpdateTime = updateTime; }
}
