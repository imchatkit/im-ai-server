package com.imai.core.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.imai.core.domain.ImMsgRead;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


/**
 * 消息已读记录视图对象 im_msg_read
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ImMsgRead.class)
public class ImMsgReadVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 已读表id
     */
    @ExcelProperty(value = "已读表id")
    private Long id;

    /**
     * 消息id
     */
    @ExcelProperty(value = "消息id")
    private Long fkMsgId;

    /**
     * 会话id
     */
    @ExcelProperty(value = "会话id")
    private Long fkConversationId;

    /**
     * 接收方id
     */
    @ExcelProperty(value = "接收方id")
    private Long fkReceiverUserId;

    /**
     * 发送者id
     */
    @ExcelProperty(value = "发送者id")
    private Long fkFromUserId;

    /**
     * 读取时间
     */
    @ExcelProperty(value = "读取时间")
    private Date readTime;

    /**
     * 接收时间
     */
    @ExcelProperty(value = "接收时间")
    private Date receiverTime;

    /**
     * 0未读; 1已读
     */
    @ExcelProperty(value = "0未读; 1已读")
    private Long readMsgStatus;

    /**
     * 0未接收; 1已接收
     */
    @ExcelProperty(value = "0未接收; 1已接收")
    private Long receiverMsgStatus;

    /**
     * 可选 自定义属性，供开发者扩展使用
     */
    @ExcelProperty(value = "可选 自定义属性，供开发者扩展使用")
    private String extras;


}
