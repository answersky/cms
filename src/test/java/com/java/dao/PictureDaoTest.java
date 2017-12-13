package com.java.dao;

import com.java.BaseTest;
import com.xiaowo.Picture;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author answer
 *         2017/11/3
 */
public class PictureDaoTest extends BaseTest {
    @Resource
    private PictureDao pictureDao;

    @Test
    public void insertPicture() throws Exception {
        Picture picture = new Picture();
        picture.setPath("/home/java/img");
        picture.setDescription("banner图");
        picture.setUrl("http://www.baidu.com");
        pictureDao.insertPicture(picture);
        System.out.println(picture.getId());
    }

    @Test
    public void findPictureById() throws Exception {
        Picture picture = pictureDao.findPictureById(2);
        System.out.println(picture);
    }

}