package com.imai.core.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;
import java.util.Date;

/**
 * 用户状态对象 im_user_status
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("im_user_status")
public class ImUserStatus extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(value = "user_id")
    private Long userId;

    /**
     * 在线状态:0离线,1在线
     */
    private Long onlineStatus;

    /**
     * 最后活跃时间
     */
    private Date lastActiveTime;

    /**
     * 设备信息
     */
    private String deviceInfo;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    private Long deleted;

    /**
     * 可选 自定义属性，供开发者扩展使用
     */
    private String extras;


}
