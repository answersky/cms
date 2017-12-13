package com.java.dao;

import com.java.BaseTest;
import com.xiaowo.Content;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author answer
 *         2017/11/3
 */
public class ContentDaoTest extends BaseTest {
    @Resource
    private ContentDao contentDao;

    @Test
    public void insertContent() throws Exception {
        Content content = new Content();
        content.setTitle("小窝");
        content.setEditor("answer");
        content.setCategoryId(11);
        content.setTypeId(1);
        content.setTextId(1);
        contentDao.insertContent(content);
    }

    @Test
    public void findContentByCategoryId() throws Exception {
        List<Content> contents = contentDao.findContentByCategoryId(41);
        System.out.println(contents);
    }

}