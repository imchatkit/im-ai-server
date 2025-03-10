package com.imai.core.service;

import com.imai.core.domain.bo.ImMessageBo;
import com.imai.core.domain.vo.ImMessageVo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 消息存储Service接口
 *
 * @author wei
 * @date 2025-01-07
 */
public interface IImMessageService {

    /**
     * 查询消息存储
     *
     * @param id 主键
     * @return 消息存储
     */
    ImMessageVo queryById(Long id);

    /**
     * 分页查询消息存储列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 消息存储分页列表
     */
    TableDataInfo<ImMessageVo> queryPageList(ImMessageBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的消息存储列表
     *
     * @param bo 查询条件
     * @return 消息存储列表
     */
    List<ImMessageVo> queryList(ImMessageBo bo);

    /**
     * 新增消息存储
     *
     * @param bo 消息存储
     * @return 是否新增成功
     */
    Boolean insertByBo(ImMessageBo bo);

    /**
     * 修改消息存储
     *
     * @param bo 消息存储
     * @return 是否修改成功
     */
    Boolean updateByBo(ImMessageBo bo);

    /**
     * 校验并批量删除消息存储信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 根据 fkConversationId 查询消息列表，查询数量限制100条
     *
     * @param fkConversationId 会话ID
     * @return 消息存储列表
     */
    List<ImMessageVo> queryListByFkConversationId(Long fkConversationId);
}