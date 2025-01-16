package com.imai.core.domain.bo;

import com.imai.core.domain.ImSync;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;

/**
 * 多端同步业务对象 im_sync
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ImSync.class, reverseConvertGenerate = false)
public class ImSyncBo extends BaseEntity {

    /**
     * 同步id
     */
    @NotNull(message = "同步id不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long fkUserId;

    /**
     * 用户维度单调递增的PTS位点
     */
    @NotNull(message = "用户维度单调递增的PTS位点不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long pts;

    /**
     * 消息id
     */
    @NotNull(message = "消息id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long fkMsgId;

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
