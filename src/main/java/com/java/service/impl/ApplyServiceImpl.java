package com.java.service.impl;

import com.java.dao.ApplyLogDao;
import com.java.service.ApplyLogService;
import com.xiaowo.ApplyLog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author answer
 *         2017/12/4
 */
@Service
public class ApplyServiceImpl implements ApplyLogService {
    @Resource
    private ApplyLogDao applyLogDao;

    @Override
    public List<ApplyLog> findCount() {
        return applyLogDao.findCount();
    }

    @Override
    public List<ApplyLog> findApplyLogByResource(String resource) {
        return applyLogDao.findApplyLogByResource(resource);
    }
}
