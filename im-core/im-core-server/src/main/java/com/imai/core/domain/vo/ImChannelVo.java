package com.imai.core.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.imai.core.domain.ImChannel;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 频道视图对象 im_channel
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ImChannel.class)
public class ImChannelVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 所属工作空间ID
     */
    @ExcelProperty(value = "所属工作空间ID")
    private Long fkWorkspaceId;

    /**
     * 关联的会话ID
     */
    @ExcelProperty(value = "关联的会话ID")
    private Long fkConversationId;

    /**
     * 频道名称
     */
    @ExcelProperty(value = "频道名称")
    private String channelName;

    /**
     * 频道类型:1公开,2私密
     */
    @ExcelProperty(value = "频道类型:1公开,2私密")
    private Long channelType;

    /**
     * 频道主题
     */
    @ExcelProperty(value = "频道主题")
    private String topic;

    /**
     * 频道描述
     */
    @ExcelProperty(value = "频道描述")
    private String description;

    /**
     * 父频道ID,用于嵌套
     */
    @ExcelProperty(value = "父频道ID,用于嵌套")
    private Long parentId;

    /**
     * 创建者ID
     */
    @ExcelProperty(value = "创建者ID")
    private Long creatorUserId;

    /**
     * 排序号
     */
    @ExcelProperty(value = "排序号")
    private Long sortOrder;

    /**
     * 是否归档:0否,1是
     */
    @ExcelProperty(value = "是否归档:0否,1是")
    private Long archived;

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
