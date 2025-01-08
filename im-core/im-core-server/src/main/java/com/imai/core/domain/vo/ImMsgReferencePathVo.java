package com.imai.core.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.imai.core.domain.ImMsgReferencePath;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 消息引用路径视图对象 im_msg_reference_path
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ImMsgReferencePath.class)
public class ImMsgReferencePathVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 消息ID
     */
    @ExcelProperty(value = "消息ID")
    private Long fkMsgId;

    /**
     * 祖先消息ID
     */
    @ExcelProperty(value = "祖先消息ID")
    private Long ancestorMsgId;

    /**
     * 引用深度(层级距离)
     */
    @ExcelProperty(value = "引用深度(层级距离)")
    private Long distance;

    /**
     * 引用路径(格式:id1->id2->id3)
     */
    @ExcelProperty(value = "引用路径(格式:id1->id2->id3)")
    private String path;

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
