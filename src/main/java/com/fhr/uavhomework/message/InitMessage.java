package com.fhr.uavhomework.message;

import com.fhr.uavhomework.message.AbstractMessage;
import com.fhr.uavhomework.message.IMessage;

/**
 * @author FanHuaran
 * @description 无人机初始化消息
 * @create 2018-04-05 11:31
 **/
public class InitMessage extends AbstractMessage implements IMessage {
    /**
     * 初始X坐标
     */
    private final int initX;

    /**
     * 初始Y坐标
     */
    private final int initY;

    /**
     * 初始Z坐标
     */
    private final int initZ;

    public InitMessage(int id, String vehicleId, int initX, int initY, int initZ) {
        super(id, vehicleId);
        this.initX = initX;
        this.initY = initY;
        this.initZ = initZ;
    }

    public int getInitX() {
        return initX;
    }

    public int getInitY() {
        return initY;
    }

    public int getInitZ() {
        return initZ;
    }
}
