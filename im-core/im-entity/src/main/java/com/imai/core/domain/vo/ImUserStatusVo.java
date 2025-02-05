package com.imai.core.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.imai.core.domain.ImUserStatus;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


/**
 * 用户状态视图对象 im_user_status
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ImUserStatus.class)
public class ImUserStatusVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long userId;

    /**
     * 在线状态:0离线,1在线
     */
    @ExcelProperty(value = "在线状态:0离线,1在线")
    private Long onlineStatus;

    /**
     * 最后活跃时间
     */
    @ExcelProperty(value = "最后活跃时间")
    private Date lastActiveTime;

    /**
     * 设备信息
     */
    @ExcelProperty(value = "设备信息")
    private String deviceInfo;

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
