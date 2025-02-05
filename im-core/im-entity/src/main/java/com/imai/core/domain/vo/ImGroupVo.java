package com.imai.core.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.imai.core.domain.ImGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 群组视图对象 im_group
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ImGroup.class)
public class ImGroupVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 群组ID
     */
    @ExcelProperty(value = "群组ID")
    private Long id;

    /**
     * 关联的会话ID
     */
    @ExcelProperty(value = "关联的会话ID")
    private Long fkConversationId;

    /**
     * 群名称
     */
    @ExcelProperty(value = "群名称")
    private String name;

    /**
     * 群主ID
     */
    @ExcelProperty(value = "群主ID")
    private Long ownerId;

    /**
     * 群类型: 1-普通群 2-部门群 3-企业群
     */
    @ExcelProperty(value = "群类型: 1-普通群 2-部门群 3-企业群")
    private Long groupType;

    /**
     * 最大成员数
     */
    @ExcelProperty(value = "最大成员数")
    private Long maxMemberCount;

    /**
     * 加群方式: 0-自由加入 1-需验证 2-禁止加入
     */
    @ExcelProperty(value = "加群方式: 0-自由加入 1-需验证 2-禁止加入")
    private Long joinType;

    /**
     * 群公告
     */
    @ExcelProperty(value = "群公告")
    private String notice;

    /**
     * 关联组织ID
     */
    @ExcelProperty(value = "关联组织ID")
    private Long orgId;

    /**
     * 关联部门ID
     */
    @ExcelProperty(value = "关联部门ID")
    private Long deptId;

    /**
     * 可选 自定义属性，供开发者扩展使用
     */
    @ExcelProperty(value = "可选 自定义属性，供开发者扩展使用")
    private String extras;


}
