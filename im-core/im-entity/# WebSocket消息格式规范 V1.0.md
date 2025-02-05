# WebSocket消息格式规范 V1.0

## 更新记录

```
2024.12.27
- 初始化文档
- 定义完整消息结构
- 支持多租户、多通道接入
- 丰富的业务扩展能力
```

## 一、消息基础结构

### 1.1 请求消息格式
```json
{
    "header": {
        "ver": 1,                // 协议版本号
        "seq": "c-uuid-1",       // 客户端序列号
        "app_id": "app_123",     // 应用ID
        "tenant_id": "tenant_1", // 租户ID(saas多租户支持)
        "platform": "ios",       // 平台类型
        "device_id": "dev_123",  // 设备ID
        "timestamp": 1648567456000 //客户端发送时间
    },

    "route": {
        "cmd": 1,               // 命令类型
        "type": 1,              // 消息类型
        "room": "12345",        // 会话ID
        "room_type": 1,         // 会话类型(1单聊/2群聊/3聊天室等)
        "from": "uid1",         // 发送者ID
        "to": "uid2",           // 接收者ID(单聊必填)
        "target": ["uid2"],     // 目标用户列表(群聊定向消息)
        "source": "serverApi"         // 消息来源(serverApi/websocket)
    },

    "content": {
        "text": "你好",          // 文本内容
        "items": [],            // 富文本内容
        "mentions": {           // @功能
            "all": false,       // 是否@所有人
            "uids": ["uid2"],   // @的用户列表
            "push_content": "张三在群里@了你" // @推送内容
        },
        "quote": {              // 引用消息
            "msg_id": "msg_1",
            "text": "原消息"
        },
        "extension": {          // 消息扩展字段
            "title": "标题",
            "description": "描述",
            "image": "http://...",
            "url": "http://..."
        }
    },

    "control": {
        // === 消息控制 ===
        "persistent": true,     // 是否持久化
        "sync_device": true,    // 是否多端同步
        "priority": 1,          // 消息优先级(0-9)
        "ttl": 86400,          // 消息有效期(秒)
        
        // === 已读控制 ===
        "need_receipt": true,   // 是否需要回执
        "receipt_type": 1,      // 回执类型(1送达/2已读)
        "read_timeout": 7200,   // 已读超时时间(秒)
        
        // === 推送控制 ===
        "push": {
            "enable": true,           // 是否开启推送
            "title": "新消息",        // 推送标题
            "content": "张三: 你好",   // 推送内容
            "payload": {},            // 推送自定义数据
            "badge": 1,               // 角标数
            "sound": "default",       // 推送声音
            "channel_id": "chat"      // 推送通道
        },

        // === 安全控制 ===
        "security": {
            "check_sensitive": true,  // 是否检查敏感词
            "check_spam": true,       // 是否检查垃圾消息
            "encryption": true,       // 是否加密
            "sign": "xxx"            // 消息签名
        },

        // === 回调控制(新增) ===
        "callback": {
            "enable": true,           // 是否开启回调
            "url": "http://api.example.com/callback",  // 回调地址
            "events": [               // 需要回调的事件类型
                "msg_received",       // 消息送达
                "msg_read",          // 消息已读
                "msg_recall",        // 消息撤回
                "msg_deleted"        // 消息删除
            ],
            "timeout": 3000,         // 回调超时时间(毫秒)
            "retry": {               // 重试配置
                "max_times": 3,      // 最大重试次数
                "interval": 1000     // 重试间隔(毫秒)
            },
            "security": {            // 回调安全配置
                "token": "xxx",      // 回调验证token
                "sign_type": "md5"   // 签名类型
            }
        }
    },

    "options": {
        // === 业务配置 ===
        "biz_type": "crm",          // 业务类型
        "biz_id": "case_123",       // 业务ID
        "conversation": {
            "top": false,           // 是否置顶会话
            "mute": false,          // 是否免打扰
            "unread": true          // 是否计入未读
        },
        
        // === 自定义配置 ===
        "custom": {                 // 自定义业务字段
            "order_id": "123",
            "product_id": "456"
        },
        "extra": {}                // 额外扩展字段
    }
}
```

