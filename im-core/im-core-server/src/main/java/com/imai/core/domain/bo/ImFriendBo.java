package com.imai.core.domain.bo;

import com.imai.core.domain.ImFriend;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;

/**
 * 好友关系业务对象 im_friend
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ImFriend.class, reverseConvertGenerate = false)
public class ImFriendBo extends BaseEntity {

    /**
     * 关系ID
     */
    @NotNull(message = "关系ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long fkUserId;

    /**
     * 好友ID
     */
    @NotNull(message = "好友ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long fkFriendId;

    /**
     * 单聊会话ID
     */
    @NotNull(message = "单聊会话ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long conversationId;

    /**
     * 备注名
     */
    @NotBlank(message = "备注名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String remark;

    /**
     * 来源: 1-搜索 2-群聊 3-名片
     */
    @NotNull(message = "来源: 1-搜索 2-群聊 3-名片不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long source;

    /**
     * 状态: 1-正常 2-删除 3-拉黑
     */
    @NotNull(message = "状态: 1-正常 2-删除 3-拉黑不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long status;

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
