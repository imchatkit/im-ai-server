package com.imai.core.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;

/**
 * 消息回调记录对象 im_callback_record
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("im_callback_record")
public class ImCallbackRecord extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 回调类型
     */
    private Long callbackType;

    /**
     * 回调地址
     */
    private String callbackUrl;

    /**
     * 请求内容
     */
    private String requestBody;

    /**
     * 响应内容
     */
    private String responseBody;

    /**
     *
     */
    private Long callbackStatus;

    /**
     * 重试次数
     */
    private Long retryCount;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    private Long deleted;

    /**
     * 可选 自定义属性，供开发者扩展使用
     */
    private String extras;


}
