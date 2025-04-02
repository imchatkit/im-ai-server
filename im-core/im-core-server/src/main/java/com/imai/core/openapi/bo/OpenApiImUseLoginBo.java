package com.imai.core.openapi.bo;

import com.imai.core.domain.ImUser;
import com.imai.core.domain.bo.ImUserBo;
import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;

import java.io.Serial;
import java.io.Serializable;

/**
 * 注册im_user
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@AutoMappers({
    @AutoMapper(target = ImUser.class, reverseConvertGenerate = false),
    @AutoMapper(target = ImUserBo.class, reverseConvertGenerate = false)
})
public class OpenApiImUseLoginBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 设备类型
     * 参考 {@link org.dromara.common.core.enums.DeviceType}
     * 可选值:
     * - im_app_ios: iOS端
     * - im_app_android: Android端
     * - im_web: Web端
     * - im_pc_win: Windows端
     * - im_pc_mac: Mac端
     */
    @NotBlank(message = "设备类型不能为空", groups = {AddGroup.class, EditGroup.class})
    private String device;

    /**
     * 用户id
     */
    @NotBlank(message = "不能为空", groups = {AddGroup.class, EditGroup.class})
    private String userId;

}