### 1.2 响应消息格式
```json
{
    "header": {
        "ver": 1,
        "seq": "c-uuid-1",        // 原始序列号
        "timestamp": 1648567456000
    },
    
    "status": {
        "code": 200,              // 状态码
        "message": "success",      // 状态描述
        "detail": {}              // 详细错误信息
    },

    "data": {
        "msg_id": "s-msg-1",      // 服务端消息ID
        "room_seq": 100,          // 会话序列号
        "user_pts": 1000,         // 用户序列号
        "timestamp": 1648567456000,
        
        // 消息处理结果
        "results": {
            "sensitive_words": ["敏感词1"],  // 命中的敏感词
            "replaced_text": "***",         // 替换后的文本
            "spam_score": 0.1,             // 垃圾消息分数
            "push_results": []             // 推送结果
        }
    }
}
```

## 二、消息收发示例

### 2.1 文本消息

#### 发送请求
```json
{
    "header": {
        "ver": 1,
        "seq": "c-123",
        "app_id": "app_1",
        "tenant_id": "tenant_1",
        "platform": "ios",
        "timestamp": 1648567456000
    },
    "route": {
        "cmd": 1,
        "type": 1,
        "room": "12345",
        "room_type": 1,
        "from": "uid1",
        "to": "uid2"
    },
    "content": {
        "text": "你好",
        "items": []
    },
    "control": {
        "persistent": true,
        "sync_device": true,
        "push": {
            "enable": true,
            "content": "张三: 你好"
        }
    }
}
```

#### 响应消息
```json
{
    "header": {
        "ver": 1,
        "seq": "c-123",
        "timestamp": 1648567456000
    },
    "status": {
        "code": 200,
        "message": "success"
    },
    "data": {
        "msg_id": "s-msg-1",
        "room_seq": 100,
        "user_pts": 1000
    }
}
```


### 2.2 图片消息

#### 发送请求
```json
{
    "header": {
        "ver": 1,
        "seq": "c-124",
        "app_id": "app_1",
        "platform": "ios"
    },
    "route": {
        "cmd": 1,
        "type": 2,
        "room": "12345",
        "from": "uid1",
        "to": "uid2"
    },
    "content": {
        "text": "[图片]",
        "items": [{
            "type": "image",
            "url": "http://...",
            "thumbnail": "http://...",
            "width": 720,
            "height": 1280,
            "size": 1024000
        }]
    },
    "control": {
        "persistent": true,
        "push": {
            "enable": true,
            "content": "张三发送了一张图片"
        }
    }
}
```

### 2.3 群@消息

#### 发送请求
```json
{
    "header": {
        "ver": 1,
        "seq": "c-125",
        "app_id": "app_1"
    },
    "route": {
        "cmd": 2,
        "type": 1,
        "room": "group_123",
        "room_type": 2,
        "from": "uid1"
    },
    "content": {
        "text": "@张三 @李四 大家好",
        "items": [
            {
                "type": "at",
                "start": 0,
                "end": 3,
                "uid": "uid2",
                "name": "张三"
            },
            {
                "type": "at",
                "start": 4,
                "end": 7,
                "uid": "uid3",
                "name": "李四"
            }
        ],
        "mentions": {
            "uids": ["uid2", "uid3"],
            "push_content": "张三在群里@了你"
        }
    },
    "control": {
        "push": {
            "enable": true,
            "target": ["uid2", "uid3"]
        }
    }
}
```

### 2.4 消息已读

#### 发送请求
```json
{
    "header": {
        "ver": 1,
        "seq": "c-126",
        "app_id": "app_1"
    },
    "route": {
        "cmd": 22,
        "type": 53,
        "room": "12345",
        "from": "uid1"
    },
    "content": {
        "msg_id": "s-msg-1",
        "timestamp": 1648567456000
    }
}
```

### 2.5 消息撤回

