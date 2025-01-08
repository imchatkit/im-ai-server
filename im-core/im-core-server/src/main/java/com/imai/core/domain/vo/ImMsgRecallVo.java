package com.imai.core.domain.vo;

import java.util.Date;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.imai.core.domain.ImMsgRecall;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 消息撤回记录视图对象 im_msg_recall
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ImMsgRecall.class)
public class ImMsgRecallVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 撤回记录id
     */
    @ExcelProperty(value = "撤回记录id")
    private Long id;

    /**
     * 消息id
     */
    @ExcelProperty(value = "消息id")
    private Long fkMsgId;

    /**
     * 撤回用户id
     */
    @ExcelProperty(value = "撤回用户id")
    private Long fkUserId;

    /**
     * 撤回时间，精确到毫秒
     */
    @ExcelProperty(value = "撤回时间，精确到毫秒")
    private Date recallTime;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    @ExcelProperty(value = "是否删除 0-未删除 1-已删除")
    private Long deleted;

    /**
     * 可选 自定义属性，供开发者扩展使用
     */
    @ExcelProperty(value = "可选 自定义属性，供开发者扩展使用")
    private String extras;


}
