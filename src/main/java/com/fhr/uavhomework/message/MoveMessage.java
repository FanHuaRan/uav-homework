package com.fhr.uavhomework.message;

/**
 * @author FanHuaran
 * @description 无人机移动消息
 * @create 2018-04-05 11:35
 **/
public class MoveMessage extends AbstractMessage implements IMessage {
    private final int moveX;

    private final int moveY;

    private final int moveZ;

    private final int lastX;

    private final int lastY;

    private final int lastZ;

    public MoveMessage(int id, String vehicleId, int moveX, int moveY, int moveZ, int lastX, int lastY, int lastZ) {
        super(id, vehicleId);
        this.moveX = moveX;
        this.moveY = moveY;
        this.moveZ = moveZ;
        this.lastX = lastX;
        this.lastY = lastY;
        this.lastZ = lastZ;
    }
}
