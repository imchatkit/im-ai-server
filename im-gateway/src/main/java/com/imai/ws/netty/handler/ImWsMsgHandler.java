package com.imai.ws.netty.handler;


import com.imai.dispatcher.ImDispatcher;
import com.imai.ws.netty.user.SendMsgUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: wei
 * @Date: 2024/3/13 自定义handler
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class ImWsMsgHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    //    @Autowired
//    private ReceiverProducer receiverProducer;
    @Autowired
    private SendMsgUtil sendMsgUtil;

    @DubboReference
    private ImDispatcher imDispatcher;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
//        Long userId = ctx.channel().attr(ChannelAttributes.USER_ID).get();
//        String device = ctx.channel().attr(ChannelAttributes.DEVICE_TYPE).get();
//        String channelId = ctx.channel().id().asLongText();
//
//        log.info("[WsRead]_userId:{},channelId:{},device:{},\n-data:{}", userId, channelId, device, msg.text());

//        ReqMessageData reqMessageData = JsonUtils.parseObject(msg.text(), ReqMessageData.class);
//        MqMsgData mqMsgData = new MqMsgData();
//        mqMsgData.setReqMessageData(reqMessageData);
//        mqMsgData.setChannelId(channelId);
//        mqMsgData.setFrom(userId);
//        receiverProducer.sendMessage(mqMsgData);

        log.info("[WsRead]:{}", msg.text());
        imDispatcher.dispatch(msg.text());
        sendMsgUtil.send("res:" + msg.text(), ctx.channel());
    }


}
