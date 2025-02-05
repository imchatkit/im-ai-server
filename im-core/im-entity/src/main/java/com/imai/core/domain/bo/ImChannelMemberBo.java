package com.imai.core.domain.bo;

import com.imai.core.domain.ImChannelMember;
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
 * 频道成员业务对象 im_channel_member
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ImChannelMember.class, reverseConvertGenerate = false)
public class ImChannelMemberBo extends BaseEntity {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 频道ID
     */
    @NotNull(message = "频道ID不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long fkChannelId;

    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long fkUserId;

    /**
     * 用户权限
     */
    @NotNull(message = "用户权限不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long memberRole;

    /**
     * 加入时间
     */
    @NotNull(message = "加入时间不能为空", groups = {AddGroup.class, EditGroup.class})
    private Date joinTime;

    /**
     * 通知级别:0关闭,1提及时,2所有消息
     */
    @NotNull(message = "通知级别:0关闭,1提及时,2所有消息不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long notificationLevel;

    /**
     * 是否星标:0否,1是
     */
    @NotNull(message = "是否星标:0否,1是不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long starred;

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
