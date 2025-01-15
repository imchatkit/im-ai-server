package com.imai.core.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;

/**
 * 聊天会话基础对象 im_conversation
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("im_conversation")
public class ImConversation extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 会话类型: 1-单聊 2-群聊 3-系统通知 4-机器人 5频道
     */
    private Long conversationType;

    /**
     * 会话状态: 1-正常 2-禁用 3-删除 4-归档
     */
    private Long conversationStatus;

    /**
     * 是否删除: 0-否 1-是
     */
    private Long deleted;

    /**
     * 可选 自定义属性，供开发者扩展使用
     */
    private String extras;


}
