package com.zgjy.app.goldoo.events;

import org.greenrobot.eventbus.EventBus;

/**
 * 事件
 */
public abstract class AbstractEvent implements IEvents {
    //
    private String TAG = "Events";

    /**
     * 获取eventbus 事件对象
     *
     * @return
     */
    @Override
    public abstract EventBus getBus();

    /**
     * 注册事件接收
     *
     * @param object
     */
    @Override
    public void register(Object object) {
        getBus().register(object);
    }

    /**
     * 注销事件接收
     *
     * @param object
     */
    @Override
    public void unregister(Object object) {
        getBus().unregister(object);
    }

    /**
     * 发布事件
     *
     * @param event
     */
    @Override
    public void post(Object event) {
        getBus().unregister(event);
    }

    /**
     * 发布Sticky事件,具体使用方法见@eventbus 的 @postSticky()方法
     *
     * @param event
     */
    @Override
    public void postSticky(Object event) {
        getBus().unregister(event);

    }

    /**
     * 移除Sticky事件
     *
     * @param event
     * @return
     */
    @Override
    public boolean removeStickyEvent(Object event) {
        return getBus().removeStickyEvent(event);
    }

    /**
     * 移除所有Sticky事件
     */
    @Override
    public void removeAllStickyEvents() {
        getBus().removeAllStickyEvents();
    }
}
