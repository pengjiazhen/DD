package com.bj.lightapp.aspectj;

/**
 * Created by jiazhen on 2018/4/28.
 * Desc:
 */
public class ClickBean {
    private String clickLabel;
    private Object object;

    public ClickBean(String clickLabel, Object object) {
        this.clickLabel = clickLabel;
        this.object = object;
    }

    public String getClickLabel() {
        return clickLabel;
    }

    public void setClickLabel(String clickLabel) {
        this.clickLabel = clickLabel;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
