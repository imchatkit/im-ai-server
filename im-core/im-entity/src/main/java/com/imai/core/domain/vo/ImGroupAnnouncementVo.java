package com.imai.core.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.imai.core.domain.ImGroupAnnouncement;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 群公告视图对象 im_group_announcement
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ImGroupAnnouncement.class)
public class ImGroupAnnouncementVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 公告id
     */
    @ExcelProperty(value = "公告id")
    private Long id;

    /**
     * 群组id
     */
    @ExcelProperty(value = "群组id")
    private Long fkGroupId;

    /**
     * 公告内容
     */
    @ExcelProperty(value = "公告内容")
    private String content;

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
