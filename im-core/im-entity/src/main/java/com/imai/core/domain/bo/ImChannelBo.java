package com.imai.core.domain.bo;

import com.imai.core.domain.ImChannel;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;

/**
 * 频道业务对象 im_channel
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ImChannel.class, reverseConvertGenerate = false)
public class ImChannelBo extends BaseEntity {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 所属工作空间ID
     */
    @NotNull(message = "所属工作空间ID不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long fkWorkspaceId;

    /**
     * 关联的会话ID
     */
    @NotNull(message = "关联的会话ID不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long fkConversationId;

    /**
     * 频道名称
     */
    @NotBlank(message = "频道名称不能为空", groups = {AddGroup.class, EditGroup.class})
    private String channelName;

    /**
     * 频道类型:1公开,2私密
     */
    @NotNull(message = "频道类型:1公开,2私密不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long channelType;

    /**
     * 频道主题
     */
    @NotBlank(message = "频道主题不能为空", groups = {AddGroup.class, EditGroup.class})
    private String topic;

    /**
     * 频道描述
     */
    @NotBlank(message = "频道描述不能为空", groups = {AddGroup.class, EditGroup.class})
    private String description;

    /**
     * 父频道ID,用于嵌套
     */
    @NotNull(message = "父频道ID,用于嵌套不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long parentId;

    /**
     * 创建者ID
     */
    @NotNull(message = "创建者ID不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long creatorUserId;

    /**
     * 排序号
     */
    @NotNull(message = "排序号不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long sortOrder;

    /**
     * 是否归档:0否,1是
     */
    @NotNull(message = "是否归档:0否,1是不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long archived;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    @NotNull(message = "是否删除 0-未删除 1-已删除不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long deleted;

    /**
     * 可选 自定义属性，供开发者扩展使用
     */
    @NotBlank(message = "可选 自定义属性，供开发者扩展使用不能为空", groups = {AddGroup.class, EditGroup.class})
    private String extras;


}
