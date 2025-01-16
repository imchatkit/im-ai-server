package com.imai.core.domain.bo;

import com.imai.core.domain.ImUser;
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
 * 用户业务对象 im_user
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ImUser.class, reverseConvertGenerate = false)
public class ImUserBo extends BaseEntity {

    /**
     * 主键id
     */
    @NotNull(message = "主键id不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 手机号地区编码+86等等
     */
    @NotNull(message = "手机号地区编码+86等等不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long addressCode;

    /**
     *
     */
    @NotBlank(message = "不能为空", groups = {AddGroup.class, EditGroup.class})
    private String phone;

    /**
     * 身份证号码
     */
    @NotBlank(message = "身份证号码不能为空", groups = {AddGroup.class, EditGroup.class})
    private String idCardNo;

    /**
     *
     */
    @NotBlank(message = "不能为空", groups = {AddGroup.class, EditGroup.class})
    private String email;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空", groups = {AddGroup.class, EditGroup.class})
    private String password;

    /**
     * 性别 1-男 2-女 3-未知
     */
    @NotNull(message = "性别 1-男 2-女 3-未知不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long sex;

    /**
     * 头像
     */
    @NotBlank(message = "头像不能为空", groups = {AddGroup.class, EditGroup.class})
    private String avatar;

    /**
     *
     */
    @NotBlank(message = "不能为空", groups = {AddGroup.class, EditGroup.class})
    private String nickname;

    /**
     * 最后离线时间
     */
    @NotNull(message = "最后离线时间不能为空", groups = {AddGroup.class, EditGroup.class})
    private Date lastOfflineTime;

    /**
     * 可选	自定义属性，供开发者扩展使用
     */
    @NotBlank(message = "可选	自定义属性，供开发者扩展使用不能为空", groups = {AddGroup.class, EditGroup.class})
    private String attributes;

    /**
     * 所有设备不推送提醒 1提醒
     */
    @NotNull(message = "所有设备不推送提醒 1提醒不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long allValid;

    /**
     * 用户状态
     */
    @NotNull(message = "用户状态不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long userStatus;

    /**
     * 最后登录时间
     */
    @NotNull(message = "最后登录时间不能为空", groups = {AddGroup.class, EditGroup.class})
    private Date lastLoginTime;

    /**
     * 最后登录IP
     */
    @NotBlank(message = "最后登录IP不能为空", groups = {AddGroup.class, EditGroup.class})
    private String lastLoginIp;

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
