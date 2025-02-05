package com.imai.core.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;
import java.util.Date;

/**
 * 用户对象 im_user
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("im_user")
public class ImUser extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 手机号地区编码+86等等
     */
    private Long addressCode;

    /**
     *
     */
    private String phone;

    /**
     * 身份证号码
     */
    private String idCardNo;

    /**
     *
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别 1-男 2-女 3-未知
     */
    private Long sex;

    /**
     * 头像
     */
    private String avatar;

    /**
     *
     */
    private String nickname;

    /**
     * 最后离线时间
     */
    private Date lastOfflineTime;

    /**
     * 可选	自定义属性，供开发者扩展使用
     */
    private String attributes;

    /**
     * 所有设备不推送提醒 1提醒
     */
    private Long allValid;

    /**
     * 用户状态
     */
    private Long userStatus;

    /**
     * 最后登录时间
     */
    private Date lastLoginTime;

    /**
     * 最后登录IP
     */
    private String lastLoginIp;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    private Long deleted;

    /**
     * 可选 自定义属性，供开发者扩展使用
     */
    private String extras;


}
