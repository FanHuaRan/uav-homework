package com.fhr.uavhomework.message;

/**
 * @author FanHuaran
 * @description 消息抽象接口
 * @create 2018-04-05 11:23
 **/
public interface IMessage {
    /**
     * 获取无人机消息编号
     * @return
     */
    int getId();

    /**
     * 获取无人机编号
     * @return
     */
    String getVehicleId();
}
