package com.imai.core.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

import java.io.Serial;

/**
 * 好友申请对象 im_friend_request
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("im_friend_request")
public class ImFriendRequest extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 申请ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 申请人ID
     */
    private Long fromUserId;

    /**
     * 给接收人的备注
     */
    private String fromUserRemarkName;

    /**
     * 接收人ID
     */
    private Long toUserId;

    /**
     * 验证信息
     */
    private String message;

    /**
     * 状态: 0-待处理 1-同意 2-拒绝 3-已过期 4-已取消 5-已删除 6-已忽略
     */
    private Long status;

    /**
     * 处理时间
     */
    private Date handleTime;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    private Long deleted;

    /**
     * 可选 自定义属性，供开发者扩展使用
     */
    private String extras;


}
