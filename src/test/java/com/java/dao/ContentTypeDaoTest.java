package com.java.dao;

import com.java.BaseTest;
import com.xiaowo.ContentType;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author answer
 *         2017/11/3
 */
public class ContentTypeDaoTest extends BaseTest {
    @Resource
    private ContentTypeDao contentTypeDao;

    @Test
    public void findContentTypes() throws Exception {
        List<ContentType> list = contentTypeDao.findContentTypes();
        System.out.println(list);
    }

    @Test
    public void findContentTypeById() throws Exception {
        ContentType contentType = contentTypeDao.findContentTypeById(2);
        System.out.println(contentType);
    }

}