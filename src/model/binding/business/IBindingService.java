package model.binding.business;

import model.binding.domain.Binding;
import model.binding.domain.BindingView;

import java.util.List;

/**
 * 绑定业务逻辑层接口
 * Created by Myk on 2017/11/1.
 */
public interface IBindingService {

    /**
     * 关系查询条件
     *
     * @return
     */
    String getCondition();

    /**
     * 查询关系记录列表条数（视图）
     *
     * @param whereHQL 查询条件
     * @return
     */
    int getBindingViewListCount(String whereHQL);


    /**
     * 查询关系记录列表（分页）（视图）
     *
     * @param whereHQL 查询条件
     * @param start    开始条数
     * @param pageSize 条数
     * @return
     */
    List <BindingView> getBindingViewList(String whereHQL, int start, int pageSize);

    /**
     * 绑定定位器与POS终端
     *
     * @param binding 绑定表
     * @return
     */
    boolean saveBinding(Binding binding);

    /**
     * 查询绑定信息
     *
     * @param bindingId 绑定Id
     * @return
     */
    Binding getBinding(String bindingId);

    /**
     * 删除绑定信息
     *
     * @param binding 绑定表
     */
    void deleteBinding(Binding binding);

    /**
     * 查询绑定状态(已绑定：BindingView||未绑定：null)
     *
     * @param locatorId  定位器Id
     * @param terminalId POS终端Id
     * @return
     */
    BindingView getBindingViewStatus(String locatorId, String terminalId);

    /**
     * 查询绑定状态(已绑定：true||未绑定：false)
     *
     * @param locatorId  定位器Id
     * @param terminalId POS终端Id
     * @return
     */
    boolean getBindingStatus(String locatorId, String terminalId);

    /**
     * 判断此定位是否已在系统中存在
     *
     * @param locatorName  定位器序列号
     * @param terminalName 终端序列号
     * @return
     */
    BindingView getBindingName(String locatorName, String terminalName);

    /**
     * 根据绑定Id查询
     * @param bindingId
     * @return
     */
    BindingView getBindingView(String bindingId);
}
