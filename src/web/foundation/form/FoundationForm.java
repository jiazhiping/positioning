package web.foundation.form;

import java.util.Date;

/**
 * 系统默认值实体类
 * Created by Myk on 2017/11/15.
 */
public class FoundationForm {
    private String systemId;//系统编号 001
    private String threshold;//告警阈值
    private String battery;//告警电量
    private Date CreateTime;// 创建时间
    private Date UpdateTime;// 修改时间

    public String getSystemId() { return systemId; }

    public void setSystemId(String systemId) { this.systemId = systemId; }

    public String getThreshold() { return threshold; }

    public void setThreshold(String threshold) { this.threshold = threshold; }

    public String getBattery() { return battery; }

    public void setBattery(String battery) { this.battery = battery; }

    public Date getCreateTime() { return CreateTime; }

    public void setCreateTime(Date createTime) {CreateTime = createTime; }

    public Date getUpdateTime() { return UpdateTime; }

    public void setUpdateTime(Date updateTime) {UpdateTime = updateTime; }
}
