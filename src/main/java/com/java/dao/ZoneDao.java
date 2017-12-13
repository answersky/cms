package com.java.dao;

import com.xiaowo.Zone;

import java.util.List;

/**
 * @author answer
 *         2017/11/24
 */
public interface ZoneDao {
    /**
     * 新增地区
     *
     * @param zone
     * @return
     */
    int insertZone(Zone zone);

    /**
     * 查询所有的地区
     *
     * @return
     */
    List<Zone> findZones();

    /**
     * 根据id查询地区
     *
     * @param id
     * @return
     */
    Zone findZoneById(Integer id);

    /**
     * 更新地区
     *
     * @param zone
     * @return
     */
    int updateZone(Zone zone);

    /**
     * 删除地区
     *
     * @param id
     * @return
     */
    int deleteZone(Integer id);

    /**
     * 根据区域查询
     *
     * @param zone
     * @return
     */
    Zone findZoneByZone(String zone);
}
