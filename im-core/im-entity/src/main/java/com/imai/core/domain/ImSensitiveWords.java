package com.imai.core.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;

/**
 * 敏感词过滤对象 im_sensitive_words
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("im_sensitive_words")
public class ImSensitiveWords extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 敏感词id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 敏感词
     */
    private String word;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    private Long deleted;

    /**
     * 可选 自定义属性，供开发者扩展使用
     */
    private String extras;


}