#### 发送请求
```json
{
    "header": {
        "ver": 1,
        "seq": "c-127",
        "app_id": "app_1"
    },
    "route": {
        "cmd": 11,
        "type": 52,
        "room": "12345",
        "from": "uid1"
    },
    "content": {
        "msg_id": "s-msg-1"
    },
    "control": {
        "push": {
            "enable": true,
            "content": "张三撤回了一条消息"
        }
    }
}
```

### 2.6 正在输入状态

#### 发送请求
```json
{
    "header": {
        "ver": 1,
        "seq": "c-128",
        "app_id": "app_1"
    },
    "route": {
        "cmd": 81,              // TYPING_STATUS
        "type": 51,             // TYPING
        "room": "12345",
        "from": "uid1",
        "to": "uid2"
    },
    "content": {
        "typing_status": 1      // 1:开始输入 2:结束输入
    }
}
```

### 2.7 WebRTC信令消息

#### 视频通话邀请
```json
{
    "header": {
        "ver": 1,
        "seq": "c-129",
        "app_id": "app_1"
    },
    "route": {
        "cmd": 101,            // WEBRTC_INVITE
        "type": 61,            // WEBRTC_SIGNAL
        "room": "12345",
        "from": "uid1",
        "to": "uid2"
    },
    "content": {
        "call_id": "call_123",     // 通话ID
        "call_type": 1,            // 1:音频 2:视频
        "media_type": 2,           // 1:音频 2:视频 3:屏幕共享
        "sdp": "offer sdp...",     // SDP offer
        "candidates": []           // ICE candidates
    },
    "control": {
        "push": {
            "enable": true,
            "title": "视频通话",
            "content": "张三邀请你进行视频通话",
            "payload": {
                "call_id": "call_123",
                "call_type": 2
            }
        },
        "ttl": 30                 // 30秒超时
    }
}
```

#### 通话应答
```json
{
    "header": {
        "ver": 1,
        "seq": "c-130",
        "app_id": "app_1"
    },
    "route": {
        "cmd": 102,            // WEBRTC_ANSWER
        "type": 61,            // WEBRTC_SIGNAL
        "room": "12345",
        "from": "uid2",
        "to": "uid1"
    },
    "content": {
        "call_id": "call_123",
        "answer_type": 1,         // 1:接受 2:拒绝 3:忙线
        "sdp": "answer sdp...",   // SDP answer
        "candidates": []          // ICE candidates
    }
}
```

#### ICE候选者更新
```json
{
    "header": {
        "ver": 1,
        "seq": "c-131",
        "app_id": "app_1"
    },
    "route": {
        "cmd": 103,            // WEBRTC_CANDIDATE
        "type": 61,            // WEBRTC_SIGNAL
        "room": "12345",
        "from": "uid1",
        "to": "uid2"
    },
    "content": {
        "call_id": "call_123",
        "candidates": [{
            "sdpMid": "0",
            "sdpMLineIndex": 0,
            "candidate": "candidate:..."
        }]
    }
}
```

#### 通话状态更新
```json
{
    "header": {
        "ver": 1,
        "seq": "c-132",
        "app_id": "app_1"
    },
    "route": {
        "cmd": 104,            // WEBRTC_STATUS
        "type": 61,            // WEBRTC_SIGNAL
        "room": "12345",
        "from": "uid1",
        "to": "uid2"
    },
    "content": {
        "call_id": "call_123",
        "status": 1,           // 1:已接通 2:结束通话 3:通话中断
        "duration": 60,        // 通话时长(秒)
        "reason": "normal"     // 结束原因
    }
}
```

## 三、命令类型定义(CMD)

