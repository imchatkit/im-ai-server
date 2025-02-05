package com.imai.core.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.imai.core.domain.ImCallbackRecord;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 消息回调记录视图对象 im_callback_record
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ImCallbackRecord.class)
public class ImCallbackRecordVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 回调类型
     */
    @ExcelProperty(value = "回调类型")
    private Long callbackType;

    /**
     * 回调地址
     */
    @ExcelProperty(value = "回调地址")
    private String callbackUrl;

    /**
     * 请求内容
     */
    @ExcelProperty(value = "请求内容")
    private String requestBody;

    /**
     * 响应内容
     */
    @ExcelProperty(value = "响应内容")
    private String responseBody;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long callbackStatus;

    /**
     * 重试次数
     */
    @ExcelProperty(value = "重试次数")
    private Long retryCount;

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
