package com.imai.handler;

public interface ImSendMsg {

    boolean sendMsgToUser(String message, Long userId);


    boolean sendMsgToChannel(String message, String channelId);

}
