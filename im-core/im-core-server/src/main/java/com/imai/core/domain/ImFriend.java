package com.imai.core.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;

/**
 * 好友关系对象 im_friend
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("im_friend")
public class ImFriend extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 关系ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 用户ID
     */
    private Long fkUserId;

    /**
     * 好友ID
     */
    private Long fkFriendId;

    /**
     * 单聊会话ID
     */
    private Long conversationId;

    /**
     * 备注名
     */
    private String remark;

    /**
     * 来源: 1-搜索 2-群聊 3-名片
     */
    private Long source;

    /**
     * 状态: 1-正常 2-删除 3-拉黑
     */
    private Long status;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    private Long deleted;

    /**
     * 可选 自定义属性，供开发者扩展使用
     */
    private String extras;


}
