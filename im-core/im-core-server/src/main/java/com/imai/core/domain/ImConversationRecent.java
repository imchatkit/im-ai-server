package com.imai.core.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;
import java.util.Date;

/**
 * 首页对话列对象 im_conversation_recent
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("im_conversation_recent")
public class ImConversationRecent extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 创建者id
     */
    private Long fkUserId;

    /**
     * 会话id
     */
    private Long fkConversationId;

    /**
     * 最后一条消息id
     */
    private Long lastMsgId;

    /**
     * 最后一条消息时间，精确到毫秒
     */
    private Date lastMsgTime;

    /**
     * 未读消息数量
     */
    private Long noReadCount;

    /**
     * 置顶标志 0不置顶  1置顶
     */
    private Long topFlag;

    /**
     * 置顶时间,用于排序
     */
    private Date topTime;

    /**
     * 对话移除标志 0没移除  1移除
     */
    private Long removedFlag;

    /**
     * 移除时间,用于判断是否展示
     */
    private Date removedTime;

    /**
     * 是否有at我的消息 0无,1有
     */
    private Long atMeFlag;

    /**
     * 有at我的消息id
     */
    private Long atMeMsgId;

    /**
     * 会话类型:1单聊,2群聊,3系统通知 5频道
     */
    private Long conversationType;


}
