package com.imai.core.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;

/**
 * 群组设置对象 im_group_setting
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("im_group_setting")
public class ImGroupSetting extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 群组ID
     */
    @TableId(value = "fk_group_id")
    private Long fkGroupId;

    /**
     * 全员禁言: 0-否 1-是
     */
    private Long allMute;

    /**
     * 成员邀请开关 0-关闭 1-开启
     */
    private Long memberInvite;

    /**
     * 成员修改群信息开关 0-关闭 1-开启
     */
    private Long memberModify;

    /**
     * 成员列表可见开关 0-关闭 1-开启
     */
    private Long memberVisible;

    /**
     * 禁止群内加好友 0-关闭 1-开启
     */
    private Long forbidAddFriend;

    /**
     * 禁止发红包 0-关闭 1-开启
     */
    private Long forbidSendRedpacket;

    /**
     * 禁止发图片 0-关闭 1-开启
     */
    private Long forbidSendImage;

    /**
     * 禁止发链接 0-关闭 1-开启
     */
    private Long forbidSendLink;

    /**
     * 群组是否已解散 0-否 1-是
     */
    private Long groupDisbanded;

    /**
     * 可选 自定义属性，供开发者扩展使用
     */
    private String extras;


}
