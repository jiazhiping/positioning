package model.binding.business;

import Common.CommonFunction;
import model.binding.domain.Binding;
import model.binding.domain.BindingView;
import model.binding.persistence.IBindingManager;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 绑定业务逻辑层
 * Created by Myk on 2017/11/1.
 */
@Service("bindingService")
@Transactional
public class BindingService implements IBindingService {
    @Autowired
    private IBindingManager bindingManager;

    @Override
    public String getCondition() {
        return bindingManager.getCondition(null, null);
    }

    @Override
    public int getBindingViewListCount(String whereHQL) {
        return bindingManager.getBindingViewListCount(whereHQL);
    }

    @Override
    public List <BindingView> getBindingViewList(String whereHQL, int start, int pageSize) {
        return bindingManager.getBindingViewList(whereHQL, start, pageSize);
    }

    @Override
    public boolean saveBinding(Binding binding) {
        String bindingId = bindingManager.saveBinding(binding);
        if (!CommonFunction.VerdictNULL(bindingId)) {
            return true;//成功
        } else {
            return false;//失败
        }
    }

    @Override
    public Binding getBinding(String bindingId) {
        return bindingManager.getBinding(bindingId);
    }

    @Override
    public void deleteBinding(Binding binding) {
        bindingManager.deleteBinding(binding);
    }

    @Override
    public BindingView getBindingViewStatus(String locatorId, String terminalId) {
        List <BindingView> bindings = bindingManager.getBindingViewList(bindingManager.getCondition(locatorId, terminalId));
        if (bindings.size() > 0) {
            return bindings.get(0);//已绑定
        } else {
            return null;//未绑定
        }
    }

    @Override
    public boolean getBindingStatus(String locatorId, String terminalId) {
        List <BindingView> bindings = bindingManager.getBindingViewList(bindingManager.getCondition(locatorId, terminalId));
        if (bindings.size() > 0) {
            return true;//已绑定
        } else {
            return false;//未绑定
        }
    }

    @Override
    public BindingView getBindingName(String locatorName, String terminalName) {
        List <BindingView> bindingViews = bindingManager.getBindingName(locatorName, terminalName);
        if (bindingViews.size() > 0) {
            return bindingViews.get(0);
        } else {
            return null;
        }
    }

    @Override
    public BindingView getBindingView(String bindingId) {
        return bindingManager.getBindingView(bindingId);
    }

    @JSON(serialize = false)
    public IBindingManager getBindingManager() { return bindingManager; }

    public void setBindingManager(IBindingManager bindingManager) { this.bindingManager = bindingManager; }
}
