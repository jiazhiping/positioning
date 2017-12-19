package web.historicalRoute.action;

import Common.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import model.historicalRoute.business.IHistoricalRouteService;
import model.historicalRoute.domain.HistoricalRouteView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import web.historicalRoute.form.HistoricalRouteForm;
import web.historicalRoute.form.HistoricalRouteQueryForm;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 历史轨迹Action
 * Created by Myk on 2017/11/10.
 */
@Controller("historicalRouteAction")
@Scope("prototype")
public class HistoricalRouteAction extends ActionSupport {
    private HistoricalRouteForm historicalRouteForm = new HistoricalRouteForm();
    private HistoricalRouteQueryForm historicalRouteQueryForm = new HistoricalRouteQueryForm();
    @Autowired
    private IHistoricalRouteService historicalRouteService;
    private int flag;
    private String mapLongitude;//历史位置 经度
    private String mapLatitude;//历史位置 纬度

    // 分页
    private int pageSize = 10;// 每一页显示的记录条数
    private int curPage = 0;// 当前的页码
    private int totalPage;// 记录总条数
    private int pageMaxNumber = 10;// 分页上的显示最大的页码个数，默认显示10个页码

    /**
     * 进入历史轨迹
     *
     * @return
     */
    public String historicalRouteList() {
        ActionContext act = ActionContext.getContext();
        act.getSession().put("currentTitle", "历史轨迹列表");
        act.getSession().put("currentMenuId", "02_0201");
        String whereHQL = getCondition(act);
        totalPage = historicalRouteService.getHistoricalRouteListCount(whereHQL);
        // 重新计算页码
        int count;
        if (totalPage % pageSize == 0) {
            count = totalPage / pageSize;
        } else {
            count = totalPage / pageSize + 1;
        }
        if (curPage > (count - 1)) {
            curPage--;
        }
        int start = curPage * pageSize;
        List <HistoricalRouteView> historicalRouteViews = historicalRouteService.getHistoricalRouteList(whereHQL, start, pageSize);
        act.put("historicalRouteViews", historicalRouteViews);
        return "historicalRouteList_" + Constants.STATUS_SUCCESS;
    }

    /**
     * 进入历史轨迹地图界面
     *
     * @return
     */
    public String toHistoricalRoute() {
        ActionContext act = ActionContext.getContext();
        act.getSession().put("currentTitle", "历史轨迹地图");
        act.getSession().put("currentMenuId", "02_0201");
        String whereHQL = getCondition(act);
        List <HistoricalRouteView> historicalRouteViews = historicalRouteService.getHistoricalRouteList(whereHQL);
        act.put("historicalRouteViews", historicalRouteViews);
        return "toHistoricalRoute_" + Constants.STATUS_SUCCESS;
    }

    /**
     * 进入历史定位详细
     *
     * @return
     */
    public String historicalRoute() throws InvocationTargetException, IllegalAccessException {
        HistoricalRouteView historicalRouteView = historicalRouteService.getHistoricalRouteView(historicalRouteForm.getHistoricalRouteId());
        mapLongitude = historicalRouteView.getLongitude();
        mapLatitude = historicalRouteView.getLatitude();
        flag = 0;
        System.out.println("预设位置_经纬度:" + mapLongitude + "," + mapLatitude);
        return "historicalRoute_" + Constants.STATUS_SUCCESS;
    }

    private String getCondition(ActionContext act) {
        return historicalRouteService.getCondition(historicalRouteQueryForm.getLocatorName(),historicalRouteForm.getLocatorName());
    }

    public HistoricalRouteForm getHistoricalRouteForm() { return historicalRouteForm; }

    public void setHistoricalRouteForm(HistoricalRouteForm historicalRouteForm) { this.historicalRouteForm = historicalRouteForm; }

    public HistoricalRouteQueryForm getHistoricalRouteQueryForm() { return historicalRouteQueryForm; }

    public void setHistoricalRouteQueryForm(HistoricalRouteQueryForm historicalRouteQueryForm) { this.historicalRouteQueryForm = historicalRouteQueryForm; }

    public IHistoricalRouteService getHistoricalRouteService() { return historicalRouteService; }

    public void setHistoricalRouteService(IHistoricalRouteService historicalRouteService) { this.historicalRouteService = historicalRouteService; }

    public int getFlag() { return flag; }

    public void setFlag(int flag) { this.flag = flag; }

    public String getMapLongitude() { return mapLongitude; }

    public void setMapLongitude(String mapLongitude) { this.mapLongitude = mapLongitude; }

    public String getMapLatitude() { return mapLatitude; }

    public void setMapLatitude(String mapLatitude) { this.mapLatitude = mapLatitude; }

    public int getPageSize() { return pageSize; }

    public void setPageSize(int pageSize) { this.pageSize = pageSize; }

    public int getCurPage() { return curPage; }

    public void setCurPage(int curPage) { this.curPage = curPage; }

    public int getTotalPage() { return totalPage; }

    public void setTotalPage(int totalPage) { this.totalPage = totalPage; }

    public int getPageMaxNumber() { return pageMaxNumber; }

    public void setPageMaxNumber(int pageMaxNumber) { this.pageMaxNumber = pageMaxNumber; }
}