```java
public enum CmdType {
    // === 消息命令 ===
    SINGLE_CHAT(1, "单聊消息"),
    GROUP_CHAT(2, "群聊消息"),
    CHATROOM_CHAT(3, "聊天室消息"),
    BROADCAST(4, "广播消息"),
    CUSTOM_CHAT(5, "自定义消息"),
    
    // === 消息操作 ===
    MSG_RECALL(11, "撤回消息"),
    MSG_DELETE(12, "删除消息"),
    MSG_EDIT(13, "编辑消息"),
    MSG_REACTION(14, "消息回应"),
    
    // === 已读命令 ===
    MSG_RECEIPT(21, "消息回执"),
    MSG_READ(22, "消息已读"),
    READ_ALL(23, "全部已读"),
    READ_CANCEL(24, "取消已读"),
    
    // === 会话命令 ===
    CONVERSATION_CREATE(31, "创建会话"),
    CONVERSATION_DELETE(32, "删除会话"),
    CONVERSATION_UPDATE(33, "更新会话"),
    CONVERSATION_SYNC(34, "同步会话"),
    
    // === 群组命令 ===
    GROUP_CREATE(41, "创建群组"),
    GROUP_JOIN(42, "加入群组"),
    GROUP_LEAVE(43, "退出群组"),
    GROUP_DISMISS(44, "解散群组"),
    GROUP_UPDATE(45, "更新群组"),
    GROUP_MUTE(46, "群组禁言"),
    
    // === 用户命令 ===
    USER_MUTE(51, "用户禁言"),
    USER_BLOCK(52, "用户拉黑"),
    USER_ONLINE(53, "用户上线"),
    USER_OFFLINE(54, "用户离线"),
    USER_SETTING(55, "用户设置"),
    
    // === 关系命令 ===
    FRIEND_ADD(61, "添加好友"),
    FRIEND_DELETE(62, "删除好友"),
    FRIEND_UPDATE(63, "更新好友"),
    FRIEND_BLOCK(64, "拉黑好友"),
    
    // === 同步命令 ===
    SYNC_MSG(71, "同步消息"),
    SYNC_CONVERSATION(72, "同步会话"),
    SYNC_GROUP(73, "同步群组"),
    SYNC_FRIEND(74, "同步好友"),
    
    // === 系统命令 ===
    SYS_NOTIFY(91, "系统通知"),
    CUSTOM_NOTIFY(92, "自定义通知"),
    ERROR(99, "错误消息"),
    
    // === 输入状态命令 ===
    TYPING_STATUS(81, "输入状态"),
    TYPING_CANCEL(82, "取消输入"),
    
    // === WebRTC信令命令 ===
    WEBRTC_INVITE(101, "音视频邀请"),
    WEBRTC_ANSWER(102, "音视频应答"),
    WEBRTC_CANDIDATE(103, "候选者更新"),
    WEBRTC_STATUS(104, "通话状态"),
    WEBRTC_SWITCH(105, "切换音视频"),
    WEBRTC_QUALITY(106, "质量报告"),
    
    // === 屏幕共享命令 ===
    SCREEN_SHARE_START(111, "开始共享"),
    SCREEN_SHARE_STOP(112, "停止共享"),
    SCREEN_SHARE_QUALITY(113, "共享质量");
}
```

## 四、消息类型定义(TYPE)

```java
public enum MsgType {
    // === 基础消息 ===
    TEXT(1, "文本消息"),
    IMAGE(2, "图片消息"),
    AUDIO(3, "语音消息"),
    VIDEO(4, "视频消息"),
    FILE(5, "文件消息"),
    LOCATION(6, "位置消息"),
    CONTACT(7, "名片消息"),
    
    // === 富媒体消息 ===
    RICH_TEXT(11, "富文本消息"),
    MARKDOWN(12, "Markdown消息"),
    HTML(13, "HTML消息"),
    
    // === 复合消息 ===
    QUOTE(21, "引用消息"),
    FORWARD(22, "转发消息"),
    MERGE(23, "合并消息"),
    CARD(24, "卡片消息"),
    
    // === 互动消息 ===
    REACTION(31, "表情回应"),
    VOTE(32, "投票消息"),
    RED_PACKET(33, "红包消息"),
    GAME(34, "小游戏消息"),
    
    // === 通知消息 ===
    GROUP_NOTIFY(41, "群通知"),
    FRIEND_NOTIFY(42, "好友通知"),
    SYSTEM_NOTIFY(43, "系统通知"),
    CUSTOM_NOTIFY(44, "自定义通知"),
    
    // === 状态消息 ===
    TYPING(51, "正在输入"),
    TYPING_END(52, "结束输入"),
    
    // === WebRTC信令类型 ===
    WEBRTC_SIGNAL(61, "音视频信令"),
    WEBRTC_STATUS(62, "通话状态"),
    WEBRTC_QUALITY(63, "质量数据"),
    
    // === 屏幕共享类型 ===
    SCREEN_SHARE(71, "屏幕共享"),
    SCREEN_CONTROL(72, "共享控制"),
    
    // === 自定义消息 ===
    CUSTOM(91, "自定义消息"),
    TEMPLATE(92, "模板消息");
}
```

