package com.imai.core.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.imai.core.domain.ImGroupSetting;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.common.translation.constant.TransConstant;

import java.io.Serial;
import java.io.Serializable;


/**
 * 群组设置视图对象 im_group_setting
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ImGroupSetting.class)
public class ImGroupSettingVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 群组ID
     */
    @ExcelProperty(value = "群组ID")
    private Long fkGroupId;

    /**
     * 全员禁言: 0-否 1-是
     */
    @ExcelProperty(value = "全员禁言: 0-否 1-是")
    private Long allMute;

    /**
     * 成员邀请开关 0-关闭 1-开启
     */
    @ExcelProperty(value = "成员邀请开关 0-关闭 1-开启")
    private Long memberInvite;

    /**
     * 成员修改群信息开关 0-关闭 1-开启
     */
    @ExcelProperty(value = "成员修改群信息开关 0-关闭 1-开启")
    private Long memberModify;

    /**
     * 成员列表可见开关 0-关闭 1-开启
     */
    @ExcelProperty(value = "成员列表可见开关 0-关闭 1-开启")
    private Long memberVisible;

    /**
     * 禁止群内加好友 0-关闭 1-开启
     */
    @ExcelProperty(value = "禁止群内加好友 0-关闭 1-开启")
    private Long forbidAddFriend;

    /**
     * 禁止发红包 0-关闭 1-开启
     */
    @ExcelProperty(value = "禁止发红包 0-关闭 1-开启")
    private Long forbidSendRedpacket;

    /**
     * 禁止发图片 0-关闭 1-开启
     */
    @ExcelProperty(value = "禁止发图片 0-关闭 1-开启")
    private Long forbidSendImage;

    /**
     * 禁止发图片 0-关闭 1-开启Url
     */
    @Translation(type = TransConstant.OSS_ID_TO_URL, mapper = "forbidSendImage")
    private String forbidSendImageUrl;
    /**
     * 禁止发链接 0-关闭 1-开启
     */
    @ExcelProperty(value = "禁止发链接 0-关闭 1-开启")
    private Long forbidSendLink;

    /**
     * 群组是否已解散 0-否 1-是
     */
    @ExcelProperty(value = "群组是否已解散 0-否 1-是")
    private Long groupDisbanded;

    /**
     * 可选 自定义属性，供开发者扩展使用
     */
    @ExcelProperty(value = "可选 自定义属性，供开发者扩展使用")
    private String extras;


}
