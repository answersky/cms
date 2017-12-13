package com.java.dao;

import com.java.BaseTest;
import com.xiaowo.Zone;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author answer
 *         2017/11/24
 */
public class ZoneDaoTest extends BaseTest {
    @Resource
    private ZoneDao zoneDao;

    @Test
    public void insertZone() throws Exception {
        Zone zone = new Zone();
        zone.setZone("武汉");
        zoneDao.insertZone(zone);
    }

    @Test
    public void findZones() throws Exception {
        List<Zone> zones = zoneDao.findZones();
        System.out.println(zones);
    }

    @Test
    public void findZoneById() throws Exception {
        Zone zone = zoneDao.findZoneById(8);
        System.out.println(zone);
    }

    @Test
    public void updateZone() throws Exception {

    }

    @Test
    public void deleteZone() throws Exception {
        zoneDao.deleteZone(8);
    }

}