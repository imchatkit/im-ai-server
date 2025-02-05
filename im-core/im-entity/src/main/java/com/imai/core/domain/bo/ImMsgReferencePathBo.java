package com.imai.core.domain.bo;

import com.imai.core.domain.ImMsgReferencePath;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;

/**
 * 消息引用路径业务对象 im_msg_reference_path
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ImMsgReferencePath.class, reverseConvertGenerate = false)
public class ImMsgReferencePathBo extends BaseEntity {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 消息ID
     */
    @NotNull(message = "消息ID不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long fkMsgId;

    /**
     * 祖先消息ID
     */
    @NotNull(message = "祖先消息ID不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long ancestorMsgId;

    /**
     * 引用深度(层级距离)
     */
    @NotNull(message = "引用深度(层级距离)不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long distance;

    /**
     * 引用路径(格式:id1->id2->id3)
     */
    @NotBlank(message = "引用路径(格式:id1->id2->id3)不能为空", groups = {AddGroup.class, EditGroup.class})
    private String path;

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
