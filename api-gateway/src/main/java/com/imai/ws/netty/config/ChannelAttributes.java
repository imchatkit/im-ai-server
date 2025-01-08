package com.imai.ws.netty.config;

import io.netty.util.AttributeKey;

/**
 * @author wei
 * @description: 自定义的Channel属性
 * @date 2024/11/4 15:20
 */
public final class ChannelAttributes {

    // 定义常用的 AttributeKey
    public static final AttributeKey<String> DEVICE_TYPE = AttributeKey.valueOf("d_t");
    public static final AttributeKey<Long> USER_ID = AttributeKey.valueOf("u_i");

    // 定义一个私有的构造方法，防止实例化
    private ChannelAttributes() {
        throw new AssertionError("Utility class should not be instantiated");
    }
}
