package com.imai.core.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.imai.core.domain.ImMsgReference;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 消息引用关系视图对象 im_msg_reference
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ImMsgReference.class)
public class ImMsgReferenceVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 当前消息ID
     */
    @ExcelProperty(value = "当前消息ID")
    private Long fkMsgId;

    /**
     * 被引用的消息ID
     */
    @ExcelProperty(value = "被引用的消息ID")
    private Long fkRefMsgId;

    /**
     * 引用类型:1回复,2转发,3引用
     */
    @ExcelProperty(value = "引用类型:1回复,2转发,3引用")
    private Long refType;

    /**
     * 引用时添加的评论文本
     */
    @ExcelProperty(value = "引用时添加的评论文本")
    private String refText;

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
