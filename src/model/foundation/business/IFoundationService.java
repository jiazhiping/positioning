package model.foundation.business;

import model.foundation.domain.Foundation;

/**
 * 系统默认值业务逻辑层接口
 * Created by Myk on 2017/11/15.
 */
public interface IFoundationService {

    /**
     * 获取当前系统默认值
     *
     * @return
     */
    Foundation getFoundation();

    /**
     * 修改全局阈值
     *
     * @param foundation
     * @return
     */
    boolean updateFoundation(Foundation foundation);
}
