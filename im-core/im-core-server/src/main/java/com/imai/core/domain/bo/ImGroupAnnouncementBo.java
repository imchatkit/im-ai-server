package com.imai.core.domain.bo;

import com.imai.core.domain.ImGroupAnnouncement;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;

/**
 * 群公告业务对象 im_group_announcement
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ImGroupAnnouncement.class, reverseConvertGenerate = false)
public class ImGroupAnnouncementBo extends BaseEntity {

    /**
     * 公告id
     */
    @NotNull(message = "公告id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 群组id
     */
    @NotNull(message = "群组id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long fkGroupId;

    /**
     * 公告内容
     */
    @NotBlank(message = "公告内容不能为空", groups = { AddGroup.class, EditGroup.class })
    private String content;

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
