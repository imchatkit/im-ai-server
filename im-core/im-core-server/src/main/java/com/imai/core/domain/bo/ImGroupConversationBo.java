package com.imai.core.domain.bo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 创建群组会话请求参数
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
public class ImGroupConversationBo {

    // /**
    //  * 群组名称
    //  */
    // @NotNull(message = "群组名称不能为空")
    // private String name;

    /**
     * 群组成员ID列表
     */
    @NotEmpty(message = "群组成员不能为空")
    private List<Long> memberIds;

    // /**
    //  * 群组公告
    //  */
    // private String notice;

    // /**
    //  * 群组类型
    //  */
    // private Integer groupType;

    // /**
    //  * 群组最大成员数
    //  */
    // private Integer maxMemberCount;

    /**
     * 加入方式 加群方式: 0-自由加入 1-需验证 2-禁止加入
     */
    private Integer joinType;

    /**
     * 扩展信息
     */
    private String extras;
}