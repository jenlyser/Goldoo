package com.zgjy.app.goldoo.events;


import org.greenrobot.eventbus.EventBus;

/**
 * 事件接口
 * description
 * author Sean
 * Version 1.0
 * date 2020/6/29
 * Copyright w-king
 */
public interface IEvents {

    /**
     * 获取eventbus 事件对象
     *
     * @return
     */
    EventBus getBus();

    /**
     * 注册事件接收
     *
     * @param object
     */
    void register(Object object);

    /**
     * 注销事件接收
     *
     * @param object
     */
    void unregister(Object object);

    /**
     * 发布事件
     *
     * @param event
     */
    void post(Object event);

    /**
     * 发布Sticky事件,具体使用方法见@eventbus 的 @postSticky()方法
     *
     * @param event
     */
    void postSticky(Object event);

    /**
     * 移除Sticky事件
     *
     * @param event
     * @return
     */
    boolean removeStickyEvent(Object event);

    /**
     * 移除所有Sticky事件
     */
    void removeAllStickyEvents();
}
