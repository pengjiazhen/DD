package com.longfor.log.factory;

/**
 * @author: tongzhenhua
 * @date: 2018/3/22
 * @function:
 */
public interface Constants {
    String Action_EXIT = "longfor.action.exit";

    int LOG_TYPE_CLICK = 1;
    int LOG_TYPE_BEHAVIOR = 2;
    int LOG_TYPE_CRASH = 3;
    int LOG_TYPE_PV_DURATION = 4;
    int LOG_TYPE_APP_USE_DURATION = 5;

    int LOG_LEVEL_DEBUG = 1;
    int LOG_LEVEL_INFO = 2;
    int LOG_LEVEL_WARNING = 4;
    int LOG_LEVEL_ERROR = 8;
}
