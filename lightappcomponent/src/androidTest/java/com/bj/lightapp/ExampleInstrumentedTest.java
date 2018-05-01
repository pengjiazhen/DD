package com.bj.lightapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.longfor.log.db.DBLogConstants;
import com.longfor.log.db.DataBaseManager;
import com.longfor.log.db.bean.CommonsLog;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.bj.lightapp.test", appContext.getPackageName());
    }

    @Test
    public void testHello(){
       /* LightAppModel lightAppModel = new LightAppModel(RepositoryManager.getInstance(null));
        lightAppModel.getLightAppList("93964")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<LightAppRes>>() {
                    @Override
                    public void accept(List<LightAppRes> lightAppRes) {
                        Log.d("sss",lightAppRes.toString());
                    }
                });;*/
    }

    @Test
    public void test() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.bj.lightapp", appContext.getPackageName());

        DataBaseManager manager = DataBaseManager.getInstance().init(appContext);

        CommonsLog commonsLog = new CommonsLog();
        commonsLog.setId(32L);
        commonsLog.setLogType(DBLogConstants.LOG_TYPE.CLICK.ordinal());
        commonsLog.setJson("{}");
        commonsLog.setTime(System.currentTimeMillis());
        manager.addCommonsLog(commonsLog);
    }

    @After
    public void testA() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.bj.lightapp", appContext.getPackageName());

        DataBaseManager manager = DataBaseManager.getInstance().init(appContext);

        List<CommonsLog> commonsLog = manager.getCommonsLog();

        Log.e("PJZ",""+commonsLog.toString());
    }


}
