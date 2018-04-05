package com.fhr.uavhomework.core;

import com.fhr.uavhomework.vehicle.UAVehicle;

/**
 * @author FanHuaran
 * @description
 * @create 2018-04-05 16:20
 **/
public interface IUAVRuntimeService {
    void nextMessage(String messageStr);

    UAVehicle outputChangeVehicleAtMoment(int msgId);

    String outputChangeVehicleAtMomentForStr(int msgId);
}
