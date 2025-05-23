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
public class OpenApiImUseRegisterBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 昵称
     */
    @NotBlank(message = "不能为空", groups = {AddGroup.class, EditGroup.class})
    private String nickname;


    /**
     * 可选	自定义属性，供开发者扩展使用
     */
    private String attributes;

}
