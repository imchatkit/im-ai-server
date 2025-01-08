package com.imai.core.service;

import com.imai.core.domain.bo.ImDeviceBo;
import com.imai.core.domain.vo.ImDeviceVo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 客户端设备Service接口
 *
 * @author wei
 * @date 2025-01-07
 */
public interface IImDeviceService {

    /**
     * 查询客户端设备
     *
     * @param id 主键
     * @return 客户端设备
     */
    ImDeviceVo queryById(Long id);

    /**
     * 分页查询客户端设备列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 客户端设备分页列表
     */
    TableDataInfo<ImDeviceVo> queryPageList(ImDeviceBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的客户端设备列表
     *
     * @param bo 查询条件
     * @return 客户端设备列表
     */
    List<ImDeviceVo> queryList(ImDeviceBo bo);

    /**
     * 新增客户端设备
     *
     * @param bo 客户端设备
     * @return 是否新增成功
     */
    Boolean insertByBo(ImDeviceBo bo);

    /**
     * 修改客户端设备
     *
     * @param bo 客户端设备
     * @return 是否修改成功
     */
    Boolean updateByBo(ImDeviceBo bo);

    /**
     * 校验并批量删除客户端设备信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
