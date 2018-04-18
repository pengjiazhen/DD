package com.bj.lightapp.data.api;

import com.bj.lightapp.data.protocol.LightAppRes;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jiazhen on 2018/4/13.
 * Desc:
 */
public interface LightAppService {

    //FilesService/appuser?userid=93964&usercode=shaomx&compid=5&logintype=1
    @GET("FilesService/appuser?usercode=shaomx&compid=5&logintype=1")
    Flowable<List<LightAppRes>> getUserLightApp(@Query("userid") String userId);
}
