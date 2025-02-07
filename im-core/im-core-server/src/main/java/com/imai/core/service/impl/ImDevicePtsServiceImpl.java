package com.imai.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imai.core.domain.ImDevicePts;
import com.imai.core.domain.bo.ImDevicePtsBo;
import com.imai.core.domain.vo.ImDevicePtsVo;
import com.imai.core.mapper.ImDevicePtsMapper;
import com.imai.core.service.IImDevicePtsService;
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
 * 设备ptsService业务层处理
 *
 * @author wei
 * @date 2025-01-07
 */
@RequiredArgsConstructor
@Service
public class ImDevicePtsServiceImpl implements IImDevicePtsService {

    private final ImDevicePtsMapper baseMapper;

    /**
     * 查询设备pts
     *
     * @param id 主键
     * @return 设备pts
     */
    @Override
    public ImDevicePtsVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询设备pts列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 设备pts分页列表
     */
    @Override
    public TableDataInfo<ImDevicePtsVo> queryPageList(ImDevicePtsBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ImDevicePts> lqw = buildQueryWrapper(bo);
        Page<ImDevicePtsVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的设备pts列表
     *
     * @param bo 查询条件
     * @return 设备pts列表
     */
    @Override
    public List<ImDevicePtsVo> queryList(ImDevicePtsBo bo) {
        LambdaQueryWrapper<ImDevicePts> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ImDevicePts> buildQueryWrapper(ImDevicePtsBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ImDevicePts> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFkUserId() != null, ImDevicePts::getFkUserId, bo.getFkUserId());
        lqw.eq(bo.getFkDeviceId() != null, ImDevicePts::getFkDeviceId, bo.getFkDeviceId());
        lqw.eq(bo.getMaxPts() != null, ImDevicePts::getMaxPts, bo.getMaxPts());
        lqw.eq(bo.getDeleted() != null, ImDevicePts::getDeleted, bo.getDeleted());
        lqw.eq(StringUtils.isNotBlank(bo.getExtras()), ImDevicePts::getExtras, bo.getExtras());
        return lqw;
    }

    /**
     * 新增设备pts
     *
     * @param bo 设备pts
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ImDevicePtsBo bo) {
        ImDevicePts add = MapstructUtils.convert(bo, ImDevicePts.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改设备pts
     *
     * @param bo 设备pts
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ImDevicePtsBo bo) {
        ImDevicePts update = MapstructUtils.convert(bo, ImDevicePts.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ImDevicePts entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除设备pts信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteByIds(ids) > 0;
    }
}
