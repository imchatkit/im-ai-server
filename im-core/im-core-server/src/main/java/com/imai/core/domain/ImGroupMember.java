package com.imai.core.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;

/**
 * 群成员对象 im_group_member
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("im_group_member")
public class ImGroupMember extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 唯一id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 会话ID
     */
    private Long fkConversationId;

    /**
     * 群组表id
     */
    private Long fkGroupId;

    /**
     * 会话成员表id
     */
    private Long fkConversationMemberId;

    /**
     * 用户id
     */
    private Long fkUserId;

    /**
     * 邀请该成员进群的用户
     */
    private Long memberInvitedJoinUser;

    /**
     * 可选	自定义属性，供开发者扩展使用
     */
    private String extras;

    /**
     * 群聊中用户的备注名
     */
    private String userGroupRemarkName;

    /**
     * 角色 1-普通群成员 2-管理员 3-群主
     */
    private Long role;

    /**
     * 群组成员状态  0主动退群  1正常 2被移出群聊
     */
    private Long groupMemberStatus;

    /**
     * 群组成员进群方式: 1创建时加入 2主动扫码加入 3被邀请进入
     */
    private Long groupMemberJoinType;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    private Long deleted;


}
