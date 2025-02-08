package com.imai.core.service;

import com.imai.core.domain.bo.ImConversationBo;
import com.imai.core.domain.vo.ImConversationVo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 聊天会话基础Service接口
 *
 * @author wei
 * @date 2025-01-07
 */
public interface IImConversationService {

    /**
     * 查询聊天会话基础
     *
     * @param id 主键
     * @return 聊天会话基础
     */
    ImConversationVo queryById(Long id);

    /**
     * 分页查询聊天会话基础列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 聊天会话基础分页列表
     */
    TableDataInfo<ImConversationVo> queryPageList(ImConversationBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的聊天会话基础列表
     *
     * @param bo 查询条件
     * @return 聊天会话基础列表
     */
    List<ImConversationVo> queryList(ImConversationBo bo);

    /**
     * 新增聊天会话基础
     *
     * @param bo 聊天会话基础
     * @return 是否新增成功
     */
    Boolean insertByBo(ImConversationBo bo);

    /**
     * 修改聊天会话基础
     *
     * @param bo 聊天会话基础
     * @return 是否修改成功
     */
    Boolean updateByBo(ImConversationBo bo);

    /**
     * 校验并批量删除聊天会话基础信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 创建陌生人会话
     *
     * @param bo 会话信息
     * @param targetUserId 目标用户ID
     * @return 是否创建成功
     */
    Boolean createStrangerConversation(ImConversationBo bo, Long targetUserId);

    /**
     * 查询当前用户加入的会话列表
     *
     * @param pageQuery 分页参数
     * @return 会话分页列表
     */
    TableDataInfo<ImConversationVo> queryJoinedConversations(PageQuery pageQuery);

    /**
     * 分页查询当前用户加入的会话列表（通过会话成员分页）
     *
     * @param pageQuery 分页参数
     * @return 会话分页列表
     */
    TableDataInfo<ImConversationVo> queryJoinedConversationsByMemberPage(PageQuery pageQuery);
}
