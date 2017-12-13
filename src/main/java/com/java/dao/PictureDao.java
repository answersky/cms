package com.java.dao;


import com.xiaowo.Picture;

/**
 * @author answer
 *         2017/11/3
 */
public interface PictureDao {
    /**
     * 新增图片
     *
     * @param picture
     */
    void insertPicture(Picture picture);

    /**
     * 根据id查询图片
     *
     * @param id
     * @return
     */
    Picture findPictureById(Integer id);

    /**
     * 更新图片
     *
     * @param picture
     */
    void updatePicture(Picture picture);
}
