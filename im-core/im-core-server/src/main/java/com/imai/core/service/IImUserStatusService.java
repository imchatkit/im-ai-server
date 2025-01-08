package com.imai.core.service;

import com.imai.core.domain.bo.ImUserStatusBo;
import com.imai.core.domain.vo.ImUserStatusVo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 用户状态Service接口
 *
 * @author wei
 * @date 2025-01-07
 */
public interface IImUserStatusService {

    /**
     * 查询用户状态
     *
     * @param userId 主键
     * @return 用户状态
     */
    ImUserStatusVo queryById(Long userId);

    /**
     * 分页查询用户状态列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 用户状态分页列表
     */
    TableDataInfo<ImUserStatusVo> queryPageList(ImUserStatusBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的用户状态列表
     *
     * @param bo 查询条件
     * @return 用户状态列表
     */
    List<ImUserStatusVo> queryList(ImUserStatusBo bo);

    /**
     * 新增用户状态
     *
     * @param bo 用户状态
     * @return 是否新增成功
     */
    Boolean insertByBo(ImUserStatusBo bo);

    /**
     * 修改用户状态
     *
     * @param bo 用户状态
     * @return 是否修改成功
     */
    Boolean updateByBo(ImUserStatusBo bo);

    /**
     * 校验并批量删除用户状态信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
