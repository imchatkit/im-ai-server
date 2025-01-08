package com.imai.core.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.imai.core.domain.ImSensitiveWords;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 敏感词过滤视图对象 im_sensitive_words
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ImSensitiveWords.class)
public class ImSensitiveWordsVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 敏感词id
     */
    @ExcelProperty(value = "敏感词id")
    private Long id;

    /**
     * 敏感词
     */
    @ExcelProperty(value = "敏感词")
    private String word;

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
