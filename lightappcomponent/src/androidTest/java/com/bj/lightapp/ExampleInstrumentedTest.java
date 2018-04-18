package com.bj.lightapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.bj.basic.data.manager.RepositoryManager;
import com.bj.lightapp.data.protocol.LightAppRes;
import com.bj.lightapp.mvp.model.LightAppModel;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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
        LightAppModel lightAppModel = new LightAppModel(RepositoryManager.getInstance(null));
        lightAppModel.getLightAppList("93964")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<LightAppRes>>() {
                    @Override
                    public void accept(List<LightAppRes> lightAppRes) {
                        Log.d("sss",lightAppRes.toString());
                    }
                });;
    }
}
