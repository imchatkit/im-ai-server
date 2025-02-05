package com.imai.core.domain.bo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 批量添加会话成员业务对象
 *
 * @author wei
 * @date 2024-01-15
 */
@Data
@NoArgsConstructor
public class ImConversationMemberBatchAddBo {

    /**
     * 会话ID
     */
    @NotNull(message = "会话ID不能为空")
    private Long conversationId;

    /**
     * 用户ID列表
     */
    @NotEmpty(message = "用户列表不能为空")
    private List<Long> userIds;

    /**
     * 用户角色（可选）
     */
    private Long role;

    /**
     * 是否免打扰（可选）
     */
    private Long disturbFlag;

    /**
     * 是否置顶（可选）
     */
    private Long topFlag;

    /**
     * 是否禁言@所有人（可选）
     */
    private Long muteAtAll;

    /**
     * 是否被禁言（可选）
     */
    private Long muted;
} 