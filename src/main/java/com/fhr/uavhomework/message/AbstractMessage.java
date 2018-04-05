package com.fhr.uavhomework.message;

/**
 * @author FanHuaran
 * @description 无人机抽象消息
 * @create 2018-04-05 11:25
 **/
public abstract class AbstractMessage implements IMessage{
    protected  final int id;

    protected  final String vehicleId;

    public AbstractMessage(int id, String vehicleId) {
        this.id = id;
        this.vehicleId = vehicleId;
    }

    public int getId() {
        return id;
    }

    public String getVehicleId() {
        return vehicleId;
    }
}
