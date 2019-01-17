package com.xgsb.datafactory.bean;

import com.xgsb.datafactory.enu.EventBusAction;

/**
 * Name: EventRouter
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-12-06 18:38
 */
public class EventRouter {
    private EventBusAction action;
    private Object data;

    public EventRouter(EventBusAction action) {
        this.action = action;
    }

    public EventRouter(EventBusAction action, Object data) {
        this.action = action;
        this.data = data;
    }

    public EventBusAction getAction() {
        return action;
    }

    public void setAction(EventBusAction action) {
        this.action = action;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
