package com.imai.core.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.imai.core.domain.ImUser;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


/**
 * 用户视图对象 im_user
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ImUser.class)
public class ImUserVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @ExcelProperty(value = "主键id")
    private Long id;

    /**
     * 手机号地区编码+86等等
     */
    @ExcelProperty(value = "手机号地区编码+86等等")
    private Long addressCode;

    /**
     *
     */
    @ExcelProperty(value = "")
    private String phone;

    /**
     * 身份证号码
     */
    @ExcelProperty(value = "身份证号码")
    private String idCardNo;

    /**
     *
     */
    @ExcelProperty(value = "")
    private String email;

    /**
     * 密码
     */
    @ExcelProperty(value = "密码")
    private String password;

    /**
     * 性别 1-男 2-女 3-未知
     */
    @ExcelProperty(value = "性别 1-男 2-女 3-未知")
    private Long sex;

    /**
     * 头像
     */
    @ExcelProperty(value = "头像")
    private String avatar;

    /**
     *
     */
    @ExcelProperty(value = "")
    private String nickname;

    /**
     * 最后离线时间
     */
    @ExcelProperty(value = "最后离线时间")
    private Date lastOfflineTime;

    /**
     * 可选	自定义属性，供开发者扩展使用
     */
    @ExcelProperty(value = "可选	自定义属性，供开发者扩展使用")
    private String attributes;

    /**
     * 所有设备不推送提醒 1提醒
     */
    @ExcelProperty(value = "所有设备不推送提醒 1提醒")
    private Long allValid;

    /**
     * 用户状态
     */
    @ExcelProperty(value = "用户状态")
    private Long userStatus;

    /**
     * 最后登录时间
     */
    @ExcelProperty(value = "最后登录时间")
    private Date lastLoginTime;

    /**
     * 最后登录IP
     */
    @ExcelProperty(value = "最后登录IP")
    private String lastLoginIp;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    @ExcelProperty(value = "是否删除 0-未删除 1-已删除")
    private Long deleted;

    /**
     * 可选 自定义属性，供开发者扩展使用
     */
    @ExcelProperty(value = "可选 自定义属性，供开发者扩展使用")
    private String extras;


}
