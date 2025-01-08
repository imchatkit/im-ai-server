package com.imai.core.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.imai.core.domain.ImGroupMember;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 群成员视图对象 im_group_member
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ImGroupMember.class)
public class ImGroupMemberVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 唯一id
     */
    @ExcelProperty(value = "唯一id")
    private Long id;

    /**
     * 会话ID
     */
    @ExcelProperty(value = "会话ID")
    private Long fkConversationId;

    /**
     * 群组表id
     */
    @ExcelProperty(value = "群组表id")
    private Long fkGroupId;

    /**
     * 会话成员表id
     */
    @ExcelProperty(value = "会话成员表id")
    private Long fkConversationMemberId;

    /**
     * 用户id
     */
    @ExcelProperty(value = "用户id")
    private Long fkUserId;

    /**
     * 邀请该成员进群的用户
     */
    @ExcelProperty(value = "邀请该成员进群的用户")
    private Long memberInvitedJoinUser;

    /**
     * 可选	自定义属性，供开发者扩展使用
     */
    @ExcelProperty(value = "可选	自定义属性，供开发者扩展使用")
    private String extras;

    /**
     * 群聊中用户的备注名
     */
    @ExcelProperty(value = "群聊中用户的备注名")
    private String userGroupRemarkName;

    /**
     * 角色 1-普通群成员 2-管理员 3-群主
     */
    @ExcelProperty(value = "角色 1-普通群成员 2-管理员 3-群主")
    private Long role;

    /**
     * 群组成员状态  0主动退群  1正常 2被移出群聊
     */
    @ExcelProperty(value = "群组成员状态  0主动退群  1正常 2被移出群聊")
    private Long groupMemberStatus;

    /**
     * 群组成员进群方式: 1创建时加入 2主动扫码加入 3被邀请进入
     */
    @ExcelProperty(value = "群组成员进群方式: 1创建时加入 2主动扫码加入 3被邀请进入")
    private Long groupMemberJoinType;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    @ExcelProperty(value = "是否删除 0-未删除 1-已删除")
    private Long deleted;


}
