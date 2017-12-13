package com.java.service.impl;

import com.java.dao.PictureDao;
import com.java.service.PicService;
import com.xiaowo.Picture;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author answer
 *         2017/11/6
 */
@Service
public class PictureServiceImpl implements PicService {
    @Resource
    private PictureDao pictureDao;

    @Override
    public Integer savePicture(String path) {
        Picture picture = new Picture();
        picture.setPath(path);
        pictureDao.insertPicture(picture);
        return picture.getId();
    }
}
