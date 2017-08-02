package com.arvin.takeout.utils;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Arvin on 2017/8/2 21:06
 * E-Mail Address：it_xiaoshuai@163.com
 */

/**
 * 被观察的数据源，用于储存订单数据，一旦有新的数据进来，他就会不辞辛苦地通知所有观察者
 */
public class OrderObservable extends Observable {

    private static OrderObservable sOrderObserver = new OrderObservable();
    private OrderObservable() {
    }

    public static OrderObservable getInstance(){
        return sOrderObserver;
    }

    /* 订单状态
       * 1 未支付 2 已提交订单 3 商家接单  4 配送中,等待送达 5已送达 6 取消的订单*/
    public static final String ORDERTYPE_UNPAYMENT = "10";
    public static final String ORDERTYPE_SUBMIT = "20";
    public static final String ORDERTYPE_RECEIVEORDER = "30";
    public static final String ORDERTYPE_DISTRIBUTION = "40";
    // 骑手状态：接单、取餐、送餐
    public static final String ORDERTYPE_DISTRIBUTION_RIDER_RECEIVE = "43";
    public static final String ORDERTYPE_DISTRIBUTION_RIDER_TAKE_MEAL = "46";
    public static final String ORDERTYPE_DISTRIBUTION_RIDER_GIVE_MEAL = "48";

    public static final String ORDERTYPE_SERVED = "50";
    public static final String ORDERTYPE_CANCELLEDORDER = "60";


    public void changeData(Map<String, String> orderInfo) {
        setChanged(); //告诉所有观察者数据发生了变化
        notifyObservers(orderInfo);
    }
}