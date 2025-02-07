package com.imai.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imai.core.domain.ImUserPts;
import com.imai.core.domain.bo.ImUserPtsBo;
import com.imai.core.domain.vo.ImUserPtsVo;
import com.imai.core.mapper.ImUserPtsMapper;
import com.imai.core.service.IImUserPtsService;
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
 * 用户ptsService业务层处理
 *
 * @author wei
 * @date 2025-01-07
 */
@RequiredArgsConstructor
@Service
public class ImUserPtsServiceImpl implements IImUserPtsService {

    private final ImUserPtsMapper baseMapper;

    /**
     * 查询用户pts
     *
     * @param userId 主键
     * @return 用户pts
     */
    @Override
    public ImUserPtsVo queryById(Long userId) {
        return baseMapper.selectVoById(userId);
    }

    /**
     * 分页查询用户pts列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 用户pts分页列表
     */
    @Override
    public TableDataInfo<ImUserPtsVo> queryPageList(ImUserPtsBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ImUserPts> lqw = buildQueryWrapper(bo);
        Page<ImUserPtsVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的用户pts列表
     *
     * @param bo 查询条件
     * @return 用户pts列表
     */
    @Override
    public List<ImUserPtsVo> queryList(ImUserPtsBo bo) {
        LambdaQueryWrapper<ImUserPts> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ImUserPts> buildQueryWrapper(ImUserPtsBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ImUserPts> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getPts() != null, ImUserPts::getPts, bo.getPts());
        lqw.eq(bo.getDeleted() != null, ImUserPts::getDeleted, bo.getDeleted());
        lqw.eq(StringUtils.isNotBlank(bo.getExtras()), ImUserPts::getExtras, bo.getExtras());
        return lqw;
    }

    /**
     * 新增用户pts
     *
     * @param bo 用户pts
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ImUserPtsBo bo) {
        ImUserPts add = MapstructUtils.convert(bo, ImUserPts.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setUserId(add.getUserId());
        }
        return flag;
    }

    /**
     * 修改用户pts
     *
     * @param bo 用户pts
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ImUserPtsBo bo) {
        ImUserPts update = MapstructUtils.convert(bo, ImUserPts.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ImUserPts entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除用户pts信息
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
