package com.longfor.log.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * @author: tongzhenhua
 * @date: 2018/3/20
 * @function:
 */
@Entity
public class CommonsLog {
    @Id(autoincrement = true)
    private Long id;
    private int logType;
    private long time;
    private String json;
    @Generated(hash = 297063451)
    public CommonsLog(Long id, int logType, long time, String json) {
        this.id = id;
        this.logType = logType;
        this.time = time;
        this.json = json;
    }
    @Generated(hash = 1019635369)
    public CommonsLog() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getLogType() {
        return this.logType;
    }
    public void setLogType(int logType) {
        this.logType = logType;
    }
    public long getTime() {
        return this.time;
    }
    public void setTime(long time) {
        this.time = time;
    }
    public String getJson() {
        return this.json;
    }
    public void setJson(String json) {
        this.json = json;
    }
    
}
