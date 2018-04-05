package com.fhr.uavhomework.core;

import com.fhr.uavhomework.vehicle.UAVehicle;

/**
 * @author FanHuaran
 * @description 无人机运行时服务
 * @create 2018-04-05 16:20
 **/
public interface IUAVRuntimeService {
    /**
     * 输入下一条消息
     * @param messageStr
     */
    void nextMessage(String messageStr);

    /**
     * 输出某个命名编号下的无人机的状态
     * @param msgId
     * @return
     */
    UAVehicle outputChangeVehicleAtMoment(int msgId);

    /**
     * 输出某个命名编号下的无人机的状态的字符串表示
     * @param msgId
     * @return
     */
    String outputChangeVehicleForStrAtMoment(int msgId);
}
