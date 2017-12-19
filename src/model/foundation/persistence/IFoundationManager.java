package model.foundation.persistence;

import model.foundation.domain.Foundation;

/**
 * 系统默认值持久化层接口
 * Created by Myk on 2017/11/15.
 */
public interface IFoundationManager {
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
