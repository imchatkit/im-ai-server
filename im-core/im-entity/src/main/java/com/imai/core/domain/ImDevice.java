package com.imai.core.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;

/**
 * 客户端设备对象 im_device
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("im_device")
public class ImDevice extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 用户id
     */
    private Long fkUserId;

    /**
     * 设备不想收到推送提醒
     */
    private Long valid;

    /**
     * 通知推送token
     */
    private String pushToken;

    /**
     * 设备唯一编码(由设备端生成)
     */
    private String uniqueDeviceCode;

    /**
     * 推送通道 1极光 2友盟
     */
    private String pushChannel;

    /**
     * 客户端平台: 1web, 2Android, 3 ios, 4windows, 5mac
     */
    private Long platform;

    /**
     * 设备状态 0退出登录 1正常
     */
    private Long deviceStatus;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    private Long deleted;

    /**
     * 可选 自定义属性，供开发者扩展使用
     */
    private String extras;


}
