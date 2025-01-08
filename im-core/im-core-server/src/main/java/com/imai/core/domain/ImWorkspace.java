package com.imai.core.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;

/**
 * 工作空间对象 im_workspace
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("im_workspace")
public class ImWorkspace extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 工作空间ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 工作空间名称
     */
    private String workspaceName;

    /**
     * 创建者ID
     */
    private Long creatorUserId;

    /**
     * 工作空间描述
     */
    private String description;

    /**
     * 工作空间域名
     */
    private String domain;

    /**
     * logo地址
     */
    private String logoUrl;

    /**
     * 空间状态
     */
    private Long workspaceStatus;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    private Long deleted;

    /**
     * 可选 自定义属性，供开发者扩展使用
     */
    private String extras;


}
