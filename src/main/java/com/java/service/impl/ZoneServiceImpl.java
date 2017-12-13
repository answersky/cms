package com.java.service.impl;

import com.java.dao.ZoneDao;
import com.java.service.ZoneService;
import com.xiaowo.Zone;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author answer
 *         2017/11/24
 */
@Service
public class ZoneServiceImpl implements ZoneService {
    @Resource
    private ZoneDao zoneDao;

    @Override
    public List<Zone> findZones() {
        return zoneDao.findZones();
    }

    @Override
    public Zone findZoneByZone(String zone) {
        return zoneDao.findZoneByZone(zone);
    }

    @Override
    public void deleteZone(Integer id) {
        zoneDao.deleteZone(id);
    }

    @Override
    public int saveZone(String zone) {
        Zone zo = findZoneByZone(zone);
        if (zo != null) {
            return 0;
        }
        Zone zon = new Zone();
        zon.setZone(zone);
        return zoneDao.insertZone(zon);
    }
}
