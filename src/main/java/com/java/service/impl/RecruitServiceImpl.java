package com.java.service.impl;

import com.java.dao.RecruitDao;
import com.java.dao.ZoneDao;
import com.java.service.DepartmentService;
import com.java.service.PositionService;
import com.java.service.RecruitService;
import com.xiaowo.Department;
import com.xiaowo.Position;
import com.xiaowo.Recruit;
import com.xiaowo.Zone;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author answer
 *         2017/11/24
 */
@Service
public class RecruitServiceImpl implements RecruitService {
    @Resource
    private RecruitDao recruitDao;
    @Resource
    private DepartmentService departmentService;
    @Resource
    private PositionService positionService;
    @Resource
    private ZoneDao zoneDao;


    @Override
    public List<Recruit> findAllRecruit() {
        List<Recruit> list = recruitDao.findRecruit();
        formatRecruit(list);
        return list;
    }

    @Override
    public Recruit findRecruitById(Integer id) {
        return recruitDao.findRecruitById(id);
    }

    @Override
    public int saveRecruit(Integer departmentId, Integer positionId, Integer zoneId, String title, String duty, String jobRequire, String type) {
        Recruit recruit = recruitDao.findBydepartIdAndPosIdAndZoneId(departmentId, positionId, zoneId, type);
        if (recruit != null) {
            return 0;
        }
        Recruit re = new Recruit();
        re.setDepartmentId(departmentId);
        re.setPositionId(positionId);
        re.setZoneId(zoneId);
        re.setTitle(title);
        re.setDuty(duty);
        re.setJobRequirements(jobRequire);
        re.setType(type);
        return recruitDao.insertRecruit(re);
    }

    @Override
    public void deleteRecruit(Integer id) {
        recruitDao.deleteRecruit(id);
    }

    @Override
    public int updateRecruit(Integer id, Integer departmentId, Integer positionId, Integer zoneId, String title, String duty, String jobRequire, String type) {
        Recruit re = recruitDao.findRecruitById(id);
        re.setDepartmentId(departmentId);
        re.setPositionId(positionId);
        re.setZoneId(zoneId);
        re.setTitle(title);
        re.setDuty(duty);
        re.setJobRequirements(jobRequire);
        re.setType(type);
        return recruitDao.updateRecruit(re);
    }

    @Override
    public void publish(List<Integer> list) {
        recruitDao.publishRecruit(list);
    }

    private void formatRecruit(List<Recruit> recruits) {
        if (recruits != null && recruits.size() > 0) {
            for (Recruit recruit : recruits) {
                Integer departmentId = recruit.getDepartmentId();
                Integer positionId = recruit.getPositionId();
                Integer zoneId = recruit.getZoneId();
                Department department = departmentService.findDepartmentById(departmentId);
                if (department != null) {
                    recruit.setDepartment(department.getDepartment());
                }
                Position position = positionService.findPositionById(positionId);
                if (position != null) {
                    recruit.setPosition(position.getPosition());
                }
                Zone zone = zoneDao.findZoneById(zoneId);
                if (zone != null) {
                    recruit.setZone(zone.getZone());
                }
            }
        }
    }
}
