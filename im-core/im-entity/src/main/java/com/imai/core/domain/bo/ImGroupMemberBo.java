package com.imai.core.domain.bo;

import com.imai.core.domain.ImGroupMember;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;

/**
 * 群成员业务对象 im_group_member
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ImGroupMember.class, reverseConvertGenerate = false)
public class ImGroupMemberBo extends BaseEntity {

    /**
     * 唯一id
     */
    @NotNull(message = "唯一id不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 会话ID
     */
    @NotNull(message = "会话ID不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long fkConversationId;

    /**
     * 群组表id
     */
    @NotNull(message = "群组表id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long fkGroupId;

    /**
     * 会话成员表id
     */
    @NotNull(message = "会话成员表id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long fkConversationMemberId;

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long fkUserId;

    /**
     * 邀请该成员进群的用户
     */
    @NotNull(message = "邀请该成员进群的用户不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long memberInvitedJoinUser;

    /**
     * 可选	自定义属性，供开发者扩展使用
     */
    @NotBlank(message = "可选	自定义属性，供开发者扩展使用不能为空", groups = {AddGroup.class, EditGroup.class})
    private String extras;

    /**
     * 群聊中用户的备注名
     */
    @NotBlank(message = "群聊中用户的备注名不能为空", groups = {AddGroup.class, EditGroup.class})
    private String userGroupRemarkName;

    /**
     * 角色 1-普通群成员 2-管理员 3-群主
     */
    @NotNull(message = "角色 1-普通群成员 2-管理员 3-群主不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long role;

    /**
     * 群组成员状态  0主动退群  1正常 2被移出群聊
     */
    @NotNull(message = "群组成员状态  0主动退群  1正常 2被移出群聊不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long groupMemberStatus;

    /**
     * 群组成员进群方式: 1创建时加入 2主动扫码加入 3被邀请进入
     */
    @NotNull(message = "群组成员进群方式: 1创建时加入 2主动扫码加入 3被邀请进入不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long groupMemberJoinType;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    @NotNull(message = "是否删除 0-未删除 1-已删除不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long deleted;


}
