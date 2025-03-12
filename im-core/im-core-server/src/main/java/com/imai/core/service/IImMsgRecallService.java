package com.imai.core.service;

import com.imai.core.domain.bo.ImMsgRecallBo;
import com.imai.core.domain.vo.ImMsgRecallVo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 消息撤回记录Service接口
 *
 * @author wei
 * @date 2025-01-07
 */
public interface IImMsgRecallService {

    /**
     * 撤回消息
     *
     * @param msgId  消息ID
     * @param userId 撤回用户ID
     * @return 是否撤回成功
     */
    Boolean recallMessage(Long msgId, Long userId);

    /**
     * 查询消息撤回记录
     *
     * @param id 主键
     * @return 消息撤回记录
     */
    ImMsgRecallVo queryById(Long id);

    /**
     * 分页查询消息撤回记录列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 消息撤回记录分页列表
     */
    TableDataInfo<ImMsgRecallVo> queryPageList(ImMsgRecallBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的消息撤回记录列表
     *
     * @param bo 查询条件
     * @return 消息撤回记录列表
     */
    List<ImMsgRecallVo> queryList(ImMsgRecallBo bo);

    /**
     * 新增消息撤回记录
     *
     * @param bo 消息撤回记录
     * @return 是否新增成功
     */
    Boolean insertByBo(ImMsgRecallBo bo);

    /**
     * 修改消息撤回记录
     *
     * @param bo 消息撤回记录
     * @return 是否修改成功
     */
    Boolean updateByBo(ImMsgRecallBo bo);

    /**
     * 校验并批量删除消息撤回记录信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
