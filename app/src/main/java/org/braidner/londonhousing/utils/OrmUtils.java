package org.braidner.londonhousing.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.SparseArray;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import org.braidner.londonhousing.dao.BoroughDao;
import org.braidner.londonhousing.dao.CommonDao;
import org.braidner.londonhousing.entity.Borough;
import org.braidner.londonhousing.entity.Point;
import org.braidner.londonhousing.entity.Ward;
import org.braidner.londonhousing.entity.WardProperty;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

/**
 * Created by KuznetsovNE on 07.05.2015.
 */
public class OrmUtils extends OrmLiteSqliteOpenHelper {

    public static final String TAG = OrmUtils.class.getSimpleName();

    private static final String DATABASE_NAME = "LondonLocations";
    private static final Integer DATABASE_VERSION = 1;

    public static final int BOROUGH_DAO_NUM = 0;
    public static final int WARD_DAO_NUM = BOROUGH_DAO_NUM + 1;
    public static final int WARD_PROPERTY_DAO_NUM = WARD_DAO_NUM + 1;
    public static final int POINT_DAO_NUM = WARD_PROPERTY_DAO_NUM + 1;

    private SparseArray<CommonDao> daos = new SparseArray<>();

    private Class[] classes = {
            Borough.class,
            Ward.class,
            WardProperty.class,
            Point.class
    };

    public OrmUtils(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static String getDBName() {
        return DATABASE_NAME;
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            DLog.i(TAG, "onCreate");
            createAllTables(connectionSource);
        } catch (SQLException e) {
            DLog.e(TAG, "Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    public void createAllTables(ConnectionSource connectionSource) throws SQLException {
        for (Class className : classes) {
            TableUtils.createTable(connectionSource, className);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            DLog.i(TAG, "onUpgrade");
            dropAllTables(connectionSource);
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            DLog.e(TAG, "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

    private void dropAllTables(ConnectionSource connectionSource) throws SQLException {
        for (Class className : classes) {
            TableUtils.dropTable(connectionSource, className, true);
        }
    }

    public void clearDatabase() {
        try {
            DLog.i(TAG, "onClear");
            dropAllTables(connectionSource);
            createAllTables(connectionSource);
        } catch (SQLException e) {
            DLog.e(TAG, "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

    public CommonDao getDaoByClass(Class<?> classInstance) throws SQLException {
        if (classInstance.equals(Borough.class)) {
            return getCustomDaoByNum(BoroughDao.class, BOROUGH_DAO_NUM);
        } else if (classInstance.equals(Ward.class)) {
            return getDaoByNum(Ward.class, WARD_DAO_NUM);
        } else if (classInstance.equals(WardProperty.class)) {
            return getDaoByNum(WardProperty.class, WARD_PROPERTY_DAO_NUM);
        } else if (classInstance.equals(Point.class)) {
            return getDaoByNum(Point.class, POINT_DAO_NUM);
        }
        return null;
    }

    private CommonDao getCustomDaoByNum(Class<? extends CommonDao> className, int daoNum) throws SQLException {
        CommonDao dao = daos.get(daoNum);
        if (dao == null) try {
            dao = className.getDeclaredConstructor(ConnectionSource.class).newInstance(getConnectionSource());
            daos.put(daoNum, dao);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dao;
    }

    private CommonDao getDaoByNum(Class<?> className, int daoNum) throws SQLException {
        CommonDao dao = daos.get(daoNum);
        if (dao == null) {
            dao = new CommonDao(getConnectionSource(), className);
            daos.put(daoNum, dao);
        }
        return dao;
    }

    @Override
    public void close() {
        super.close();
        daos = null;
    }
}
