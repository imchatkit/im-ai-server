package com.imai.core.domain.bo;

import com.imai.core.domain.ImMsgRecall;
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
 * 消息撤回记录业务对象 im_msg_recall
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ImMsgRecall.class, reverseConvertGenerate = false)
public class ImMsgRecallBo extends BaseEntity {

    /**
     * 撤回记录id
     */
    @NotNull(message = "撤回记录id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 消息id
     */
    @NotNull(message = "消息id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long fkMsgId;

    /**
     * 撤回用户id
     */
    @NotNull(message = "撤回用户id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long fkUserId;

    /**
     * 撤回时间，精确到毫秒
     */
    @NotNull(message = "撤回时间，精确到毫秒不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date recallTime;

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
