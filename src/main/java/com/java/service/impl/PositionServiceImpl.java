package com.java.service.impl;

import com.java.dao.PositionDao;
import com.java.service.PositionService;
import com.xiaowo.Position;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author answer
 *         2017/11/24
 */
@Service
public class PositionServiceImpl implements PositionService {
    @Resource
    private PositionDao positionDao;

    @Override
    public List<Position> findPositions() {
        return positionDao.findPositions();
    }

    @Override
    public void deletePosition(Integer id) {
        positionDao.deletePosition(id);
    }

    @Override
    public Position findPositionById(Integer id) {
        return positionDao.findPositionById(id);
    }

    @Override
    public int updatePosition(Integer id, String position, String remark) {
        List<String> posts = positionDao.findPositionNoSame(id);
        if (posts != null && posts.size() > 0) {
            if (posts.indexOf(position) >= 0) {
                //已存在
                return 0;
            }
        }
        Position pos = positionDao.findPositionById(id);
        pos.setPosition(position);
        pos.setRemark(remark);
        return positionDao.updatePosition(pos);
    }

    @Override
    public int savePosition(String position, String remark) {
        Position pos = positionDao.findPositionByPosition(position);
        if (pos != null) {
            return 0;
        }
        Position posit = new Position();
        posit.setPosition(position);
        posit.setRemark(remark);
        return positionDao.insertPosition(posit);
    }
}
