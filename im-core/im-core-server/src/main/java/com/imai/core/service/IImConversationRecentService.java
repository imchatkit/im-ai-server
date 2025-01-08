package com.imai.core.service;

import com.imai.core.domain.bo.ImConversationRecentBo;
import com.imai.core.domain.vo.ImConversationRecentVo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 首页对话列Service接口
 *
 * @author wei
 * @date 2025-01-07
 */
public interface IImConversationRecentService {

    /**
     * 查询首页对话列
     *
     * @param id 主键
     * @return 首页对话列
     */
    ImConversationRecentVo queryById(Long id);

    /**
     * 分页查询首页对话列列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 首页对话列分页列表
     */
    TableDataInfo<ImConversationRecentVo> queryPageList(ImConversationRecentBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的首页对话列列表
     *
     * @param bo 查询条件
     * @return 首页对话列列表
     */
    List<ImConversationRecentVo> queryList(ImConversationRecentBo bo);

    /**
     * 新增首页对话列
     *
     * @param bo 首页对话列
     * @return 是否新增成功
     */
    Boolean insertByBo(ImConversationRecentBo bo);

    /**
     * 修改首页对话列
     *
     * @param bo 首页对话列
     * @return 是否修改成功
     */
    Boolean updateByBo(ImConversationRecentBo bo);

    /**
     * 校验并批量删除首页对话列信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
