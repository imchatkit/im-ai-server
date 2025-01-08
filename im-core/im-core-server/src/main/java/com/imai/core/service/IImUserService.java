package com.imai.core.service;

import com.imai.core.domain.bo.ImUserBo;
import com.imai.core.domain.vo.ImUserVo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 用户Service接口
 *
 * @author wei
 * @date 2025-01-07
 */
public interface IImUserService {

    /**
     * 查询用户
     *
     * @param id 主键
     * @return 用户
     */
    ImUserVo queryById(Long id);

    /**
     * 分页查询用户列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 用户分页列表
     */
    TableDataInfo<ImUserVo> queryPageList(ImUserBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的用户列表
     *
     * @param bo 查询条件
     * @return 用户列表
     */
    List<ImUserVo> queryList(ImUserBo bo);

    /**
     * 新增用户
     *
     * @param bo 用户
     * @return 是否新增成功
     */
    Boolean insertByBo(ImUserBo bo);

    /**
     * 修改用户
     *
     * @param bo 用户
     * @return 是否修改成功
     */
    Boolean updateByBo(ImUserBo bo);

    /**
     * 校验并批量删除用户信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
