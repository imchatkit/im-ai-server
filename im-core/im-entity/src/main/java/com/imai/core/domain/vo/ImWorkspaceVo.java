package com.imai.core.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.imai.core.domain.ImWorkspace;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 工作空间视图对象 im_workspace
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ImWorkspace.class)
public class ImWorkspaceVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 工作空间ID
     */
    @ExcelProperty(value = "工作空间ID")
    private Long id;

    /**
     * 工作空间名称
     */
    @ExcelProperty(value = "工作空间名称")
    private String workspaceName;

    /**
     * 创建者ID
     */
    @ExcelProperty(value = "创建者ID")
    private Long creatorUserId;

    /**
     * 工作空间描述
     */
    @ExcelProperty(value = "工作空间描述")
    private String description;

    /**
     * 工作空间域名
     */
    @ExcelProperty(value = "工作空间域名")
    private String domain;

    /**
     * logo地址
     */
    @ExcelProperty(value = "logo地址")
    private String logoUrl;

    /**
     * 空间状态
     */
    @ExcelProperty(value = "空间状态")
    private Long workspaceStatus;

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
