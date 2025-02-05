package com.imai.core.domain.bo;

import com.imai.core.domain.ImSysConversationInit;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;

/**
 * 系统会话初始化业务对象 im_sys_conversation_init
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ImSysConversationInit.class, reverseConvertGenerate = false)
public class ImSysConversationInitBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 会话类型
     */
    @NotNull(message = "会话类型不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long conversationType;

    /**
     * 会话名称
     */
    @NotBlank(message = "会话名称不能为空", groups = {AddGroup.class, EditGroup.class})
    private String conversationName;

    /**
     * 可选	自定义属性，供开发者扩展使用。
     */
    @NotBlank(message = "可选	自定义属性，供开发者扩展使用。不能为空", groups = {AddGroup.class, EditGroup.class})
    private String extras;

    /**
     * 头像
     */
    @NotBlank(message = "头像不能为空", groups = {AddGroup.class, EditGroup.class})
    private String avatar;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    @NotNull(message = "是否删除 0-未删除 1-已删除不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long deleted;


}
