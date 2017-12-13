package com.java.dao;


import com.xiaowo.ContentType;

import java.util.List;

/**
 * @author answer
 *         2017/11/3
 */
public interface ContentTypeDao {

    /**
     * 获取所有的文章类型
     *
     * @return
     */
    List<ContentType> findContentTypes();

    /**
     * 根据id查询文章类型
     *
     * @param id
     * @return
     */
    ContentType findContentTypeById(Integer id);
}
