package com.imai.core.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.tenant.core.TenantEntity;

import java.io.Serial;

/**
 * 消息存储对象 im_message
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("im_message")
public class ImMessage extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 消息id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 会话id
     */
    private Long fkConversationId;

    /**
     * 发送者id
     */
    private Long fkFromUserId;

    /**
     * 会话粒度单调自增序列号
     */
    private Long conversationSeq;

    /**
     * 客户端本地消息id
     */
    private String localMsgId;

    /**
     * 消息类型
     */
    private Long msgType;

    /**
     * 载荷内容如图片视频卡片等不同的参数
     */
    private String payload;

    /**
     * 媒体文件地址
     */
    private String mediaUrl;

    /**
     * 文字内容
     */
    private String msgText;

    /**
     * 被@用户列表 格式:[{userId:1,name:"张三"},{userId:2,name:"李四"}]
     */
    private String atUsers;

    /**
     * 消息状态 1正常 2已撤回
     */
    private Long msgStatus;

    /**
     * 接收人,多人用英文逗号分隔-群内指定人员可见场景
     */
    private String receiverOnly;

    /**
     * 接收方总人数
     */
    private Long receiverCount;

    /**
     * 被引用次数
     */
    private Long refCount;

    /**
     * 引用类型:0原创,1回复,2转发,3引用
     */
    private Long refType;

    /**
     * 会话根消息ID(第一条被引用的消息)
     */
    private Long rootMsgId;

    /**
     * 直接引用的消息ID
     */
    private Long parentMsgId;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    private Long deleted;

    /**
     * @全体成员标记 0-否 1-是
     */
    private Long atAll;

    /**
     * 可选 自定义属性，供开发者扩展使用
     */
    private String extras;

    /**
     * 应用ID
     */
    private String appId;

    /**
     * 会话类型:1单聊,2群聊,3聊天室
     */
    private Long conversationType;

    /**
     * 接收者ID(单聊必填)
     */
    private Long toUid;

    /**
     * 命令类型
     */
    private Long cmd;

    /**
     * 是否持久化
     */
    private Long persistent;

    /**
     * 消息优先级
     */
    private Long priority;

    /**
     * 是否需要回执
     */
    private Long needReceipt;


}
