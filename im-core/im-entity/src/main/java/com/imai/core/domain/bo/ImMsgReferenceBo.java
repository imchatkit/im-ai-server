package com.imai.core.domain.bo;

import com.imai.core.domain.ImMsgReference;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;

/**
 * 消息引用关系业务对象 im_msg_reference
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ImMsgReference.class, reverseConvertGenerate = false)
public class ImMsgReferenceBo extends BaseEntity {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 当前消息ID
     */
    @NotNull(message = "当前消息ID不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long fkMsgId;

    /**
     * 被引用的消息ID
     */
    @NotNull(message = "被引用的消息ID不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long fkRefMsgId;

    /**
     * 引用类型:1回复,2转发,3引用
     */
    @NotNull(message = "引用类型:1回复,2转发,3引用不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long refType;

    /**
     * 引用时添加的评论文本
     */
    @NotBlank(message = "引用时添加的评论文本不能为空", groups = {AddGroup.class, EditGroup.class})
    private String refText;

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
