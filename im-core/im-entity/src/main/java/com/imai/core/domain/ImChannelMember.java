package com.imai.core.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;
import java.util.Date;

/**
 * 频道成员对象 im_channel_member
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("im_channel_member")
public class ImChannelMember extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 频道ID
     */
    private Long fkChannelId;

    /**
     * 用户ID
     */
    private Long fkUserId;

    /**
     * 用户权限
     */
    private Long memberRole;

    /**
     * 加入时间
     */
    private Date joinTime;

    /**
     * 通知级别:0关闭,1提及时,2所有消息
     */
    private Long notificationLevel;

    /**
     * 是否星标:0否,1是
     */
    private Long starred;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    private Long deleted;

    /**
     * 可选 自定义属性，供开发者扩展使用
     */
    private String extras;


}
