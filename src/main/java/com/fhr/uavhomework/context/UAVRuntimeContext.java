package com.fhr.uavhomework.context;

import com.fhr.uavhomework.vehicle.UAVehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author FanHuaran
 * @description 无人机运行上下文
 * @create 2018-04-05 14:28
 **/
public class UAVRuntimeContext {
    private static final  UAVRuntimeScene EMPTRY_SCENE = new UAVRuntimeScene(new HashMap<>(),null);

    private final List<UAVRuntimeScene> uaRuntimeScenes = new ArrayList<>();

    {
        uaRuntimeScenes.add(EMPTRY_SCENE);
    }

    public void nextScene(UAVRuntimeScene uaRuntimeScene) {
        uaRuntimeScenes.add(uaRuntimeScene);
    }

    public void nextChangeVehicle(UAVehicle nextVehicle) {
        UAVRuntimeScene lastScene = getLastScene();
        Map<String, UAVehicle> nextVehicles = new HashMap<>(lastScene.getUaVehicles());
        String currentChangeVehicleId = null;
        if (nextVehicle != null) {
            currentChangeVehicleId = nextVehicle.getId();
            nextVehicles.put(currentChangeVehicleId, nextVehicle);
        }
        uaRuntimeScenes.add(new UAVRuntimeScene(nextVehicles, currentChangeVehicleId));
    }

    public UAVRuntimeScene getLastScene() {
        if(uaRuntimeScenes.size()>0){
            return uaRuntimeScenes.get(uaRuntimeScenes.size() - 1);
        }
        return EMPTRY_SCENE;
    }

    public UAVRuntimeScene getSceneAtMoment(int moment) {
        return uaRuntimeScenes.get(moment);
    }

    public UAVehicle getChangeVehicleAtMoment(int moment) {
        UAVRuntimeScene uaRuntimeScene = getSceneAtMoment(moment);
        return uaRuntimeScene.getCurrentChangeVehicle();
    }
}
