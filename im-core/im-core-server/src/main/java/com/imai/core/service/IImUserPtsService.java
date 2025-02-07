package com.imai.core.service;

import com.imai.core.domain.bo.ImUserPtsBo;
import com.imai.core.domain.vo.ImUserPtsVo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 用户ptsService接口
 *
 * @author wei
 * @date 2025-01-07
 */
public interface IImUserPtsService {

    /**
     * 查询用户pts
     *
     * @param userId 主键
     * @return 用户pts
     */
    ImUserPtsVo queryById(Long userId);

    /**
     * 分页查询用户pts列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 用户pts分页列表
     */
    TableDataInfo<ImUserPtsVo> queryPageList(ImUserPtsBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的用户pts列表
     *
     * @param bo 查询条件
     * @return 用户pts列表
     */
    List<ImUserPtsVo> queryList(ImUserPtsBo bo);

    /**
     * 新增用户pts
     *
     * @param bo 用户pts
     * @return 是否新增成功
     */
    Boolean insertByBo(ImUserPtsBo bo);

    /**
     * 修改用户pts
     *
     * @param bo 用户pts
     * @return 是否修改成功
     */
    Boolean updateByBo(ImUserPtsBo bo);

    /**
     * 校验并批量删除用户pts信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
