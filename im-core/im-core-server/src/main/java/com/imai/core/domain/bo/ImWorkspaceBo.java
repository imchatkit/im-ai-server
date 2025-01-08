package com.imai.core.domain.bo;

import com.imai.core.domain.ImWorkspace;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;

/**
 * 工作空间业务对象 im_workspace
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ImWorkspace.class, reverseConvertGenerate = false)
public class ImWorkspaceBo extends BaseEntity {

    /**
     * 工作空间ID
     */
    @NotNull(message = "工作空间ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 工作空间名称
     */
    @NotBlank(message = "工作空间名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String workspaceName;

    /**
     * 创建者ID
     */
    @NotNull(message = "创建者ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long creatorUserId;

    /**
     * 工作空间描述
     */
    @NotBlank(message = "工作空间描述不能为空", groups = { AddGroup.class, EditGroup.class })
    private String description;

    /**
     * 工作空间域名
     */
    @NotBlank(message = "工作空间域名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String domain;

    /**
     * logo地址
     */
    @NotBlank(message = "logo地址不能为空", groups = { AddGroup.class, EditGroup.class })
    private String logoUrl;

    /**
     * 空间状态
     */
    @NotNull(message = "空间状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long workspaceStatus;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    @NotNull(message = "是否删除 0-未删除 1-已删除不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long deleted;

    /**
     * 可选 自定义属性，供开发者扩展使用
     */
    @NotBlank(message = "可选 自定义属性，供开发者扩展使用不能为空", groups = { AddGroup.class, EditGroup.class })
    private String extras;


}
