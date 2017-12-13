package com.java.service;

import com.xiaowo.Recruit;

import java.util.List;

/**
 * @author answer
 *         2017/11/24
 */
public interface RecruitService {
    /**
     * 查询所有的招聘信息
     *
     * @return
     */
    List<Recruit> findAllRecruit();

    /**
     * 根据id查询信息
     *
     * @param id
     * @return
     */
    Recruit findRecruitById(Integer id);

    /**
     * 新增招聘信息
     *
     * @param departmentId
     * @param positionId
     * @param zoneId
     * @param title
     * @param duty
     * @param jobRequire
     * @param type
     * @return
     */
    int saveRecruit(Integer departmentId, Integer positionId, Integer zoneId, String title, String duty, String jobRequire, String type);

    /**
     * 删除招聘信息
     *
     * @param id
     */
    void deleteRecruit(Integer id);

    /**
     * 更新招聘信息
     *
     * @param id
     * @param departmentId
     * @param positionId
     * @param zoneId
     * @param title
     * @param duty
     * @param jobRequire
     * @param type
     * @return
     */
    int updateRecruit(Integer id, Integer departmentId, Integer positionId, Integer zoneId, String title, String duty, String jobRequire, String type);

    /**
     * 发布招聘信息
     *
     * @param list
     */
    void publish(List<Integer> list);
}
