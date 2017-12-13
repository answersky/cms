package com.java.dao;

import com.xiaowo.ApplyLog;

import java.util.List;

/**
 * @author answer
 *         2017/12/4
 */
public interface ApplyLogDao {
    /**
     * 查询着陆页/h5页面的申请统计
     *
     * @return
     */
    List<ApplyLog> findCount();

    /**
     * 根据来源页面查询
     *
     * @param resource
     * @return
     */
    List<ApplyLog> findApplyLogByResource(String resource);
}
