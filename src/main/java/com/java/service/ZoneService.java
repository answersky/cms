package com.java.service;

import com.xiaowo.Zone;

import java.util.List;

/**
 * @author answer
 *         2017/11/24
 */
public interface ZoneService {
    /**
     * 查询所有的区域
     *
     * @return
     */
    List<Zone> findZones();

    /**
     * 根据zone查询
     *
     * @param zone
     * @return
     */
    Zone findZoneByZone(String zone);

    /**
     * 删除地区
     *
     * @param id
     */
    void deleteZone(Integer id);

    /**
     * 新增区域
     *
     * @param zone
     * @return
     */
    int saveZone(String zone);
}
