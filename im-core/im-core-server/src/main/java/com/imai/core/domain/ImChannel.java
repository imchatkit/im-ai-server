package com.imai.core.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;

/**
 * 频道对象 im_channel
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("im_channel")
public class ImChannel extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 所属工作空间ID
     */
    private Long fkWorkspaceId;

    /**
     * 关联的会话ID
     */
    private Long fkConversationId;

    /**
     * 频道名称
     */
    private String channelName;

    /**
     * 频道类型:1公开,2私密
     */
    private Long channelType;

    /**
     * 频道主题
     */
    private String topic;

    /**
     * 频道描述
     */
    private String description;

    /**
     * 父频道ID,用于嵌套
     */
    private Long parentId;

    /**
     * 创建者ID
     */
    private Long creatorUserId;

    /**
     * 排序号
     */
    private Long sortOrder;

    /**
     * 是否归档:0否,1是
     */
    private Long archived;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    private Long deleted;

    /**
     * 可选 自定义属性，供开发者扩展使用
     */
    private String extras;


}
