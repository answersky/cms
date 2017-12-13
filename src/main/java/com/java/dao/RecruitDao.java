package com.java.dao;

import com.xiaowo.Recruit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author answer
 *         2017/11/24
 */
public interface RecruitDao {
    /**
     * 新增招聘信息
     *
     * @param recruit
     * @return
     */
    int insertRecruit(Recruit recruit);

    /**
     * 查询有效招聘信息
     *
     * @return
     */
    List<Recruit> findRecruit();

    /**
     * 根据id查询招聘信息
     *
     * @param id
     * @return
     */
    Recruit findRecruitById(Integer id);

    /**
     * 更新招聘信息
     *
     * @param recruit
     * @return
     */
    int updateRecruit(Recruit recruit);

    /**
     * 发布招聘信息
     *
     * @param ids
     * @return
     */
    int publishRecruit(@Param("ids") List<Integer> ids);

    /**
     * 删除招聘信息
     *
     * @param id
     * @return
     */
    int deleteRecruit(Integer id);

    /**
     * 根据部门id，职位id，区域id查询信息
     *
     * @param departmentId
     * @param positionId
     * @param zoneId
     * @return
     */
    Recruit findBydepartIdAndPosIdAndZoneId(@Param("departmentId") Integer departmentId, @Param("positionId") Integer positionId, @Param("zoneId") Integer zoneId, @Param("type") String type);
}
