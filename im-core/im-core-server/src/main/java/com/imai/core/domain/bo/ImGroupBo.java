package com.imai.core.domain.bo;

import com.imai.core.domain.ImGroup;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;

/**
 * 群组业务对象 im_group
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ImGroup.class, reverseConvertGenerate = false)
public class ImGroupBo extends BaseEntity {

    /**
     * 群组ID
     */
    @NotNull(message = "群组ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 关联的会话ID
     */
    @NotNull(message = "关联的会话ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long fkConversationId;

    /**
     * 群名称
     */
    @NotBlank(message = "群名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 群主ID
     */
    @NotNull(message = "群主ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long ownerId;

    /**
     * 群类型: 1-普通群 2-部门群 3-企业群
     */
    @NotNull(message = "群类型: 1-普通群 2-部门群 3-企业群不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long groupType;

    /**
     * 最大成员数
     */
    @NotNull(message = "最大成员数不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long maxMemberCount;

    /**
     * 加群方式: 0-自由加入 1-需验证 2-禁止加入
     */
    @NotNull(message = "加群方式: 0-自由加入 1-需验证 2-禁止加入不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long joinType;

    /**
     * 群公告
     */
    @NotBlank(message = "群公告不能为空", groups = { AddGroup.class, EditGroup.class })
    private String notice;

    /**
     * 关联组织ID
     */
    @NotNull(message = "关联组织ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long orgId;

    /**
     * 关联部门ID
     */
    @NotNull(message = "关联部门ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long deptId;

    /**
     * 可选 自定义属性，供开发者扩展使用
     */
    @NotBlank(message = "可选 自定义属性，供开发者扩展使用不能为空", groups = { AddGroup.class, EditGroup.class })
    private String extras;


}