## 五、特殊说明

### 5.1 多租户支持
1. 每个租户通过app_id和tenant_id进行隔离
2. 支持租户级别的配置管理:
   - 功能开关配置
   - 安全策略配置
   - 消息规则配置
   - 计费策略配置

### 5.2 消息可靠性
1. 所有请求都有唯一seq标识
2. 重要消息支持ACK机制
3. 支持消息重发机制
4. 支持离线消息同步
5. 支持多端消息同步

### 5.3 消息排序
1. room_seq: 会话维度的消息序号
2. user_pts: 用户维度的同步序号
3. timestamp: 消息时间戳
4. 优先使用room_seq进行排序

### 5.4 安全机制
1. 应用级别的安全隔离
2. 支持消息加密传输
3. 支持消息防篡改签名
4. 支持敏感词过滤
5. 支持垃圾消息识别
6. 支持频率限制

### 5.5 扩展能力
1. 支持自定义消息类型
2. 支持自定义通知模板
3. 支持灵活的业务配置
4. 支持丰富的扩展字段
5. 支持多样的推送配置

### 5.6 业务集成
1. 支持多种接入方式:
   - WebSocket接入
   - HTTP API接入
   - SDK接入
2. 支持多种部署方式:
   - 公有云服务
   - 专属云服务
   - 私有化部署
3. 支持多种业务场景:
   - 社交聊天
   - 客服系统
   - 团队协作
   - 直播互动
   - 游戏社交

### 5.7 WebRTC通话支持

1. **信令交换**
   - 支持标准WebRTC信令流程
   - 支持ICE打洞协商
   - 支持音视频编解码协商

2. **通话控制**
   - 支持1v1音视频通话
   - 支持多人音视频会议
   - 支持屏幕共享
   - 支持音视频设备切换

3. **质量保证**
   - 支持通话质量监控
   - 支持弱网适应
   - 支持通话数据统计
   - 支持实时网络诊断


4. **安全机制**
   - 端到端加密
   - 通话鉴权控制
   - 敏感内容审核
   - 通话行为监控

### 5.8 回调机制

1. **回调配置**
   - 支持应用级别默认配置
   - 支持消息级别单独配置
   - 支持多个回调地址
   - 支持事件过滤

2. **回调安全**
   - 支持回调地址白名单
   - 支持签名验证
   - 支持Token认证
   - IP限制支持

3. **可靠性保证**
   - 支持失败重试
   - 超时控制
   - 回调结果确认
   - 回调日志记录

4. **回调事件类型**
   - 消息事件(发送、送达、已读、撤回、删除)
   - 会话事件(创建、更新、删除)
   - 群组事件(创建、解散、成员变更)
   - 关系链事件(好友添加、删除)
   - 用户事件(在线状态变更)
   - 音视频事件(通话状态变更)

5. **回调数据格式**
```json
{
    "event": "msg_received",        // 事件类型
    "timestamp": 1648567456000,     // 事件时间
    "data": {                       // 事件数据
        "msg_id": "msg_123",        // 消息ID
        "app_id": "app_1",          // 应用ID
        "from": "uid1",             // 发送者
        "to": "uid2",               // 接收者
        "room": "12345",            // 会话ID
        "status": "success"         // 处理状态
    },
    "security": {                   // 安全信息
        "token": "xxx",             // 回调token
        "sign": "yyy"              // 签名
    }
}
```
