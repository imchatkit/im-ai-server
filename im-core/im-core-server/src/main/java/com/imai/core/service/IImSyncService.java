package com.imai.core.service;

import com.imai.core.domain.bo.ImSyncBo;
import com.imai.core.domain.vo.ImSyncVo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 多端同步Service接口
 *
 * @author wei
 * @date 2025-01-07
 */
public interface IImSyncService {

    /**
     * 查询多端同步
     *
     * @param id 主键
     * @return 多端同步
     */
    ImSyncVo queryById(Long id);

    /**
     * 分页查询多端同步列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 多端同步分页列表
     */
    TableDataInfo<ImSyncVo> queryPageList(ImSyncBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的多端同步列表
     *
     * @param bo 查询条件
     * @return 多端同步列表
     */
    List<ImSyncVo> queryList(ImSyncBo bo);

    /**
     * 新增多端同步
     *
     * @param bo 多端同步
     * @return 是否新增成功
     */
    Boolean insertByBo(ImSyncBo bo);

    /**
     * 修改多端同步
     *
     * @param bo 多端同步
     * @return 是否修改成功
     */
    Boolean updateByBo(ImSyncBo bo);

    /**
     * 校验并批量删除多端同步信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
