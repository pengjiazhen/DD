package com.longfor.log.db;

import android.content.Context;

import com.longfor.log.db.bean.CommonsLog;
import com.longfor.log.db.gen.CommonsLogDao;
import com.longfor.log.db.gen.DaoMaster;
import com.longfor.log.db.gen.DaoSession;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;


/**
 * @author: tongzhenhua
 * @date: 2018/3/20
 * @function: 操作数据库的增删改查
 */
public class DataBaseManager {
    private DaoSession mDaoSession = null;

    private DataBaseManager() {

    }

    /**
     * 初始化数据库，仅供包内调用
     * @param context
     * @return
     */
    DataBaseManager init(Context context) {
        final DataOpenHelper helper = new DataOpenHelper(context, "log_record.db");
        final Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
        return getInstance();
    }

    // 统一使用一个日志表
    /**
     * 获取crash日志列表
     * @return
     */
    List<CommonsLog> getCommonsLog() {
        return mDaoSession.getCommonsLogDao().loadAll();
    }
    /**
     * 获取日志列表
     * @param num 获取数量
     * @return
     */
    List<CommonsLog> getCommonsLog(int num) {
        return mDaoSession.getCommonsLogDao().queryBuilder().limit(num).list();
    }
    /**
     * 获取指定类型的日志列表
     * @param type 日志类型
     * @param num 获取数量
     * @return
     */
    List<CommonsLog> getCommonsLogByType(int type, int num) {
        return mDaoSession.getCommonsLogDao().queryBuilder().where(CommonsLogDao.Properties.LogType.eq(type)).limit(num).list();
    }

    List<CommonsLog> getCommonsLogByType(int type) {
        return mDaoSession.getCommonsLogDao().queryBuilder().where(CommonsLogDao.Properties.LogType.eq(type)).list();
    }
    List<CommonsLog> getCommonsLogIgnoreType(int ...ignoreTypes) {
        if(ignoreTypes ==null) {
            return mDaoSession.getCommonsLogDao().loadAll();
        }
        else if(ignoreTypes.length == 1) {
            return mDaoSession.getCommonsLogDao().queryBuilder().where(CommonsLogDao.Properties.LogType.notEq(ignoreTypes[0])).list();
        }
        else {
            WhereCondition []conditions = new WhereCondition[ignoreTypes.length-1];
            for(int i = 1; i < ignoreTypes.length; i++) {
                conditions[i] = CommonsLogDao.Properties.LogType.notEq(ignoreTypes[i]);
            }
            return mDaoSession.getCommonsLogDao().queryBuilder().where(CommonsLogDao.Properties.LogType.notEq(ignoreTypes[0]),conditions).list();
        }
    }

    List<CommonsLog> getCommonsLogIgnoreType(int num, int ...ignoreTypes) {
        if(ignoreTypes ==null||ignoreTypes.length==0) {
            return mDaoSession.getCommonsLogDao().queryBuilder().limit(num).list();
        }
        else if(ignoreTypes.length == 1) {
            return mDaoSession.getCommonsLogDao().queryBuilder().where(CommonsLogDao.Properties.LogType.notEq(ignoreTypes[0])).limit(num).list();
        }
        else {
            WhereCondition []conditions = new WhereCondition[ignoreTypes.length-1];
            for(int i = 1; i < ignoreTypes.length; i++) {
                conditions[i] = CommonsLogDao.Properties.LogType.notEq(ignoreTypes[i]);
            }
            return mDaoSession.getCommonsLogDao().queryBuilder().where(CommonsLogDao.Properties.LogType.notEq(ignoreTypes[0]),conditions).limit(num).list();
        }
    }

    /**
     * 根据日志保存时间获取日志
     * @param time 时间段以前的日志
     * @return
     */
    List<CommonsLog> getCommonsLogByTimeBefore(long time) {
        return mDaoSession.getCommonsLogDao().queryBuilder().where(CommonsLogDao.Properties.Time.lt(time)).list();
    }
    /**
     * 根据日志保存时间获取日志
     * @param time 时间段以后的日志
     * @return
     */
    List<CommonsLog> getCommonsLogByTime(long time) {
        return mDaoSession.getCommonsLogDao().queryBuilder().where(CommonsLogDao.Properties.Time.ge(time)).list();
    }
    void addCommonsLog(CommonsLog... logs) {
        mDaoSession.getCommonsLogDao().insertInTx(logs);
    }
    void addCommonsLog(Iterable<CommonsLog> logs) {
        mDaoSession.getCommonsLogDao().insertInTx(logs);
    }
    void deleteLogs() {
        mDaoSession.getCommonsLogDao().deleteAll();
    }
    void deleteLogs(int num) {
        mDaoSession.getCommonsLogDao().deleteInTx(getCommonsLog(num));
    }
    void deleteLogsByType(int type, int num) {
        mDaoSession.getCommonsLogDao().deleteInTx(getCommonsLogByType(type, num));
    }
    void deleteLogsByType(int type) {
        mDaoSession.getCommonsLogDao().deleteInTx(getCommonsLogByType(type));
    }

    void deleteLogsByTime(long time) {
        mDaoSession.getCommonsLogDao().deleteInTx(getCommonsLogByTimeBefore(time));
    }
    void updataLogs(CommonsLog ... logs) {
        mDaoSession.getCommonsLogDao().updateInTx(logs);
    }
    void updataLogs(Iterable<CommonsLog> logs) {
        mDaoSession.getCommonsLogDao().updateInTx(logs);
    }


    static DataBaseManager getInstance() {
        return DataBaseManagerHolder.mInstance;
    }
    private static class DataBaseManagerHolder {
        private static DataBaseManager mInstance = new DataBaseManager();
    }
}
