package org.braidner.londonhousing.dao;

import com.j256.ormlite.support.ConnectionSource;

import org.braidner.londonhousing.entity.Borough;
import org.braidner.londonhousing.entity.Indexed;
import org.braidner.londonhousing.entity.Point;
import org.braidner.londonhousing.entity.Ward;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * Created by smith / 12.05.2015.
 */
public class BoroughDao extends CommonDao<Borough, Integer> {

    public BoroughDao(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Borough.class);
    }

    public List<Borough> searchByName(String name) throws SQLException {
        return queryBuilder().where().like("code", name + "%").query();
    }

    public List<Borough> searchAll() throws SQLException {
        return queryBuilder().query();
    }

    public void create(Borough borough, CommonDao<Ward, Integer> wardDao, CommonDao<Point, Integer> pointDao) throws SQLException {
        create(borough);
        if (borough.getWards() != null) {
            saveWards(borough.getWards(), wardDao);
        }
        savePolygon(borough.getPolygon(), pointDao);
    }

    public void createOrUpdate(Borough Borough, List<Ward> wards, CommonDao<Ward, Integer> wardDao) throws SQLException {
        createOrUpdate(Borough);
        saveWards(wards, wardDao);
    }

    private void saveWards(Collection<Ward> wards, CommonDao<Ward, Integer> wardDao) throws SQLException {
        for (Ward ward : wards) {
            wardDao.createOrUpdate(ward);
        }
    }

    private void savePolygon(Collection<Point> polygon, CommonDao<Point, Integer> pointDao) throws SQLException {
        for (Point point : polygon) {
            pointDao.createOrUpdate(point);
        }
    }

    public void saveWard(Ward ward, CommonDao<Ward, Integer> wardDao) {
        try {
            wardDao.createOrUpdate(ward);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
