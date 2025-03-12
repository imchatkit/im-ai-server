package com.imai.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imai.core.domain.ImGroup;
import com.imai.core.domain.bo.ImGroupBo;
import com.imai.core.domain.vo.ImGroupVo;
import com.imai.core.mapper.ImGroupMapper;
import com.imai.core.service.IImGroupService;
import com.imai.ws.WebSocketMessage;
import com.imai.ws.enums.MsgType;

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
 * 群组Service业务层处理
 *
 * @author wei
 * @date 2025-01-07
 */
@RequiredArgsConstructor
@Service
public class ImGroupServiceImpl implements IImGroupService {

    private final ImGroupMapper baseMapper;

    private final ImGroupMemberServiceImpl groupMemberService;

    /**
     * 查询群组
     *
     * @param id 主键
     * @return 群组
     */
    @Override
    public ImGroupVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询群组列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 群组分页列表
     */
    @Override
    public TableDataInfo<ImGroupVo> queryPageList(ImGroupBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ImGroup> lqw = buildQueryWrapper(bo);
        Page<ImGroupVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的群组列表
     *
     * @param bo 查询条件
     * @return 群组列表
     */
    @Override
    public List<ImGroupVo> queryList(ImGroupBo bo) {
        LambdaQueryWrapper<ImGroup> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ImGroup> buildQueryWrapper(ImGroupBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ImGroup> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFkConversationId() != null, ImGroup::getFkConversationId, bo.getFkConversationId());
        lqw.like(StringUtils.isNotBlank(bo.getName()), ImGroup::getName, bo.getName());
        lqw.eq(bo.getOwnerId() != null, ImGroup::getOwnerId, bo.getOwnerId());
        lqw.eq(bo.getGroupType() != null, ImGroup::getGroupType, bo.getGroupType());
        lqw.eq(bo.getMaxMemberCount() != null, ImGroup::getMaxMemberCount, bo.getMaxMemberCount());
        lqw.eq(bo.getJoinType() != null, ImGroup::getJoinType, bo.getJoinType());
        lqw.eq(StringUtils.isNotBlank(bo.getNotice()), ImGroup::getNotice, bo.getNotice());
        lqw.eq(bo.getOrgId() != null, ImGroup::getOrgId, bo.getOrgId());
        lqw.eq(bo.getDeptId() != null, ImGroup::getDeptId, bo.getDeptId());
        lqw.eq(StringUtils.isNotBlank(bo.getExtras()), ImGroup::getExtras, bo.getExtras());
        return lqw;
    }

    /**
     * 新增群组
     *
     * @param bo 群组
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ImGroupBo bo) {
        ImGroup add = MapstructUtils.convert(bo, ImGroup.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改群组
     *
     * @param bo 群组
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ImGroupBo bo) {
        ImGroup update = MapstructUtils.convert(bo, ImGroup.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ImGroup entity) {
        // TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除群组信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            // TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteByIds(ids) > 0;
    }

    /**
     * 查询我加入的群组列表
     *
     * @param userId 用户ID
     * @return 群组列表
     */
    @Override
    public List<ImGroupVo> queryMyJoinedGroups(Long userId) {
        List<Long> groupIds = groupMemberService.queryUserJoinedGroupIds(userId);
        if (groupIds.isEmpty()) {
            return List.of();
        }
        LambdaQueryWrapper<ImGroup> lqw = Wrappers.lambdaQuery();
        lqw.in(ImGroup::getId, groupIds);
        return baseMapper.selectVoList(lqw);
    }

    /**
     * 查询我创建的群组列表
     *
     * @param userId 用户ID
     * @return 群组列表
     */
    @Override
    public List<ImGroupVo> queryMyCreatedGroups(Long userId) {
        LambdaQueryWrapper<ImGroup> lqw = Wrappers.lambdaQuery();
        lqw.eq(ImGroup::getOwnerId, userId);
        return baseMapper.selectVoList(lqw);
    }

}
