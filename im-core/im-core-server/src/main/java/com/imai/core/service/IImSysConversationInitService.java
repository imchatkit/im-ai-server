package com.imai.core.service;

import com.imai.core.domain.bo.ImSysConversationInitBo;
import com.imai.core.domain.vo.ImSysConversationInitVo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 系统会话初始化Service接口
 *
 * @author wei
 * @date 2025-01-07
 */
public interface IImSysConversationInitService {

    /**
     * 查询系统会话初始化
     *
     * @param id 主键
     * @return 系统会话初始化
     */
    ImSysConversationInitVo queryById(Long id);

    /**
     * 分页查询系统会话初始化列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 系统会话初始化分页列表
     */
    TableDataInfo<ImSysConversationInitVo> queryPageList(ImSysConversationInitBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的系统会话初始化列表
     *
     * @param bo 查询条件
     * @return 系统会话初始化列表
     */
    List<ImSysConversationInitVo> queryList(ImSysConversationInitBo bo);

    /**
     * 新增系统会话初始化
     *
     * @param bo 系统会话初始化
     * @return 是否新增成功
     */
    Boolean insertByBo(ImSysConversationInitBo bo);

    /**
     * 修改系统会话初始化
     *
     * @param bo 系统会话初始化
     * @return 是否修改成功
     */
    Boolean updateByBo(ImSysConversationInitBo bo);

    /**
     * 校验并批量删除系统会话初始化信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
