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
    /**
     * 空场景常量，是运行上下文的初始场景
     */
    private static final UAVRuntimeScene EMPTRY_SCENE = new UAVRuntimeScene(new HashMap<>(), null);

    /**
     * 场景记录表
     */
    private final List<UAVRuntimeScene> uaRuntimeScenes = new ArrayList<>();

    /**
     * 初始场景记录表
     */ {
        uaRuntimeScenes.add(EMPTRY_SCENE);
    }

    /**
     * 添加一个场景
     *
     * @param uaRuntimeScene
     */
    public void nextScene(UAVRuntimeScene uaRuntimeScene) {
        uaRuntimeScenes.add(uaRuntimeScene);
    }

    /**
     * 添加下一个改变无人机（隐含添加下一个场景）
     *
     * @param nextVehicle
     */
    public void nextChangeVehicle(UAVehicle nextVehicle) {
        //复制上一个场景
        UAVRuntimeScene lastScene = getLastScene();
        Map<String, UAVehicle> nextVehicles = new HashMap<>(lastScene.getUaVehicles());

        //修改
        String currentChangeVehicleId = null;
        if (nextVehicle != null) {
            currentChangeVehicleId = nextVehicle.getId();
            nextVehicles.put(currentChangeVehicleId, nextVehicle);
        }

        //添加场景
        uaRuntimeScenes.add(new UAVRuntimeScene(nextVehicles, currentChangeVehicleId));
    }

    /**
     * 获取最后一个场景
     *
     * @return
     */
    public UAVRuntimeScene getLastScene() {
        if (uaRuntimeScenes.size() > 0) {
            return uaRuntimeScenes.get(uaRuntimeScenes.size() - 1);
        }
        return EMPTRY_SCENE;
    }

    /**
     * 获取给定moment的场景
     *
     * @param moment 指令单位，从1开始
     * @return
     */
    public UAVRuntimeScene getSceneAtMoment(int moment) {
        return uaRuntimeScenes.get(moment);
    }

    /**
     * 获取给定moment下修改的无人机
     *
     * @param moment 指令单位，从1开始
     * @return
     */
    public UAVehicle getChangeVehicleAtMoment(int moment) {
        UAVRuntimeScene uaRuntimeScene = getSceneAtMoment(moment);
        return uaRuntimeScene.getCurrentChangeVehicle();
    }
}
