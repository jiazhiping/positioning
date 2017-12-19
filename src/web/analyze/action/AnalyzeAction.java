package web.analyze.action;

import Common.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import model.monitoringRecord.business.IMonitoringRecordService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 分析Action
 * Created by Myk on 2017/11/19 0019.
 */
public class AnalyzeAction extends ActionSupport {
    int monitoringType0 = 0;
    int monitoringType1 = 0;
    int monitoringType2 = 0;
    int monitoringType3 = 0;
    int monitoringType4 = 0;
    int monitoringType5 = 0;
    int monitoringType6 = 0;

    @Autowired
    private IMonitoringRecordService monitoringRecordService;

    /**
     * 进入告警类型分析图界面
     *
     * @return
     */
    public String analyzeList() {
        ActionContext act = ActionContext.getContext();
        act.getSession().put("currentTitle", "告警类型统计");
        act.getSession().put("currentMenuId", "05_0501");
        String monitoringType0HQL = analyzeCondition("已知定位器上线", 0);
        monitoringType0 = monitoringRecordService.getMonitoringRecordListCount(monitoringType0HQL);
        String monitoringType1HQL = analyzeCondition("未知定位器上线", 0);
        monitoringType1 = monitoringRecordService.getMonitoringRecordListCount(monitoringType1HQL);
        String monitoringType2HQL = analyzeCondition("POS终端信息不匹配", 0);
        monitoringType2 = monitoringRecordService.getMonitoringRecordListCount(monitoringType2HQL);
        String monitoringType3HQL = analyzeCondition("非法POS终端信息", 0);
        monitoringType3 = monitoringRecordService.getMonitoringRecordListCount(monitoringType3HQL);
        String monitoringType4HQL = analyzeCondition("POS终端信息未绑定", 0);
        monitoringType4 = monitoringRecordService.getMonitoringRecordListCount(monitoringType4HQL);
        String monitoringType5HQL = analyzeCondition("定位器电量低", 0);
        monitoringType5 = monitoringRecordService.getMonitoringRecordListCount(monitoringType5HQL);
        String monitoringType6HQL = analyzeCondition("定位器位置异常", 0);
        monitoringType6 = monitoringRecordService.getMonitoringRecordListCount(monitoringType6HQL);
        return "analyzeList_" + Constants.STATUS_SUCCESS;
    }

    /**
     * 进入告警级别分析图界面
     *
     * @return
     */
    public String analyzeToList() {
        ActionContext act = ActionContext.getContext();
        act.getSession().put("currentTitle", "告警级别统计");
        act.getSession().put("currentMenuId", "05_0502");
        String monitoringType0HQL = analyzeCondition("0", 1);//提示
        monitoringType0 = monitoringRecordService.getMonitoringRecordListCount(monitoringType0HQL);
        String monitoringType1HQL = analyzeCondition("1", 1);//次要
        monitoringType1 = monitoringRecordService.getMonitoringRecordListCount(monitoringType1HQL);
        String monitoringType2HQL = analyzeCondition("2", 1);//主要
        monitoringType2 = monitoringRecordService.getMonitoringRecordListCount(monitoringType2HQL);
        String monitoringType3HQL = analyzeCondition("3", 1);//严重
        monitoringType3 = monitoringRecordService.getMonitoringRecordListCount(monitoringType3HQL);
        return "analyzeToList_" + Constants.STATUS_SUCCESS;
    }

    private String analyzeCondition(String Type_Grade, int Type) {
        return monitoringRecordService.analyzeCondition(Type_Grade, Type);
    }

    public IMonitoringRecordService getMonitoringRecordService() { return monitoringRecordService; }

    public void setMonitoringRecordService(IMonitoringRecordService monitoringRecordService) { this.monitoringRecordService = monitoringRecordService; }

    public int getMonitoringType0() { return monitoringType0; }

    public void setMonitoringType0(int monitoringType0) { this.monitoringType0 = monitoringType0; }

    public int getMonitoringType1() { return monitoringType1; }

    public void setMonitoringType1(int monitoringType1) { this.monitoringType1 = monitoringType1; }

    public int getMonitoringType2() { return monitoringType2; }

    public void setMonitoringType2(int monitoringType2) { this.monitoringType2 = monitoringType2; }

    public int getMonitoringType3() { return monitoringType3; }

    public void setMonitoringType3(int monitoringType3) { this.monitoringType3 = monitoringType3; }

    public int getMonitoringType4() { return monitoringType4; }

    public void setMonitoringType4(int monitoringType4) { this.monitoringType4 = monitoringType4; }

    public int getMonitoringType5() { return monitoringType5; }

    public void setMonitoringType5(int monitoringType5) { this.monitoringType5 = monitoringType5; }

    public int getMonitoringType6() { return monitoringType6; }

    public void setMonitoringType6(int monitoringType6) { this.monitoringType6 = monitoringType6; }
}
