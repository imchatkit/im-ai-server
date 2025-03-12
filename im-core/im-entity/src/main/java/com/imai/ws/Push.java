package com.imai.ws;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import lombok.Builder;
/**
 * 推送控制
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Push {
    private Boolean enable;         // 是否开启推送
    private String title;          // 推送标题
    private String content;        // 推送内容
    private Map<String, Object> payload;  // 推送自定义数据
    private Integer badge;         // 角标数
    private String sound;          // 推送声音
    private String channelId;      // 推送通道
}
