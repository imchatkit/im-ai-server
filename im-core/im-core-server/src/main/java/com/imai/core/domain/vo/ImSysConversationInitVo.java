package com.imai.core.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.imai.core.domain.ImSysConversationInit;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 系统会话初始化视图对象 im_sys_conversation_init
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ImSysConversationInit.class)
public class ImSysConversationInitVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long id;

    /**
     * 会话类型
     */
    @ExcelProperty(value = "会话类型")
    private Long conversationType;

    /**
     * 会话名称
     */
    @ExcelProperty(value = "会话名称")
    private String conversationName;

    /**
     * 可选	自定义属性，供开发者扩展使用。
     */
    @ExcelProperty(value = "可选	自定义属性，供开发者扩展使用。")
    private String extras;

    /**
     * 头像
     */
    @ExcelProperty(value = "头像")
    private String avatar;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    @ExcelProperty(value = "是否删除 0-未删除 1-已删除")
    private Long deleted;


}
