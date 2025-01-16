package com.imai.core.domain.bo;

import com.imai.core.domain.ImGroupSetting;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;

/**
 * 群组设置业务对象 im_group_setting
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ImGroupSetting.class, reverseConvertGenerate = false)
public class ImGroupSettingBo extends BaseEntity {

    /**
     * 群组ID
     */
    @NotNull(message = "群组ID不能为空", groups = {EditGroup.class})
    private Long fkGroupId;

    /**
     * 全员禁言: 0-否 1-是
     */
    @NotNull(message = "全员禁言: 0-否 1-是不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long allMute;

    /**
     * 成员邀请开关 0-关闭 1-开启
     */
    @NotNull(message = "成员邀请开关 0-关闭 1-开启不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long memberInvite;

    /**
     * 成员修改群信息开关 0-关闭 1-开启
     */
    @NotNull(message = "成员修改群信息开关 0-关闭 1-开启不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long memberModify;

    /**
     * 成员列表可见开关 0-关闭 1-开启
     */
    @NotNull(message = "成员列表可见开关 0-关闭 1-开启不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long memberVisible;

    /**
     * 禁止群内加好友 0-关闭 1-开启
     */
    @NotNull(message = "禁止群内加好友 0-关闭 1-开启不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long forbidAddFriend;

    /**
     * 禁止发红包 0-关闭 1-开启
     */
    @NotNull(message = "禁止发红包 0-关闭 1-开启不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long forbidSendRedpacket;

    /**
     * 禁止发图片 0-关闭 1-开启
     */
    @NotNull(message = "禁止发图片 0-关闭 1-开启不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long forbidSendImage;

    /**
     * 禁止发链接 0-关闭 1-开启
     */
    @NotNull(message = "禁止发链接 0-关闭 1-开启不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long forbidSendLink;

    /**
     * 群组是否已解散 0-否 1-是
     */
    @NotNull(message = "群组是否已解散 0-否 1-是不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long groupDisbanded;

    /**
     * 可选 自定义属性，供开发者扩展使用
     */
    @NotBlank(message = "可选 自定义属性，供开发者扩展使用不能为空", groups = {AddGroup.class, EditGroup.class})
    private String extras;


}
