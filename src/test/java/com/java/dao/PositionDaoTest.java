package com.java.dao;

import com.java.BaseTest;
import com.xiaowo.Position;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author answer
 *         2017/11/24
 */
public class PositionDaoTest extends BaseTest {
    @Resource
    private PositionDao positionDao;

    @Test
    public void insertPosition() throws Exception {
        Position position = new Position();
        position.setPosition("java开发工程师");
        position.setRemark("java开发");
        positionDao.insertPosition(position);
    }

    @Test
    public void findPositions() throws Exception {
        List<Position> positions = positionDao.findPositions();
        System.out.println(positions);
    }

    @Test
    public void findPositionById() throws Exception {
        Position position = positionDao.findPositionById(1);
        System.out.println(position);
    }

    @Test
    public void updatePosition() throws Exception {
        Position position = positionDao.findPositionById(1);
        position.setPosition("php开发工程师");
        position.setRemark("php开发");
        positionDao.updatePosition(position);
    }

    @Test
    public void deletePosition() throws Exception {
        positionDao.deletePosition(1);
    }

}