package com.imai.core.service;

import com.imai.core.domain.bo.ImConversationMemberBo;
import com.imai.core.domain.vo.ImConversationMemberVo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 会话成员Service接口
 *
 * @author wei
 * @date 2025-01-07
 */
public interface IImConversationMemberService {

    /**
     * 查询会话成员
     *
     * @param id 主键
     * @return 会话成员
     */
    ImConversationMemberVo queryById(Long id);

    /**
     * 分页查询会话成员列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 会话成员分页列表
     */
    TableDataInfo<ImConversationMemberVo> queryPageList(ImConversationMemberBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的会话成员列表
     *
     * @param bo 查询条件
     * @return 会话成员列表
     */
    List<ImConversationMemberVo> queryList(ImConversationMemberBo bo);

    /**
     * 新增会话成员
     *
     * @param bo 会话成员
     * @return 是否新增成功
     */
    Boolean insertByBo(ImConversationMemberBo bo);

    /**
     * 修改会话成员
     *
     * @param bo 会话成员
     * @return 是否修改成功
     */
    Boolean updateByBo(ImConversationMemberBo bo);

    /**
     * 校验并批量删除会话成员信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
