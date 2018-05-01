package com.longfor.log.db.listener;

/**
 * @author: tongzhenhua
 * @date: 2018/3/28
 * @function: 从数据库获取上传后台数据（返回格式为与后台约定格式）
 */
public interface IUploadJsonSuccess {
    void success(String uploadJson);
}
