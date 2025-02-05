package com.imai.core.domain.bo;

import com.imai.core.domain.ImSensitiveWords;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;

/**
 * 敏感词过滤业务对象 im_sensitive_words
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ImSensitiveWords.class, reverseConvertGenerate = false)
public class ImSensitiveWordsBo extends BaseEntity {

    /**
     * 敏感词id
     */
    @NotNull(message = "敏感词id不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 敏感词
     */
    @NotBlank(message = "敏感词不能为空", groups = {AddGroup.class, EditGroup.class})
    private String word;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    @NotNull(message = "是否删除 0-未删除 1-已删除不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long deleted;

    /**
     * 可选 自定义属性，供开发者扩展使用
     */
    @NotBlank(message = "可选 自定义属性，供开发者扩展使用不能为空", groups = {AddGroup.class, EditGroup.class})
    private String extras;


}
