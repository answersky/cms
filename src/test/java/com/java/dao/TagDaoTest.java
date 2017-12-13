package com.java.dao;

import com.java.BaseTest;
import com.xiaowo.Tag;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author answer
 *         2017/11/3
 */
public class TagDaoTest extends BaseTest {
    @Resource
    private TagDao tagDao;

    @Test
    public void insertTag() throws Exception {
        Tag tag = new Tag();
        tag.setTag("装修");
        tag.setReferenceCount(1);
        tagDao.insertTag(tag);
        System.out.println(tag.getId());
    }

    @Test
    public void findTagByTag() throws Exception {
        Tag tag = tagDao.findTagByTag("小窝金服");
        System.out.println(tag);
    }

    @Test
    public void updateReferenceCount() throws Exception {
        Tag tag = tagDao.findTagByTag("小窝金服");
        tagDao.updateTagReferenceCount(2, tag.getId());
    }

}