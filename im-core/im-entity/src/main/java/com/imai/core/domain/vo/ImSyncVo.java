package com.imai.core.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.imai.core.domain.ImSync;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 多端同步视图对象 im_sync
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ImSync.class)
public class ImSyncVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 同步id
     */
    @ExcelProperty(value = "同步id")
    private Long id;

    /**
     * 用户id
     */
    @ExcelProperty(value = "用户id")
    private Long fkUserId;

    /**
     * 用户维度单调递增的PTS位点
     */
    @ExcelProperty(value = "用户维度单调递增的PTS位点")
    private Long pts;

    /**
     * 消息id
     */
    @ExcelProperty(value = "消息id")
    private Long fkMsgId;

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
