package com.imai.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imai.core.domain.ImSync;
import com.imai.core.domain.bo.ImSyncBo;
import com.imai.core.domain.vo.ImSyncVo;
import com.imai.core.mapper.ImSyncMapper;
import com.imai.core.service.IImSyncService;
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
 * 多端同步Service业务层处理
 *
 * @author wei
 * @date 2025-01-07
 */
@RequiredArgsConstructor
@Service
public class ImSyncServiceImpl implements IImSyncService {

    private final ImSyncMapper baseMapper;

    /**
     * 查询多端同步
     *
     * @param id 主键
     * @return 多端同步
     */
    @Override
    public ImSyncVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询多端同步列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 多端同步分页列表
     */
    @Override
    public TableDataInfo<ImSyncVo> queryPageList(ImSyncBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ImSync> lqw = buildQueryWrapper(bo);
        Page<ImSyncVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的多端同步列表
     *
     * @param bo 查询条件
     * @return 多端同步列表
     */
    @Override
    public List<ImSyncVo> queryList(ImSyncBo bo) {
        LambdaQueryWrapper<ImSync> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ImSync> buildQueryWrapper(ImSyncBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ImSync> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFkUserId() != null, ImSync::getFkUserId, bo.getFkUserId());
        lqw.eq(bo.getPts() != null, ImSync::getPts, bo.getPts());
        lqw.eq(bo.getFkMsgId() != null, ImSync::getFkMsgId, bo.getFkMsgId());
        lqw.eq(bo.getDeleted() != null, ImSync::getDeleted, bo.getDeleted());
        lqw.eq(StringUtils.isNotBlank(bo.getExtras()), ImSync::getExtras, bo.getExtras());
        return lqw;
    }

    /**
     * 新增多端同步
     *
     * @param bo 多端同步
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ImSyncBo bo) {
        ImSync add = MapstructUtils.convert(bo, ImSync.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改多端同步
     *
     * @param bo 多端同步
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ImSyncBo bo) {
        ImSync update = MapstructUtils.convert(bo, ImSync.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ImSync entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除多端同步信息
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
