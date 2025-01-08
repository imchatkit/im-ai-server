package com.imai.core.service;

import com.imai.core.domain.bo.ImConversationSeqBo;
import com.imai.core.domain.vo.ImConversationSeqVo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 会话序列号Service接口
 *
 * @author wei
 * @date 2025-01-07
 */
public interface IImConversationSeqService {

    /**
     * 查询会话序列号
     *
     * @param conversationId 主键
     * @return 会话序列号
     */
    ImConversationSeqVo queryById(Long conversationId);

    /**
     * 分页查询会话序列号列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 会话序列号分页列表
     */
    TableDataInfo<ImConversationSeqVo> queryPageList(ImConversationSeqBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的会话序列号列表
     *
     * @param bo 查询条件
     * @return 会话序列号列表
     */
    List<ImConversationSeqVo> queryList(ImConversationSeqBo bo);

    /**
     * 新增会话序列号
     *
     * @param bo 会话序列号
     * @return 是否新增成功
     */
    Boolean insertByBo(ImConversationSeqBo bo);

    /**
     * 修改会话序列号
     *
     * @param bo 会话序列号
     * @return 是否修改成功
     */
    Boolean updateByBo(ImConversationSeqBo bo);

    /**
     * 校验并批量删除会话序列号信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
