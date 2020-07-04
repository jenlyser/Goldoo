package com.zgjy.app.goldoo.events;

import org.greenrobot.eventbus.EventBus;

/**
 * 流程事件
 * description UIEvent
 * author Sean
 * Version 1.0
 * date 2020/6/28
 * Copyright w-king
 */
public class WorkFlowEvent {

    /**
     * 声明EventBus事件对象
     */
    private static volatile EventBus defaultInstance;
    /**
     * 自定义事件抽象类
     */
    private static AbstractEvent mEvent;

    /**
     * 获取界面事件
     *
     * @return
     */
    public static IEvents event() {
        if (mEvent == null) {
            mEvent = new AbstractEvent() {
                @Override
                public EventBus getBus() {
                    return getEvent();
                }
            };
        }
        return mEvent;
    }

    /**
     * 初始化EventBus
     *
     * @return
     */
    private static EventBus getEvent() {
        EventBus instance = defaultInstance;
        if (instance == null) {
            synchronized (WorkFlowEvent.class) {
                instance = WorkFlowEvent.defaultInstance;
                if (instance == null) {
                    instance = WorkFlowEvent.defaultInstance = new EventBus();
                }
            }
        }
        return instance;
    }
}
