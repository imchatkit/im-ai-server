package com.imai.core.service;

import com.imai.core.domain.bo.ImDevicePtsBo;
import com.imai.core.domain.vo.ImDevicePtsVo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 设备ptsService接口
 *
 * @author wei
 * @date 2025-01-07
 */
public interface IImDevicePtsService {

    /**
     * 查询设备pts
     *
     * @param id 主键
     * @return 设备pts
     */
    ImDevicePtsVo queryById(Long id);

    /**
     * 分页查询设备pts列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 设备pts分页列表
     */
    TableDataInfo<ImDevicePtsVo> queryPageList(ImDevicePtsBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的设备pts列表
     *
     * @param bo 查询条件
     * @return 设备pts列表
     */
    List<ImDevicePtsVo> queryList(ImDevicePtsBo bo);

    /**
     * 新增设备pts
     *
     * @param bo 设备pts
     * @return 是否新增成功
     */
    Boolean insertByBo(ImDevicePtsBo bo);

    /**
     * 修改设备pts
     *
     * @param bo 设备pts
     * @return 是否修改成功
     */
    Boolean updateByBo(ImDevicePtsBo bo);

    /**
     * 校验并批量删除设备pts信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
