websocket消息链路流程
这是一个给cursor输入的项目介绍文档,
介绍一条消息发送方到接收方的整体流转的类和方法,以及在spring cloud微服务中经过哪些微服务模块,通过dubbo的rpc调用哪些服务api; 


核心概念:
消息id,由雪花算法生成
会话序列号 ConversationSeq,基于每个会话的维度,从0开始单调+1自增
用户同步位点PTS, 每个用户的维度消息接收位点, 每个用户的消息接收位点是独立的, 从0开始单调+1自增

- 首先 获取token,调用接口`/openapi/v1/user/login`
- 客户端websocket协议连接,maven代码模块:im-gateway,netty核心处理类: 
im-gateway\src\main\java\com\imai\ws\netty\handler\ImWsMsgHandler.java
- 消息数据传输类型采用Json, 相关ws传输数据映射实体的代码在:
im-core\im-entity\src\main\java\com\imai\ws\WebSocketMessage.java
- 然后通过dubbo的rpc调用进行消息过滤, im-ai-server\im-core\im-core-server\src\main\java\com\imai\handler\filter\ImMsgFilterHandlerImpl.java  
根据会话类型走不同的流程, 如果是群聊会话要判断用户是否在群聊中, 不在群聊通过ws协议返回错误码,会话类型枚举: im-core\im-entity\src\main\java\com\imai\ws\enums\ConversationType.java

再经过:
- im-core\im-core-server\src\main\java\com\imai\handler\dispatcher\ImDispatcherImpl.java
要生成消息id,以及会话序列号 ConversationSeq
进行保存数据库, 保存消息表, 收件箱表, 已读未读表;

然后消息同步:

ImDispatcherImpl调用im-core\im-core-server\src\main\java\com\imai\handler\sync\ImSyncImpl.java,
ImSyncImpl的主要功能是保存到多端同步,并且更新PTS

最后在ImSyncImpl通过dubbo调用im-core\dubbo-interface\src\main\java\com\imai\handler\ImSendMsg.java,

ImSendMsg在netty网关内, 会将消息通过ws发送给消息接收方,dubbo有个广播的模式,要通知所有的netty网关, 因为分布式集群部署后, 有很多的机器, 用户的channel我不知道是连接在那个服务器上, 如果dubbo的rpc只调用一台,那么可能找不到接收方连接的服务器
