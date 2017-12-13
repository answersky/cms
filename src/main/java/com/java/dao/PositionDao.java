package com.java.dao;

import com.xiaowo.Position;

import java.util.List;

/**
 * @author answer
 *         2017/11/24
 */
public interface PositionDao {
    /**
     * 新增职位
     *
     * @param position
     * @return
     */
    int insertPosition(Position position);

    /**
     * 查询所有的职位
     *
     * @return
     */
    List<Position> findPositions();

    /**
     * 根据id查询职位
     *
     * @param id
     * @return
     */
    Position findPositionById(Integer id);

    /**
     * 更新职位
     *
     * @param position
     * @return
     */
    int updatePosition(Position position);

    /**
     * 删除职位
     *
     * @param id
     * @return
     */
    int deletePosition(Integer id);

    /**
     * 查询跟当前不一样的职位名称
     *
     * @param id
     * @return
     */
    List<String> findPositionNoSame(Integer id);

    /**
     * 根据职位来查询职位信息
     *
     * @param position
     * @return
     */
    Position findPositionByPosition(String position);
}
