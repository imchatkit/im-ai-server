package com.imai.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imai.core.domain.ImDevice;
import com.imai.core.domain.bo.ImDeviceBo;
import com.imai.core.domain.vo.ImDeviceVo;
import com.imai.core.mapper.ImDeviceMapper;
import com.imai.core.service.IImDeviceService;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 客户端设备Service业务层处理
 *
 * @author wei
 * @date 2025-01-07
 */
@RequiredArgsConstructor
@Service
public class ImDeviceServiceImpl implements IImDeviceService {

    private final ImDeviceMapper baseMapper;

    /**
     * 查询客户端设备
     *
     * @param id 主键
     * @return 客户端设备
     */
    @Override
    public ImDeviceVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询客户端设备列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 客户端设备分页列表
     */
    @Override
    public TableDataInfo<ImDeviceVo> queryPageList(ImDeviceBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ImDevice> lqw = buildQueryWrapper(bo);
        Page<ImDeviceVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的客户端设备列表
     *
     * @param bo 查询条件
     * @return 客户端设备列表
     */
    @Override
    public List<ImDeviceVo> queryList(ImDeviceBo bo) {
        LambdaQueryWrapper<ImDevice> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ImDevice> buildQueryWrapper(ImDeviceBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ImDevice> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFkUserId() != null, ImDevice::getFkUserId, bo.getFkUserId());
        lqw.eq(bo.getValid() != null, ImDevice::getValid, bo.getValid());
        lqw.eq(StringUtils.isNotBlank(bo.getPushToken()), ImDevice::getPushToken, bo.getPushToken());
        lqw.eq(StringUtils.isNotBlank(bo.getUniqueDeviceCode()), ImDevice::getUniqueDeviceCode, bo.getUniqueDeviceCode());
        lqw.eq(StringUtils.isNotBlank(bo.getPushChannel()), ImDevice::getPushChannel, bo.getPushChannel());
        lqw.eq(bo.getPlatform() != null, ImDevice::getPlatform, bo.getPlatform());
        lqw.eq(bo.getDeviceStatus() != null, ImDevice::getDeviceStatus, bo.getDeviceStatus());
        lqw.eq(bo.getDeleted() != null, ImDevice::getDeleted, bo.getDeleted());
        lqw.eq(StringUtils.isNotBlank(bo.getExtras()), ImDevice::getExtras, bo.getExtras());
        return lqw;
    }

    /**
     * 新增客户端设备
     *
     * @param bo 客户端设备
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ImDeviceBo bo) {
        ImDevice add = MapstructUtils.convert(bo, ImDevice.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改客户端设备
     *
     * @param bo 客户端设备
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ImDeviceBo bo) {
        ImDevice update = MapstructUtils.convert(bo, ImDevice.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ImDevice entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除客户端设备信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteByIds(ids) > 0;
    }
}
