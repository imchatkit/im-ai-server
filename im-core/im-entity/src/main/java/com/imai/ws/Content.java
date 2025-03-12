package com.imai.ws;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


/**
 * 消息内容
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Content implements Serializable {
    private String text;                    // 文本内容
    private List<ContentItem> items;        // 富文本内容
    private Mentions mentions;              // @功能
    private Quote quote;                    // 引用消息
    private String extension;               // 消息扩展字段
}
