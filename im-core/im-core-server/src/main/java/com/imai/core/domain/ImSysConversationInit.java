package com.imai.core.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;

/**
 * 系统会话初始化对象 im_sys_conversation_init
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("im_sys_conversation_init")
public class ImSysConversationInit extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 会话类型
     */
    private Long conversationType;

    /**
     * 会话名称
     */
    private String conversationName;

    /**
     * 可选	自定义属性，供开发者扩展使用。
     */
    private String extras;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    private Long deleted;


}
