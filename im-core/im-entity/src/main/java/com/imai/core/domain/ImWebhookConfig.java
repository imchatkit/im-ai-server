package com.imai.core.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;

/**
 * Webhook配置对象 im_webhook_config
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("im_webhook_config")
public class ImWebhookConfig extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 配置id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * webhook地址
     */
    private String webhookUrl;

    /**
     * webhook类型：1消息 2用户 3群组
     */
    private Long webhookType;

    /**
     * 密钥
     */
    private String secretKey;

    /**
     *
     */
    private Long webhookStatus;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    private Long deleted;

    /**
     * 可选 自定义属性，供开发者扩展使用
     */
    private String extras;


}
