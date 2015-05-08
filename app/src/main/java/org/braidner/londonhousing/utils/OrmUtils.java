package org.braidner.londonhousing.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;

/**
 * Created by KuznetsovNE on 07.05.2015.
 */
public class OrmUtils extends OrmLiteSqliteOpenHelper {

    public static final String TAG = OrmUtils.class.getSimpleName();

    private static final String DATABASE_NAME = "";
    private static final Integer DATABASE_VERSION = 1;

    public OrmUtils(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static String getDBName() {
        return DATABASE_NAME;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }
}
