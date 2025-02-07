package com.imai.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imai.core.domain.ImChannel;
import com.imai.core.domain.bo.ImChannelBo;
import com.imai.core.domain.vo.ImChannelVo;
import com.imai.core.mapper.ImChannelMapper;
import com.imai.core.service.IImChannelService;
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
 * 频道Service业务层处理
 *
 * @author wei
 * @date 2025-01-07
 */
@RequiredArgsConstructor
@Service
public class ImChannelServiceImpl implements IImChannelService {

    private final ImChannelMapper baseMapper;

    /**
     * 查询频道
     *
     * @param id 主键
     * @return 频道
     */
    @Override
    public ImChannelVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询频道列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 频道分页列表
     */
    @Override
    public TableDataInfo<ImChannelVo> queryPageList(ImChannelBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ImChannel> lqw = buildQueryWrapper(bo);
        Page<ImChannelVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的频道列表
     *
     * @param bo 查询条件
     * @return 频道列表
     */
    @Override
    public List<ImChannelVo> queryList(ImChannelBo bo) {
        LambdaQueryWrapper<ImChannel> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ImChannel> buildQueryWrapper(ImChannelBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ImChannel> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFkWorkspaceId() != null, ImChannel::getFkWorkspaceId, bo.getFkWorkspaceId());
        lqw.eq(bo.getFkConversationId() != null, ImChannel::getFkConversationId, bo.getFkConversationId());
        lqw.like(StringUtils.isNotBlank(bo.getChannelName()), ImChannel::getChannelName, bo.getChannelName());
        lqw.eq(bo.getChannelType() != null, ImChannel::getChannelType, bo.getChannelType());
        lqw.eq(StringUtils.isNotBlank(bo.getTopic()), ImChannel::getTopic, bo.getTopic());
        lqw.eq(StringUtils.isNotBlank(bo.getDescription()), ImChannel::getDescription, bo.getDescription());
        lqw.eq(bo.getParentId() != null, ImChannel::getParentId, bo.getParentId());
        lqw.eq(bo.getCreatorUserId() != null, ImChannel::getCreatorUserId, bo.getCreatorUserId());
        lqw.eq(bo.getSortOrder() != null, ImChannel::getSortOrder, bo.getSortOrder());
        lqw.eq(bo.getArchived() != null, ImChannel::getArchived, bo.getArchived());
        lqw.eq(bo.getDeleted() != null, ImChannel::getDeleted, bo.getDeleted());
        lqw.eq(StringUtils.isNotBlank(bo.getExtras()), ImChannel::getExtras, bo.getExtras());
        return lqw;
    }

    /**
     * 新增频道
     *
     * @param bo 频道
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ImChannelBo bo) {
        ImChannel add = MapstructUtils.convert(bo, ImChannel.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改频道
     *
     * @param bo 频道
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ImChannelBo bo) {
        ImChannel update = MapstructUtils.convert(bo, ImChannel.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ImChannel entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除频道信息
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
