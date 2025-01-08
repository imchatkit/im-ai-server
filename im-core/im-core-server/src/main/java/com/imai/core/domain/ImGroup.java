package com.imai.core.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;

/**
 * 群组对象 im_group
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("im_group")
public class ImGroup extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 群组ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 关联的会话ID
     */
    private Long fkConversationId;

    /**
     * 群名称
     */
    private String name;

    /**
     * 群主ID
     */
    private Long ownerId;

    /**
     * 群类型: 1-普通群 2-部门群 3-企业群
     */
    private Long groupType;

    /**
     * 最大成员数
     */
    private Long maxMemberCount;

    /**
     * 加群方式: 0-自由加入 1-需验证 2-禁止加入
     */
    private Long joinType;

    /**
     * 群公告
     */
    private String notice;

    /**
     * 关联组织ID
     */
    private Long orgId;

    /**
     * 关联部门ID
     */
    private Long deptId;

    /**
     * 可选 自定义属性，供开发者扩展使用
     */
    private String extras;


}
