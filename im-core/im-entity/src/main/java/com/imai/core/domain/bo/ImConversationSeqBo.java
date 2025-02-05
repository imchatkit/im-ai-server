package com.imai.core.domain.bo;

import com.imai.core.domain.ImConversationSeq;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;

/**
 * 会话序列号业务对象 im_conversation_seq
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ImConversationSeq.class, reverseConvertGenerate = false)
public class ImConversationSeqBo extends BaseEntity {

    /**
     * 主键id
     */
    @NotNull(message = "主键id不能为空", groups = {EditGroup.class})
    private Long conversationId;

    /**
     * 会话当前序列号
     */
    @NotNull(message = "会话当前序列号不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long conversationSeq;


}
