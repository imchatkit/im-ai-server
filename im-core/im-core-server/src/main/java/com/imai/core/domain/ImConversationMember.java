package com.imai.core.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;
import java.util.Date;

/**
 * 会话成员对象 im_conversation_member
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("im_conversation_member")
public class ImConversationMember extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 会话ID
     */
    private Long fkConversationId;

    /**
     * 用户id
     */
    private Long fkUserId;

    /**
     * 可选	自定义属性，供开发者扩展使用
     */
    private String extras;

    /**
     * 会话中的备注名
     */
    private String userRemarkName;

    /**
     * 角色: 1-普通成员 2-管理员 3-群主 4-访客 5-黑名单
     */
    private Long role;

    /**
     * 免打扰开关 0-关闭 1开启
     */
    private Long disturbFlag;

    /**
     * 置顶开关 0-关闭 1开启
     */
    private Long topFlag;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    private Long deleted;

    /**
     * 屏蔽@全体成员 0-不屏蔽 1-屏蔽
     */
    private Long muteAtAll;

    /**
     * 禁言状态: 1-正常发言 2-永久禁言 3-限时禁言
     */
    private Long muted;

    /**
     * 禁言结束时间
     */
    private Date muteEndTime;


}
