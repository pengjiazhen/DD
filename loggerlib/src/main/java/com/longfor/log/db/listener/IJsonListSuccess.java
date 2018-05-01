package com.longfor.log.db.listener;

/**
 * @author: tongzhenhua
 * @date: 2018/3/28
 * @function:
 */
public interface IJsonListSuccess {
    void onSuccessByType(Iterable<String> jsons);
}
