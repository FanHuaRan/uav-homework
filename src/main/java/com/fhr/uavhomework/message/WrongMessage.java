package com.fhr.uavhomework.message;

/**
 * @author FanHuaran
 * @description 无人机运行错误消息
 * @create 2018-04-05 15:09
 **/
public class WrongMessage extends AbstractMessage {

    public WrongMessage(int id, String vehicleId) {
        super(id, vehicleId);
    }
}
