package com.imai.core.domain.bo;

import com.imai.core.domain.ImConversation;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;

/**
 * 聊天会话基础业务对象 im_conversation
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ImConversation.class, reverseConvertGenerate = false)
public class ImConversationBo extends BaseEntity {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 头像
     */
    @NotBlank(message = "头像不能为空", groups = { AddGroup.class, EditGroup.class })
    private String avatar;

    /**
     * 会话类型: 1-单聊 2-群聊 3-系统通知 4-机器人 5频道
     */
    @NotNull(message = "会话类型: 1-单聊 2-群聊 3-系统通知 4-机器人 5频道不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long conversationType;

    /**
     * 会话状态: 1-正常 2-禁用 3-删除 4-归档
     */
    @NotNull(message = "会话状态: 1-正常 2-禁用 3-删除 4-归档不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long conversationStatus;

    /**
     * 是否删除: 0-否 1-是
     */
    @NotNull(message = "是否删除: 0-否 1-是不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long deleted;

    /**
     * 可选 自定义属性，供开发者扩展使用
     */
    @NotBlank(message = "可选 自定义属性，供开发者扩展使用不能为空", groups = { AddGroup.class, EditGroup.class })
    private String extras;


}
