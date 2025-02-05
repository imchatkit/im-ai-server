package com.imai.core.domain.bo;

import com.imai.core.domain.ImConversationRecent;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.util.Date;

/**
 * 首页对话列业务对象 im_conversation_recent
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ImConversationRecent.class, reverseConvertGenerate = false)
public class ImConversationRecentBo extends BaseEntity {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 创建者id
     */
    @NotNull(message = "创建者id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long fkUserId;

    /**
     * 会话id
     */
    @NotNull(message = "会话id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long fkConversationId;

    /**
     * 最后一条消息id
     */
    @NotNull(message = "最后一条消息id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long lastMsgId;

    /**
     * 最后一条消息时间，精确到毫秒
     */
    @NotNull(message = "最后一条消息时间，精确到毫秒不能为空", groups = {AddGroup.class, EditGroup.class})
    private Date lastMsgTime;

    /**
     * 未读消息数量
     */
    @NotNull(message = "未读消息数量不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long noReadCount;

    /**
     * 置顶标志 0不置顶  1置顶
     */
    @NotNull(message = "置顶标志 0不置顶  1置顶不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long topFlag;

    /**
     * 置顶时间,用于排序
     */
    @NotNull(message = "置顶时间,用于排序不能为空", groups = {AddGroup.class, EditGroup.class})
    private Date topTime;

    /**
     * 对话移除标志 0没移除  1移除
     */
    @NotNull(message = "对话移除标志 0没移除  1移除不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long removedFlag;

    /**
     * 移除时间,用于判断是否展示
     */
    @NotNull(message = "移除时间,用于判断是否展示不能为空", groups = {AddGroup.class, EditGroup.class})
    private Date removedTime;

    /**
     * 是否有at我的消息 0无,1有
     */
    @NotNull(message = "是否有at我的消息 0无,1有 不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long atMeFlag;

    /**
     * 有at我的消息id
     */
    @NotNull(message = "有at我的消息id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long atMeMsgId;

    /**
     * 会话类型:1单聊,2群聊,3系统通知 5频道
     */
    @NotNull(message = "会话类型:1单聊,2群聊,3系统通知 5频道不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long conversationType;


}
