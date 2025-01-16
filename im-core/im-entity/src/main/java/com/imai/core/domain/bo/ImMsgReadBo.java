package com.imai.core.domain.bo;

import com.imai.core.domain.ImMsgRead;
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
 * 消息已读记录业务对象 im_msg_read
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ImMsgRead.class, reverseConvertGenerate = false)
public class ImMsgReadBo extends BaseEntity {

    /**
     * 已读表id
     */
    @NotNull(message = "已读表id不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 消息id
     */
    @NotNull(message = "消息id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long fkMsgId;

    /**
     * 会话id
     */
    @NotNull(message = "会话id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long fkConversationId;

    /**
     * 接收方id
     */
    @NotNull(message = "接收方id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long fkReceiverUserId;

    /**
     * 发送者id
     */
    @NotNull(message = "发送者id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long fkFromUserId;

    /**
     * 读取时间
     */
    @NotNull(message = "读取时间不能为空", groups = {AddGroup.class, EditGroup.class})
    private Date readTime;

    /**
     * 接收时间
     */
    @NotNull(message = "接收时间不能为空", groups = {AddGroup.class, EditGroup.class})
    private Date receiverTime;

    /**
     * 0未读; 1已读
     */
    @NotNull(message = "0未读; 1已读不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long readMsgStatus;

    /**
     * 0未接收; 1已接收
     */
    @NotNull(message = "0未接收; 1已接收不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long receiverMsgStatus;

    /**
     * 可选 自定义属性，供开发者扩展使用
     */
    @NotBlank(message = "可选 自定义属性，供开发者扩展使用不能为空", groups = {AddGroup.class, EditGroup.class})
    private String extras;


}
