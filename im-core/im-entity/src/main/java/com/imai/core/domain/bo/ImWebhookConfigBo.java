package com.imai.core.domain.bo;

import com.imai.core.domain.ImWebhookConfig;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;

/**
 * Webhook配置业务对象 im_webhook_config
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ImWebhookConfig.class, reverseConvertGenerate = false)
public class ImWebhookConfigBo extends BaseEntity {

    /**
     * 配置id
     */
    @NotNull(message = "配置id不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * webhook地址
     */
    @NotBlank(message = "webhook地址不能为空", groups = {AddGroup.class, EditGroup.class})
    private String webhookUrl;

    /**
     * webhook类型：1消息 2用户 3群组
     */
    @NotNull(message = "webhook类型：1消息 2用户 3群组不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long webhookType;

    /**
     * 密钥
     */
    @NotBlank(message = "密钥不能为空", groups = {AddGroup.class, EditGroup.class})
    private String secretKey;

    /**
     *
     */
    @NotNull(message = "不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long webhookStatus;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    @NotNull(message = "是否删除 0-未删除 1-已删除不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long deleted;

    /**
     * 可选 自定义属性，供开发者扩展使用
     */
    @NotBlank(message = "可选 自定义属性，供开发者扩展使用不能为空", groups = {AddGroup.class, EditGroup.class})
    private String extras;


}
