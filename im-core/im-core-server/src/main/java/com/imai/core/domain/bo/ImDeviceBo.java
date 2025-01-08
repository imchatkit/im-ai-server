package com.imai.core.domain.bo;

import com.imai.core.domain.ImDevice;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;

/**
 * 客户端设备业务对象 im_device
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ImDevice.class, reverseConvertGenerate = false)
public class ImDeviceBo extends BaseEntity {

    /**
     * 主键id
     */
    @NotNull(message = "主键id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long fkUserId;

    /**
     * 设备不想收到推送提醒
     */
    @NotNull(message = "设备不想收到推送提醒不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long valid;

    /**
     * 通知推送token
     */
    @NotBlank(message = "通知推送token不能为空", groups = { AddGroup.class, EditGroup.class })
    private String pushToken;

    /**
     * 设备唯一编码(由设备端生成)
     */
    @NotBlank(message = "设备唯一编码(由设备端生成)不能为空", groups = { AddGroup.class, EditGroup.class })
    private String uniqueDeviceCode;

    /**
     * 推送通道 1极光 2友盟
     */
    @NotBlank(message = "推送通道 1极光 2友盟不能为空", groups = { AddGroup.class, EditGroup.class })
    private String pushChannel;

    /**
     * 客户端平台: 1web, 2Android, 3 ios, 4windows, 5mac
     */
    @NotNull(message = "客户端平台: 1web, 2Android, 3 ios, 4windows, 5mac不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long platform;

    /**
     * 设备状态 0退出登录 1正常
     */
    @NotNull(message = "设备状态 0退出登录 1正常不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long deviceStatus;

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
