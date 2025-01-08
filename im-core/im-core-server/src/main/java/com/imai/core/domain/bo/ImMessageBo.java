package com.imai.core.domain.bo;

import com.imai.core.domain.ImMessage;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;

/**
 * 消息存储业务对象 im_message
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ImMessage.class, reverseConvertGenerate = false)
public class ImMessageBo extends BaseEntity {

    /**
     * 消息id
     */
    @NotNull(message = "消息id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 会话id
     */
    @NotNull(message = "会话id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long fkConversationId;

    /**
     * 发送者id
     */
    @NotNull(message = "发送者id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long fkFromUserId;

    /**
     * 会话粒度单调自增序列号
     */
    @NotNull(message = "会话粒度单调自增序列号不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long conversationSeq;

    /**
     * 客户端本地消息id
     */
    @NotBlank(message = "客户端本地消息id不能为空", groups = { AddGroup.class, EditGroup.class })
    private String localMsgId;

    /**
     * 消息类型
     */
    @NotNull(message = "消息类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long msgType;

    /**
     * 载荷内容如图片视频卡片等不同的参数
     */
    @NotBlank(message = "载荷内容如图片视频卡片等不同的参数不能为空", groups = { AddGroup.class, EditGroup.class })
    private String payload;

    /**
     * 媒体文件地址
     */
    @NotBlank(message = "媒体文件地址不能为空", groups = { AddGroup.class, EditGroup.class })
    private String mediaUrl;

    /**
     * 文字内容
     */
    @NotBlank(message = "文字内容不能为空", groups = { AddGroup.class, EditGroup.class })
    private String msgText;

    /**
     * 被@用户列表 格式:[{userId:1,name:"张三"},{userId:2,name:"李四"}]
     */
    @NotBlank(message = "被@用户列表 ", groups = { AddGroup.class, EditGroup.class })
    private String atUsers;

    /**
     * 消息状态 1正常 2已撤回
     */
    @NotNull(message = "消息状态 1正常 2已撤回不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long msgStatus;

    /**
     * 接收人,多人用英文逗号分隔-群内指定人员可见场景
     */
    @NotBlank(message = "接收人,多人用英文逗号分隔-群内指定人员可见场景不能为空", groups = { AddGroup.class, EditGroup.class })
    private String receiverOnly;

    /**
     * 接收方总人数
     */
    @NotNull(message = "接收方总人数不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long receiverCount;

    /**
     * 被引用次数
     */
    @NotNull(message = "被引用次数不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long refCount;

    /**
     * 引用类型:0原创,1回复,2转发,3引用
     */
    @NotNull(message = "引用类型:0原创,1回复,2转发,3引用不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long refType;

    /**
     * 会话根消息ID(第一条被引用的消息)
     */
    @NotNull(message = "会话根消息ID(第一条被引用的消息)不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long rootMsgId;

    /**
     * 直接引用的消息ID
     */
    @NotNull(message = "直接引用的消息ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long parentMsgId;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    @NotNull(message = "是否删除 0-未删除 1-已删除不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long deleted;

    /**
     * @全体成员标记 0-否 1-是
     */
    @NotNull(message = "@全体成员标记 0-否 1-是不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long atAll;

    /**
     * 可选 自定义属性，供开发者扩展使用
     */
    @NotBlank(message = "可选 自定义属性，供开发者扩展使用不能为空", groups = { AddGroup.class, EditGroup.class })
    private String extras;

    /**
     * 应用ID
     */
    @NotBlank(message = "应用ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private String appId;

    /**
     * 会话类型:1单聊,2群聊,3聊天室
     */
    @NotNull(message = "会话类型:1单聊,2群聊,3聊天室不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long conversationType;

    /**
     * 接收者ID(单聊必填)
     */
    @NotNull(message = "接收者ID(单聊必填)不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long toUid;

    /**
     * 命令类型
     */
    @NotNull(message = "命令类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long cmd;

    /**
     * 是否持久化
     */
    @NotNull(message = "是否持久化不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long persistent;

    /**
     * 消息优先级
     */
    @NotNull(message = "消息优先级不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long priority;

    /**
     * 是否需要回执
     */
    @NotNull(message = "是否需要回执不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long needReceipt;


}
