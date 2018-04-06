package com.fhr.uavhomework.context;

import com.fhr.uavhomework.vehicle.UAVehicle;

import java.util.Collections;
import java.util.Map;

/**
 * @author FanHuaran
 * @description 某一命令后的运行时场景，以命令为单元，不可变对象！
 * @create 2018-04-05 14:29
 **/
public class UAVRuntimeScene {
    /**
     * 当前场景的无人机运行位置和运行状态，key为ID，value为无人机
     */
    private final Map<String, UAVehicle> uaVehicles;

    /**
     * 当前命令下作用的的编号
     */
    private final String currentChangeVehicleId;

    public UAVRuntimeScene(Map<String, UAVehicle> uaVehicles, String currentChangeVehicleId) {
        this.uaVehicles = Collections.unmodifiableMap(uaVehicles);
        this.currentChangeVehicleId = currentChangeVehicleId;
    }

    /************   getters setters ************/

    public Map<String, UAVehicle> getUaVehicles() {
        return uaVehicles;
    }

    public String getCurrentChangeVehicleId() {
        return currentChangeVehicleId;
    }

    public UAVehicle getVehicle(String vehicleId) {
        return uaVehicles.get(vehicleId);
    }

    public UAVehicle getCurrentChangeVehicle() {
        return uaVehicles.get(currentChangeVehicleId);
    }

    @Override
    public String toString() {
        return "UAVRuntimeScene{" +
                "uaVehicles=" + uaVehicles +
                ", currentChangeVehicleId='" + currentChangeVehicleId + '\'' +
                '}';
    }
}
