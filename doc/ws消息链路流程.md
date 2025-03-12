# WebSocket消息链路流程

## 核心概念

- **消息ID**: 使用雪花算法生成的唯一标识
- **会话序列号(ConversationSeq)**: 基于会话维度的自增序号,从0开始
- **用户同步位点(PTS)**: 每个用户独立的消息接收位点,从0开始自增

# 核心枚举类
im-core\im-entity\src\main\java\com\imai\ws\enums\CmdType.java
im-core\im-entity\src\main\java\com\imai\ws\enums\MsgType.java

## 消息处理流程

### 1. 连接建立
- 客户端通过 `/openapi/v1/user/login` 获取token
- 使用token建立WebSocket连接

### 2. 网关层处理 (im-gateway模块)
- 核心处理类: `im-gateway/src/main/java/com/imai/ws/netty/handler/ImWsMsgHandler.java`
  - 负责接收WebSocket消息
  - 使用线程池异步处理消息
  - 通过dubbo调用消息过滤服务
  - 用户在客户端发送和接收JSON消息，首先通过WebSocket连接到网关模块。

### 3. 消息过滤 (im-core模块的ImMsgFilterHandlerImpl)
- 处理类: `im-core/im-core-server/src/main/java/com/imai/handler/filter/ImMsgFilterHandlerImpl.java`
  - 会话类型定义: `im-core/im-entity/src/main/java/com/imai/ws/enums/ConversationType.java`

  - 根据会话类型进行不同处理
  - 单聊好友类型需要判断双方好友关系等
  - 群聊会话类型需验证用户是否在群内等
  - 验证失败通过WebSocket返回错误码
  ```java
    public enum ConversationType {
        SINGLE(1, "单聊", "Private Chat"),
        GROUP(2, "群聊", "Group Chat"),
        CHATROOM(3, "聊天室", "Chatroom"),
        CHANNEL(4, "频道", "Channel"),
        MILLION_GROUP(5, "万人群聊", "Mass Group Chat"),
        SYSTEM_CONVERSATION(6, "系统对话", "System Conversation"),
        CUSTOM_CONVERSATION(7, "自定义对话", "Custom Conversation"),
        STRANGER_CHAT(8, "陌生人单聊", "Stranger Private Message"), // 可以不是好友
       
        }
  ```

### 4. 消息分发与存储
- 处理类: `im-core/im-core-server/src/main/java/com/imai/handler/store/ImStoreHandlerImpl.java`
  - 生成消息ID和会话序列号
  - 保存到以下数据表:
    - 消息表
    - 收件箱表
    - 已读未读表

### 5. 消息同步
- 处理类: `im-core/im-core-server/src/main/java/com/imai/handler/sync/ImSyncImpl.java`
  - 处理多端同步
  - 更新用户PTS(同步位点)
  - 通过dubbo调用发送服务

### 6. 消息投递
- 处理类: `im-core/dubbo-interface/src/main/java/com/imai/handler/ImSendMsg.java`
  - 位于netty网关内
  - 通过WebSocket发送给接收方
  - 使用dubbo广播模式通知所有网关
    - 原因:分布式部署时接收方可能连接在任意服务器

## 关键数据结构
- 消息体: `im-core/im-entity/src/main/java/com/imai/ws/WebSocketMessage.java`
  - Header: 消息头(版本号、设备信息等)
  - Route: 路由信息(消息类型、发送接收方等)
  - Content: 消息内容
  - Control: 控制信息(持久化、同步等)
  - Options: 可选配置

## 技术栈
- 网络框架: Netty + websocket
- 微服务: Spring Cloud
- RPC框架: Dubbo
- 注册中心: Nacos
- 消息格式: JSON
