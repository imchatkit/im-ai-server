package com.imai.ws;

import lombok.Data;

/**
 * 富文本内容项
 */
@Data
public class ContentItem {
    private String type;        // 内容类型
    private Integer start;      // 起始位置
    private Integer end;        // 结束位置
    private String uid;         // 用户ID(at类型时使用)
    private String name;        // 用户名称(at类型时使用)
    private String url;         // 资源URL
    private String thumbnail;   // 缩略图URL
    private Integer width;      // 宽度
    private Integer height;     // 高度
    private Long size;         // 文件大小
} 