package com.imai.ws;

import lombok.Data;
import java.util.List;
import java.util.Map;

/**
 * 消息内容
 */
@Data
public class Content {
    private String text;                    // 文本内容
    private List<ContentItem> items;        // 富文本内容
    private Mentions mentions;              // @功能
    private Quote quote;                    // 引用消息
    private Map<String, Object> extension;  // 消息扩展字段
} 