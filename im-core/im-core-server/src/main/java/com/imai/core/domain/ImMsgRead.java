package com.imai.core.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;
import java.util.Date;

/**
 * 消息已读记录对象 im_msg_read
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("im_msg_read")
public class ImMsgRead extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 已读表id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 消息id
     */
    private Long fkMsgId;

    /**
     * 会话id
     */
    private Long fkConversationId;

    /**
     * 接收方id
     */
    private Long fkReceiverUserId;

    /**
     * 发送者id
     */
    private Long fkFromUserId;

    /**
     * 读取时间
     */
    private Date readTime;

    /**
     * 接收时间
     */
    private Date receiverTime;

    /**
     * 0未读; 1已读
     */
    private Long readMsgStatus;

    /**
     * 0未接收; 1已接收
     */
    private Long receiverMsgStatus;

    /**
     * 可选 自定义属性，供开发者扩展使用
     */
    private String extras;


}
