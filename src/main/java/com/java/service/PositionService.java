package com.java.service;

import com.xiaowo.Position;

import java.util.List;

/**
 * @author answer
 *         2017/11/24
 */
public interface PositionService {
    /**
     * 查询所有的部门
     *
     * @return
     */
    List<Position> findPositions();

    /**
     * 删除部门
     *
     * @param id
     */
    void deletePosition(Integer id);

    /**
     * 根据id查询部门信息
     *
     * @param id
     * @return
     */
    Position findPositionById(Integer id);

    /**
     * 更新部门
     *
     * @param id
     * @param position
     * @param remark
     * @return
     */
    int updatePosition(Integer id, String position, String remark);

    /**
     * 新增部门
     *
     * @param position
     * @param remark
     * @return
     */
    int savePosition(String position, String remark);
}
