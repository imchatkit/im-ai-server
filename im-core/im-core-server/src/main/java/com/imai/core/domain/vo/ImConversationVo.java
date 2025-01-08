package com.imai.core.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.imai.core.domain.ImConversation;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 聊天会话基础视图对象 im_conversation
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ImConversation.class)
public class ImConversationVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 头像
     */
    @ExcelProperty(value = "头像")
    private String avatar;

    /**
     * 会话类型: 1-单聊 2-群聊 3-系统通知 4-机器人 5频道
     */
    @ExcelProperty(value = "会话类型: 1-单聊 2-群聊 3-系统通知 4-机器人 5频道")
    private Long conversationType;

    /**
     * 会话状态: 1-正常 2-禁用 3-删除 4-归档
     */
    @ExcelProperty(value = "会话状态: 1-正常 2-禁用 3-删除 4-归档")
    private Long conversationStatus;

    /**
     * 是否删除: 0-否 1-是
     */
    @ExcelProperty(value = "是否删除: 0-否 1-是")
    private Long deleted;

    /**
     * 可选 自定义属性，供开发者扩展使用
     */
    @ExcelProperty(value = "可选 自定义属性，供开发者扩展使用")
    private String extras;


}
