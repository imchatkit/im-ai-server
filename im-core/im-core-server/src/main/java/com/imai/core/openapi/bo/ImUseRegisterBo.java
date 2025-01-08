package com.imai.core.openapi.bo;

import com.imai.core.domain.ImUser;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 *  注册im_user
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@AutoMapper(target = ImUser.class, reverseConvertGenerate = false)
public class ImUseRegisterBo  implements Serializable {

    /**
     * 主键id
     */
    @NotNull(message = "主键id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 昵称
     */
    @NotBlank(message = "不能为空", groups = { AddGroup.class, EditGroup.class })
    private String nickname;

    /**
     * 设备类型 See {@link org.dromara.common.core.enums.DeviceType}
     */
    private String device;

    /**
     * 可选	自定义属性，供开发者扩展使用
     */
    private String attributes;

}
