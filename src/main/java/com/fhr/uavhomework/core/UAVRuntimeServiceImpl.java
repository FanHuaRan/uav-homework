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
 * @description 无人机运行时服务，非线程安全
 * @create 2018-04-05 15:38
 **/
public class UAVRuntimeServiceImpl implements IUAVRuntimeService {
    /**
     * 无人机运行时上下文
     */
    private final UAVRuntimeContext uavRuntimeContext;

    /**
     * 无人机消息解码器
     */
    private final IMessageDecodeStrategy messageDecodeStrategy;

    /**
     * 消息计数器
     */
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
    public String outputChangeVehicleForStrAtMoment(int msgId) {
        UAVehicle uaVehicle = outputChangeVehicleAtMoment(msgId);
        if (uaVehicle == null) {
            return String.format("Cannot find %d", msgId);
        }
        if (uaVehicle.isWrong()) {
            return String.format("Error: %d", msgId);
        }
        return String.format("%s %d %d %d %d", uaVehicle.getId(), msgId, uaVehicle.getX(), uaVehicle.getY(), uaVehicle.getZ());
    }

    /**
     * 计算下一个无人机的状态
     * @param lastScene
     * @param message
     * @return
     */
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

    /**
     * 校验移动消息是否合法
     * @param lastUAVehilce
     * @param moveMessage
     * @return
     */
    private boolean moveMessageIsLegal(UAVehicle lastUAVehilce, MoveMessage moveMessage) {
        return lastUAVehilce != null &&
                !lastUAVehilce.isWrong() &&
                moveMessage != null &&
                lastUAVehilce.getX() == moveMessage.getLastX() &&
                lastUAVehilce.getY() == moveMessage.getLastY() &&
                lastUAVehilce.getZ() == moveMessage.getLastZ();
    }
}
