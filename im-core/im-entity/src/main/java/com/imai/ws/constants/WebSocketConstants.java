package com.imai.ws.constants;

/**
 * WebSocket常量定义
 */
public class WebSocketConstants {

    /**
     * 协议版本
     */
    public static final int PROTOCOL_VERSION = 1;

    /**
     * 消息优先级
     */
    public static class Priority {
        public static final int LOWEST = 0;    // 最低优先级
        public static final int LOW = 3;       // 低优先级
        public static final int NORMAL = 5;    // 普通优先级
        public static final int HIGH = 7;      // 高优先级
        public static final int HIGHEST = 9;   // 最高优先级
    }

    /**
     * 消息来源
     */
    public static class Source {
        public static final String WEBSOCKET = "websocket";  // WebSocket客户端
        public static final String SERVER_API = "serverApi"; // 服务端API
        public static final String SYSTEM = "system";        // 系统
    }

    /**
     * 平台类型
     */
    public static class Platform {
        public static final String WEB = "web";           // Web端
        public static final String ANDROID = "android";   // 安卓端
        public static final String IOS = "ios";          // iOS端
        public static final String WINDOWS = "windows";   // Windows端
        public static final String MAC = "mac";          // Mac端
        public static final String LINUX = "linux";      // Linux端
    }

    /**
     * 超时时间(毫秒)
     */
    public static class Timeout {
        public static final int CONNECT = 10000;     // 连接超时
        public static final int READ = 30000;        // 读取超时
        public static final int WRITE = 30000;       // 写入超时
        public static final int IDLE = 180000;       // 空闲超时
        public static final int RECONNECT = 3000;    // 重连间隔
    }

    /**
     * 心跳配置
     */
    public static class Heartbeat {
        public static final int INTERVAL = 30000;    // 心跳间隔(毫秒)
        public static final int TIMEOUT = 90000;     // 心跳超时(毫秒)
    }

    /**
     * 限流配置
     */
    public static class RateLimit {
        public static final int CONNECT_PER_SECOND = 100;    // 每秒连接数
        public static final int MESSAGE_PER_SECOND = 50;     // 每秒消息数
        public static final int BURST_SIZE = 100;            // 突发大小
    }

    /**
     * 消息大小限制(字节)
     */
    public static class MessageSize {
        public static final int MAX_TEXT = 10 * 1024;        // 文本消息
        public static final int MAX_BINARY = 100 * 1024;     // 二进制消息
        public static final int MAX_FRAME = 1024 * 1024;     // 单帧大小
    }

    /**
     * WebSocket子协议
     */
    public static class SubProtocol {
        public static final String JSON = "json";        // JSON协议
        public static final String PROTOBUF = "proto";   // Protobuf协议
        public static final String MSGPACK = "msgpack";  // MessagePack协议
    }

    /**
     * 错误码
     */
    public static class ErrorCode {
        public static final int SUCCESS = 0;             // 成功
        public static final int UNKNOWN = 1000;          // 未知错误
        public static final int INVALID_REQUEST = 1001;  // 无效请求
        public static final int UNAUTHORIZED = 1002;     // 未授权
        public static final int FORBIDDEN = 1003;        // 禁止访问
        public static final int NOT_FOUND = 1004;        // 未找到
        public static final int TIMEOUT = 1005;          // 超时
        public static final int RATE_LIMIT = 1006;       // 限流
        public static final int SERVER_ERROR = 1007;     // 服务器错误
    }
} 