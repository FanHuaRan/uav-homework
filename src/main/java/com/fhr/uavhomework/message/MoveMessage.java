package com.fhr.uavhomework.message;

import com.fhr.uavhomework.message.AbstractMessage;
import com.fhr.uavhomework.message.IMessage;

/**
 * @author FanHuaran
 * @description 无人机移动消息
 * @create 2018-04-05 11:35
 **/
public class MoveMessage extends AbstractMessage implements IMessage {
    /**
     * X方向上的移动距离
     */
    private final int moveX;

    /**
     * Y方向上的移动距离
     */
    private final int moveY;

    /**
     * Z方向上的移动距离
     */
    private final int moveZ;

    /**
     * 移动前的X坐标
     */
    private final int lastX;

    /**
     * 移动前的Y坐标
     */
    private final int lastY;

    /**
     * 移动前的Z坐标
     */
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

    public int getMoveX() {
        return moveX;
    }

    public int getMoveY() {
        return moveY;
    }

    public int getMoveZ() {
        return moveZ;
    }

    public int getLastX() {
        return lastX;
    }

    public int getLastY() {
        return lastY;
    }

    public int getLastZ() {
        return lastZ;
    }
}
