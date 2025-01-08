package com.imai.core.service;

import com.imai.core.domain.bo.ImGroupMemberBo;
import com.imai.core.domain.vo.ImGroupMemberVo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 群成员Service接口
 *
 * @author wei
 * @date 2025-01-07
 */
public interface IImGroupMemberService {

    /**
     * 查询群成员
     *
     * @param id 主键
     * @return 群成员
     */
    ImGroupMemberVo queryById(Long id);

    /**
     * 分页查询群成员列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 群成员分页列表
     */
    TableDataInfo<ImGroupMemberVo> queryPageList(ImGroupMemberBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的群成员列表
     *
     * @param bo 查询条件
     * @return 群成员列表
     */
    List<ImGroupMemberVo> queryList(ImGroupMemberBo bo);

    /**
     * 新增群成员
     *
     * @param bo 群成员
     * @return 是否新增成功
     */
    Boolean insertByBo(ImGroupMemberBo bo);

    /**
     * 修改群成员
     *
     * @param bo 群成员
     * @return 是否修改成功
     */
    Boolean updateByBo(ImGroupMemberBo bo);

    /**
     * 校验并批量删除群成员信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
