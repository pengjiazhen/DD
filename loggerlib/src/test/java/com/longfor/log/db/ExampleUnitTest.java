package com.longfor.log.db;

import com.longfor.log.factory.bean.CrashLogFactory;
import com.longfor.log.factory.utils.JSONUtils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void beanToJSON() {
        CrashLogFactory factory=new CrashLogFactory();
        CrashLogImpl crashLog = factory.create();
        crashLog.setAppVersion("2.1");
        String json = JSONUtils.beanToJson(crashLog);
        System.out.println(json);
    }

    @Test
    public void listToJSON() {
        List<CrashLogImpl> crashLogList=new ArrayList<>();
        CrashLogFactory factory=new CrashLogFactory();
        for (int i = 0; i < 3; i++) {
            CrashLogImpl crashLog = factory.create();
            crashLog.setAppVersion("2.1");
            crashLogList.add(crashLog);
        }
        String json = JSONUtils.listToJson(crashLogList);
        System.out.println(json);
    }

    @Test
    public void mapToJSON() {
        Map<String,CrashLogImpl> crashLogMap=new HashMap<>();
        CrashLogFactory factory=new CrashLogFactory();
        for (int i = 0; i < 3; i++) {
            CrashLogImpl crashLog = factory.create();
            crashLog.setAppVersion("2.1");
            crashLogMap.put(String.valueOf(i),crashLog);
        }
        String json = JSONUtils.mapToJson(crashLogMap);
        System.out.println(json);
    }

    @Test
    public void JSONToBean() {
        String json="{\"appVersion\":\"2.1\",\"logLevel\":0,\"logType\":3,\"sTime\":1522808888623}";
        CrashLogImpl crashLog = JSONUtils.jsonToBean(json, CrashLogImpl.class);
        crashLog.setAppVersion("2.2");
        System.out.println(JSONUtils.beanToJson(crashLog));
    }

    @Test
    public void JSONToList() {
        String json="[{\"appVersion\":\"2.1\",\"logLevel\":0,\"logType\":3,\"sTime\":1522808984748},{\"appVersion\":\"2.1\",\"logLevel\":0,\"logType\":3,\"sTime\":1522808984748},{\"appVersion\":\"2.1\",\"logLevel\":0,\"logType\":3,\"sTime\":1522808984748}]";
        List<CrashLogImpl> crashLogList = JSONUtils.jsonToList(json, CrashLogImpl.class);
        for (int i = 0; i < crashLogList.size(); i++) {
            crashLogList.get(i).setAppVersion("2.2");
        }
        System.out.println(JSONUtils.listToJson(crashLogList));
    }

    @Test
    public void JSONToMap() {
        String json="{\"0\":{\"appVersion\":\"2.1\",\"logLevel\":0,\"logType\":3,\"sTime\":1522809093335},\"1\":{\"appVersion\":\"2.1\",\"logLevel\":0,\"logType\":3,\"sTime\":1522809093335},\"2\":{\"appVersion\":\"2.1\",\"logLevel\":0,\"logType\":3,\"sTime\":1522809093335}}";
    }
}