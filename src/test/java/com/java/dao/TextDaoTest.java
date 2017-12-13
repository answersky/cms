package com.java.dao;

import com.java.BaseTest;
import com.xiaowo.Text;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author answer
 *         2017/11/3
 */
public class TextDaoTest extends BaseTest {
    @Resource
    private TextDao textDao;

    @Test
    public void insertText() throws Exception {
        Text text = new Text();
        text.setSourceUrl("http:www.ixiaowo.com");
        text.setContent("小窝金服");
        textDao.insertText(text);
        System.out.println(text.getId());
    }

    @Test
    public void findTextById() throws Exception {

    }

}