package com.longfor.log.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.longfor.log.db.gen.CommonsLogDao;
import com.longfor.log.db.gen.DaoMaster;

import org.greenrobot.greendao.database.Database;

/**
 * @author: tongzhenhua
 * @date: 2018/3/20
 * @function:
 */
public class DataOpenHelper extends DaoMaster.OpenHelper{
    public DataOpenHelper(Context context, String name) {
        super(context, name);
    }

    public DataOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        Log.i("log","oldVersion:"+oldVersion+",newVersion"+newVersion);
        MigrationHelper.getInstance().migrate(db, CommonsLogDao.class);
    }
}
