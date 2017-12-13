package com.java.dao;

import com.java.BaseTest;
import com.xiaowo.ApplyLog;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author answer
 *         2017/12/4
 */
public class ApplyLogDaoTest extends BaseTest {
    @Resource
    private ApplyLogDao applyLogDao;

    @Test
    public void findCount() throws Exception {
        List<ApplyLog> logs = applyLogDao.findCount();
        System.out.println(logs);
    }

    @Test
    public void findApplyLogByResource() throws Exception {
        List<ApplyLog> logs = applyLogDao.findApplyLogByResource("officialSite");
        System.out.println(logs);
    }

}