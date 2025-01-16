package com.imai.handler;

public interface ImSendMsg {

    boolean sendMsgToUser(String message, String userId);


    boolean sendMsgToChannel(String message, String channelId);

}
