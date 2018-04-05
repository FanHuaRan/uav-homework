package com.fhr.uavhomework.context;

import com.fhr.uavhomework.vehicle.UAVehicle;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author FanHuaran
 * @description 运行时场景某一命令下，以指令为单元
 * @create 2018-04-05 14:29
 **/
public class UAVRuntimeScene {
    private final Map<String, UAVehicle> uaVehicles;

    private final String currentChangeVehicleId;

    public UAVRuntimeScene(Map<String, UAVehicle> uaVehicles, String currentChangeVehicleId) {
        this.uaVehicles = Collections.unmodifiableMap(uaVehicles);
        this.currentChangeVehicleId = currentChangeVehicleId;
    }

    public Map<String, UAVehicle> getUaVehicles() {
        return uaVehicles;
    }

    public String getCurrentChangeVehicleId() {
        return currentChangeVehicleId;
    }

    public UAVehicle getVehicle(String vehicleId) {
        return uaVehicles.get(vehicleId);
    }

    public UAVehicle getCurrentChangeVehicle(){
        return  uaVehicles.get(currentChangeVehicleId);
    }

    @Override
    public String toString() {
        return "UAVRuntimeScene{" +
                "uaVehicles=" + uaVehicles +
                ", currentChangeVehicleId='" + currentChangeVehicleId + '\'' +
                '}';
    }
}
