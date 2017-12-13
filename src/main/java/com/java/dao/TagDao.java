package com.java.dao;

import com.xiaowo.Tag;
import org.apache.ibatis.annotations.Param;

/**
 * @author answer
 *         2017/11/3
 */
public interface TagDao {
    /**
     * 新增tag
     *
     * @param tag
     */
    void insertTag(Tag tag);

    /**
     * 根据tag查询tag标签
     *
     * @param tag
     * @return
     */
    Tag findTagByTag(String tag);

    /**
     * 修改标签的引用次数
     *
     * @param referenceCount
     * @param id
     */
    void updateTagReferenceCount(@Param("referenceCount") Integer referenceCount, @Param("id") Integer id);

    /**
     * 根据id查询标签
     *
     * @param i
     * @return
     */
    Tag findTagById(Integer i);
}
