package com.imai.core.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;

/**
 * 消息引用路径对象 im_msg_reference_path
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("im_msg_reference_path")
public class ImMsgReferencePath extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 消息ID
     */
    private Long fkMsgId;

    /**
     * 祖先消息ID
     */
    private Long ancestorMsgId;

    /**
     * 引用深度(层级距离)
     */
    private Long distance;

    /**
     * 引用路径(格式:id1->id2->id3)
     */
    private String path;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    private Long deleted;

    /**
     * 可选 自定义属性，供开发者扩展使用
     */
    private String extras;


}
