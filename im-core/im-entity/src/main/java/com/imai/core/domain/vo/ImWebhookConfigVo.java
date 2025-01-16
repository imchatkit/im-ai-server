package com.imai.core.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.imai.core.domain.ImWebhookConfig;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * Webhook配置视图对象 im_webhook_config
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ImWebhookConfig.class)
public class ImWebhookConfigVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 配置id
     */
    @ExcelProperty(value = "配置id")
    private Long id;

    /**
     * webhook地址
     */
    @ExcelProperty(value = "webhook地址")
    private String webhookUrl;

    /**
     * webhook类型：1消息 2用户 3群组
     */
    @ExcelProperty(value = "webhook类型：1消息 2用户 3群组")
    private Long webhookType;

    /**
     * 密钥
     */
    @ExcelProperty(value = "密钥")
    private String secretKey;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long webhookStatus;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    @ExcelProperty(value = "是否删除 0-未删除 1-已删除")
    private Long deleted;

    /**
     * 可选 自定义属性，供开发者扩展使用
     */
    @ExcelProperty(value = "可选 自定义属性，供开发者扩展使用")
    private String extras;


}
