package com.fhr.uavhomework.msgdecode;

import com.fhr.uavhomework.message.IMessage;
import com.fhr.uavhomework.message.InitMessage;
import com.fhr.uavhomework.message.MoveMessage;
import com.fhr.uavhomework.message.WrongMessage;
import com.fhr.uavhomework.util.IntegerUtils;

/**
 * @author FanHuaran
 * @description 简单消息解码策略实现
 * @create 2018-04-05 15:08
 **/
public class SimpleMessageDecodeStrategy implements IMessageDecodeStrategy {
    /**
     * 初始消息单词长度为4，格式：{无人机ID} {X} {Y} {Z}
     */
    private static final int INIT_MESSAGE_WORD_COUNT = 4;

    /**
     * 移动消息单词长度为7，格式：{无人机ID} {X'} {Y'} {Z'} {offsetX} {offsetY} {offsetZ}
     */
    private static final int MOVE_MESSAGE_WORD_COUNT = 7;

    @Override
    public IMessage decodeMessage(int msgId, String str) {
        if (str == null || str.isEmpty()) {
            return new WrongMessage(msgId, null);
        }

        String[] words = str.split(" ");

        String vehicleId = words.length > 0 ? words[0] : null;
        if (words.length < INIT_MESSAGE_WORD_COUNT) {
            return new WrongMessage(msgId, vehicleId);
        }

        Integer x = IntegerUtils.tryParseInt(words[1]);
        Integer y = IntegerUtils.tryParseInt(words[2]);
        Integer z = IntegerUtils.tryParseInt(words[3]);

        if (x == null || y == null || z == null) {
            return new WrongMessage(msgId, vehicleId);
        }
        if (words.length == INIT_MESSAGE_WORD_COUNT) {
            return new InitMessage(msgId, vehicleId, x, y, z);
        } else if (words.length == MOVE_MESSAGE_WORD_COUNT) {
            Integer mvX = IntegerUtils.tryParseInt(words[4]);
            Integer mvY = IntegerUtils.tryParseInt(words[5]);
            Integer mvZ = IntegerUtils.tryParseInt(words[6]);
            if (mvX != null || mvY != null || mvZ != null) {
                return new MoveMessage(msgId, vehicleId, mvX, mvY, mvZ, x, y, z);
            }
        }

        return new WrongMessage(msgId, vehicleId);
    }
}
