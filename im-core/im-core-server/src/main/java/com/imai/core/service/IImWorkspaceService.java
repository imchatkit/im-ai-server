package com.imai.core.service;

import com.imai.core.domain.bo.ImWorkspaceBo;
import com.imai.core.domain.vo.ImWorkspaceVo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 工作空间Service接口
 *
 * @author wei
 * @date 2025-01-07
 */
public interface IImWorkspaceService {

    /**
     * 查询工作空间
     *
     * @param id 主键
     * @return 工作空间
     */
    ImWorkspaceVo queryById(Long id);

    /**
     * 分页查询工作空间列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 工作空间分页列表
     */
    TableDataInfo<ImWorkspaceVo> queryPageList(ImWorkspaceBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的工作空间列表
     *
     * @param bo 查询条件
     * @return 工作空间列表
     */
    List<ImWorkspaceVo> queryList(ImWorkspaceBo bo);

    /**
     * 新增工作空间
     *
     * @param bo 工作空间
     * @return 是否新增成功
     */
    Boolean insertByBo(ImWorkspaceBo bo);

    /**
     * 修改工作空间
     *
     * @param bo 工作空间
     * @return 是否修改成功
     */
    Boolean updateByBo(ImWorkspaceBo bo);

    /**
     * 校验并批量删除工作空间信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
