package com.fhr.uavhomework.msgdecode;

import com.fhr.uavhomework.message.IMessage;

/**
 * @author FanHuaran
 * @description 消息解码策略接口
 * @create 2018-04-05 15:07
 **/
public interface IMessageDecodeStrategy {
    /**
     * 解码消息
     *
     * @param msgId
     * @param str
     * @return
     */
    IMessage decodeMessage(int msgId, String str);
}
