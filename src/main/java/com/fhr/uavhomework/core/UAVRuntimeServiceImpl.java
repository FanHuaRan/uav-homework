package com.fhr.uavhomework.core;

import com.fhr.uavhomework.context.UAVRuntimeContext;
import com.fhr.uavhomework.context.UAVRuntimeScene;
import com.fhr.uavhomework.message.IMessage;
import com.fhr.uavhomework.message.InitMessage;
import com.fhr.uavhomework.message.MoveMessage;
import com.fhr.uavhomework.msgdecode.IMessageDecodeStrategy;
import com.fhr.uavhomework.vehicle.UAVehicle;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author FanHuaran
 * @description UAV运行时服务
 * @create 2018-04-05 15:38
 **/
public class UAVRuntimeServiceImpl implements IUAVRuntimeService {
    private final UAVRuntimeContext uavRuntimeContext;

    private final IMessageDecodeStrategy messageDecodeStrategy;

    private AtomicInteger messageCount = new AtomicInteger(0);

    public UAVRuntimeServiceImpl(UAVRuntimeContext uavRuntimeContext, IMessageDecodeStrategy messageDecodeStrategy) {
        this.uavRuntimeContext = uavRuntimeContext;
        this.messageDecodeStrategy = messageDecodeStrategy;
    }

    @Override
    public void nextMessage(String messageStr) {
        IMessage message = messageDecodeStrategy.decodeMessage(messageCount.incrementAndGet(), messageStr);
        if (message == null) {
            uavRuntimeContext.nextScene(uavRuntimeContext.getLastScene());
        } else {
            UAVehicle nextUAVehicle = getNextVehice(uavRuntimeContext.getLastScene(), message);
            uavRuntimeContext.nextChangeVehicle(nextUAVehicle);
        }
    }

    @Override
    public UAVehicle outputChangeVehicleAtMoment(int msgId) {
        if (msgId > messageCount.get()) {
            return null;
        }
        return uavRuntimeContext.getChangeVehicleAtMoment(msgId);
    }

    @Override
    public String outputChangeVehicleAtMomentForStr(int msgId) {
        UAVehicle uaVehicle = outputChangeVehicleAtMoment(msgId);
        if (uaVehicle == null) {
            return String.format("Cannot find %d", msgId);
        }
        if (uaVehicle.isWrong()) {
            return String.format("Error: %d", msgId);
        }
        return String.format("%s %d %d %d %d", uaVehicle.getId(), msgId, uaVehicle.getX(), uaVehicle.getY(), uaVehicle.getZ());
    }

    private UAVehicle getNextVehice(UAVRuntimeScene lastScene, IMessage message) {
        String vehiceId = message.getVehicleId();
        if (message instanceof InitMessage) {
            InitMessage initMessage = (InitMessage) message;
            if (lastScene.getVehicle(vehiceId) == null) {
                return new UAVehicle(vehiceId, initMessage.getInitX(), initMessage.getInitY(), initMessage.getInitZ(), false);
            }
        } else if (message instanceof MoveMessage) {
            MoveMessage moveMessage = (MoveMessage) message;
            if (moveMessageIsLegal(lastScene.getVehicle(vehiceId), moveMessage)) {
                int currentX = moveMessage.getLastX() + moveMessage.getMoveX();
                int currentY = moveMessage.getLastY() + moveMessage.getMoveY();
                int currentZ = moveMessage.getLastZ() + moveMessage.getMoveZ();
                return new UAVehicle(vehiceId, currentX, currentY, currentZ, false);
            }
        }
        return new UAVehicle(vehiceId, null, null, null, true);
    }

    private boolean moveMessageIsLegal(UAVehicle lastUAVehilce, MoveMessage moveMessage) {
        return lastUAVehilce != null &&
                !lastUAVehilce.isWrong() &&
                moveMessage != null &&
                lastUAVehilce.getX() == moveMessage.getLastX() &&
                lastUAVehilce.getY() == moveMessage.getLastY() &&
                lastUAVehilce.getZ() == moveMessage.getLastZ();
    }
}