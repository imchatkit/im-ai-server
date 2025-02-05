websocket消息链路流程
这是一个给cursor输入的项目介绍文档,
介绍一条消息发送方到接收方的整体流转的类和方法,以及在spring cloud微服务中经过哪些微服务模块,通过dubbo的rpc调用哪些服务api; 


- 首先 获取token,调用接口`/openapi/v1/user/login`
- 客户端websocket协议连接,maven代码模块:im-gateway,netty核心处理类: 
im-gateway\src\main\java\com\imai\ws\netty\handler\ImWsMsgHandler.java
- 消息数据传输类型采用Json, 相关ws传输数据映射实体的代码在:
im-core\im-entity\src\main\java\com\imai\ws\WebSocketMessage.java
- 然后通过dubbo的rpc调用进行消息过滤, im-ai-server\im-core\im-core-server\src\main\java\com\imai\handler\filter\ImMsgFilterHandlerImpl.java  
根据会话类型走不同的流程, 如果是群聊会话要判断用户是否在群聊中, 不在群聊通过ws协议返回错误码,会话类型枚举: im-core\im-entity\src\main\java\com\imai\ws\enums\ConversationType.java

- im-core\im-core-server\src\main\java\com\imai\handler\dispatcher\ImDispatcherImpl.java

