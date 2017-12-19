package model.binding.persistence;

import Common.CommonFunction;
import Common.Constants;
import model.IMaYueManager;
import model.binding.domain.Binding;
import model.binding.domain.BindingView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 绑定持久化层
 * Created by Myk on 2017/11/1.
 */
@Repository("bindingManager")
public class BindingManager implements IBindingManager {
    @Autowired
    private IMaYueManager maYueManager;

    @Override
    public String getCondition(String locatorId, String terminalId) {
        String whereHQL = "WHERE 1=1 ";

        if (!CommonFunction.VerdictNULL(locatorId)) {
            whereHQL += " AND locator_id='" + locatorId + "'";
        }

        if (!CommonFunction.VerdictNULL(terminalId)) {
            whereHQL += " AND terminal_id='" + terminalId + "'";

        }

        return whereHQL + " ORDER BY create_time";
    }

    @Override
    public int getBindingViewListCount(String whereHQL) {
        return maYueManager.getModelListCount(BindingView.class, whereHQL);
    }

    @Override
    public List <BindingView> getBindingViewList(String whereHQL, int start, int pageSize) {
        return maYueManager.getModelList(BindingView.class, whereHQL, start, pageSize);
    }

    @Override
    public List <BindingView> getBindingViewList(String whereHQL) {
        return maYueManager.getModelList(BindingView.class, whereHQL);
    }

    @Override
    public String saveBinding(Binding binding) {
        String ct = Constants.date_ymd.format(new Date());
        String maxId = maYueManager.getMaxId("t_binding", "binding_id", ct, 6,
                "where binding_id like '" + ct + "%'");
        binding.setBindingId(maxId);
        binding.setCreateTime(new Date());
        return (String) maYueManager.saveModel(binding);
    }

    @Override
    public Binding getBinding(String bindingId) {
        return maYueManager.getModel(Binding.class, bindingId);
    }

    @Override
    public void deleteBinding(Binding binding) {
        maYueManager.deleteModel(binding);
    }

    @Override
    public List <Binding> getBindingList(String whereHQL) {
        return maYueManager.getModelList(Binding.class, whereHQL);
    }

    @Override
    public List <BindingView> getBindingName(String locatorName, String terminalName) {
        String whereHQL = "WHERE 1=1 ";
        if (!CommonFunction.VerdictNULL(locatorName)) {
            whereHQL += " AND locator_name='" + locatorName + "'";
        }
        if (!CommonFunction.VerdictNULL(terminalName)) {
            whereHQL += " AND terminal_name='" + terminalName + "'";
        }
        return maYueManager.getModelList(BindingView.class, whereHQL + " ORDER BY create_time");
    }

    @Override
    public BindingView getBindingView(String bindingId) {
        return maYueManager.getModel(BindingView.class, bindingId);
    }

    public IMaYueManager getMaYueManager() { return maYueManager; }

    public void setMaYueManager(IMaYueManager maYueManager) { this.maYueManager = maYueManager; }
}
