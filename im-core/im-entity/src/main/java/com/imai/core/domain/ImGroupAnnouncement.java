package com.imai.core.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;

/**
 * 群公告对象 im_group_announcement
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("im_group_announcement")
public class ImGroupAnnouncement extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 公告id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 群组id
     */
    private Long fkGroupId;

    /**
     * 公告内容
     */
    private String content;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    private Long deleted;

    /**
     * 可选 自定义属性，供开发者扩展使用
     */
    private String extras;


}
