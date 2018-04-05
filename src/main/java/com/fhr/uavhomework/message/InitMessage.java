package com.fhr.uavhomework.message;

/**
 * @author FanHuaran
 * @description 无人机初始化消息
 * @create 2018-04-05 11:31
 **/
public class InitMessage extends AbstractMessage implements IMessage {

    private final int initX;

    private final int initY;

    private final int initZ;


    public InitMessage(int id, String vehicleId, int initX, int initY, int initZ) {
        super(id, vehicleId);
        this.initX = initX;
        this.initY = initY;
        this.initZ = initZ;
    }
}
