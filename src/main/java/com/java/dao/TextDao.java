package com.java.dao;


import com.xiaowo.Text;

/**
 * @author answer
 *         2017/11/3
 */
public interface TextDao {
    /**
     * 新增文章
     *
     * @param text
     */
    void insertText(Text text);

    /**
     * 根据id查询文章
     *
     * @param id
     * @return
     */
    Text findTextById(Integer id);

    /**
     * 更新文章
     *
     * @param text
     */
    void updateText(Text text);
}
