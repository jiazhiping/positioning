package model.binding.persistence;

import model.binding.domain.Binding;
import model.binding.domain.BindingView;

import java.util.List;

/**
 * 绑定持久化层接口
 * Created by Myk on 2017/11/1.
 */
public interface IBindingManager {

    /**
     * 关系查询条件
     *
     * @param locatorId  定位器Id
     * @param terminalId POS终端Id
     * @return
     */
    String getCondition(String locatorId, String terminalId);

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
     * 查询关系记录列表
     *
     * @param whereHQL 查询条件
     * @return
     */
    List <BindingView> getBindingViewList(String whereHQL);


    /**
     * 绑定定位器与POS终端
     *
     * @param binding 绑定表
     * @return
     */
    String saveBinding(Binding binding);

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
     * 根据条件查询绑定信息列表
     *
     * @param whereHQL 查询条件
     * @return
     */
    List <Binding> getBindingList(String whereHQL);

    /**
     * 判断此定位是否已在系统中存在
     *
     * @param locatorName  定位器序列号
     * @param terminalName 终端序列号
     * @return
     */
    List <BindingView> getBindingName(String locatorName, String terminalName);

    /**
     * 根据绑定Id查询
     *
     * @param bindingId
     * @return
     */
    BindingView getBindingView(String bindingId);
}
