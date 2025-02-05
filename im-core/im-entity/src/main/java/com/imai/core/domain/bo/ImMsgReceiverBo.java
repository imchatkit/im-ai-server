package com.imai.core.domain.bo;

import com.imai.core.domain.ImMsgReceiver;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;

/**
 * 消息接收业务对象 im_msg_receiver
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ImMsgReceiver.class, reverseConvertGenerate = false)
public class ImMsgReceiverBo extends BaseEntity {

    /**
     *
     */
    @NotNull(message = "不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 消息ID
     */
    @NotNull(message = "消息ID不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long fkMsgId;

    /**
     * 接收者ID
     */
    @NotNull(message = "接收者ID不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long fkReceiverId;

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
