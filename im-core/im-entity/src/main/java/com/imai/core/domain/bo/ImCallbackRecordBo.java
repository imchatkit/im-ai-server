package com.imai.core.domain.bo;

import com.imai.core.domain.ImCallbackRecord;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;

/**
 * 消息回调记录业务对象 im_callback_record
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ImCallbackRecord.class, reverseConvertGenerate = false)
public class ImCallbackRecordBo extends BaseEntity {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 回调类型
     */
    @NotNull(message = "回调类型不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long callbackType;

    /**
     * 回调地址
     */
    @NotBlank(message = "回调地址不能为空", groups = {AddGroup.class, EditGroup.class})
    private String callbackUrl;

    /**
     * 请求内容
     */
    @NotBlank(message = "请求内容不能为空", groups = {AddGroup.class, EditGroup.class})
    private String requestBody;

    /**
     * 响应内容
     */
    @NotBlank(message = "响应内容不能为空", groups = {AddGroup.class, EditGroup.class})
    private String responseBody;

    /**
     *
     */
    @NotNull(message = "不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long callbackStatus;

    /**
     * 重试次数
     */
    @NotNull(message = "重试次数不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long retryCount;

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
