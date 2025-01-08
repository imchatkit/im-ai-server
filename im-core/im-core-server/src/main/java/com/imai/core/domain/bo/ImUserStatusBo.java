package com.imai.core.domain.bo;

import com.imai.core.domain.ImUserStatus;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.util.Date;

/**
 * 用户状态业务对象 im_user_status
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ImUserStatus.class, reverseConvertGenerate = false)
public class ImUserStatusBo extends BaseEntity {

    /**
     *
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long userId;

    /**
     * 在线状态:0离线,1在线
     */
    @NotNull(message = "在线状态:0离线,1在线不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long onlineStatus;

    /**
     * 最后活跃时间
     */
    @NotNull(message = "最后活跃时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date lastActiveTime;

    /**
     * 设备信息
     */
    @NotBlank(message = "设备信息不能为空", groups = { AddGroup.class, EditGroup.class })
    private String deviceInfo;

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
