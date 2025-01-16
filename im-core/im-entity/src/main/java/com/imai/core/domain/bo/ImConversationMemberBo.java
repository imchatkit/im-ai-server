package com.imai.core.domain.bo;

import com.imai.core.domain.ImConversationMember;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.util.Date;

/**
 * 会话成员业务对象 im_conversation_member
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ImConversationMember.class, reverseConvertGenerate = false)
public class ImConversationMemberBo extends BaseEntity {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 会话ID
     */
    @NotNull(message = "会话ID不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long fkConversationId;

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long fkUserId;

    /**
     * 可选	自定义属性，供开发者扩展使用
     */
    @NotBlank(message = "可选	自定义属性，供开发者扩展使用不能为空", groups = {AddGroup.class, EditGroup.class})
    private String extras;

    /**
     * 会话中的备注名
     */
    @NotBlank(message = "会话中的备注名不能为空", groups = {AddGroup.class, EditGroup.class})
    private String userRemarkName;

    /**
     * 角色: 1-普通成员 2-管理员 3-群主 4-访客 5-黑名单
     */
    @NotNull(message = "角色: 1-普通成员 2-管理员 3-群主 4-访客 5-黑名单不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long role;

    /**
     * 免打扰开关 0-关闭 1开启
     */
    @NotNull(message = "免打扰开关 0-关闭 1开启不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long disturbFlag;

    /**
     * 置顶开关 0-关闭 1开启
     */
    @NotNull(message = "置顶开关 0-关闭 1开启不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long topFlag;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    @NotNull(message = "是否删除 0-未删除 1-已删除不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long deleted;

    /**
     * 屏蔽@全体成员 0-不屏蔽 1-屏蔽
     */
    @NotNull(message = "屏蔽@全体成员 0-不屏蔽 1-屏蔽不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long muteAtAll;

    /**
     * 禁言状态: 1-正常发言 2-永久禁言 3-限时禁言
     */
    @NotNull(message = "禁言状态: 1-正常发言 2-永久禁言 3-限时禁言不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long muted;

    /**
     * 禁言结束时间
     */
    @NotNull(message = "禁言结束时间不能为空", groups = {AddGroup.class, EditGroup.class})
    private Date muteEndTime;


}
