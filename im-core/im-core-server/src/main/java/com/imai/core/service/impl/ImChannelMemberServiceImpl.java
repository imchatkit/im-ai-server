package com.imai.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imai.core.domain.ImChannelMember;
import com.imai.core.domain.bo.ImChannelMemberBo;
import com.imai.core.domain.vo.ImChannelMemberVo;
import com.imai.core.mapper.ImChannelMemberMapper;
import com.imai.core.service.IImChannelMemberService;
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
 * 频道成员Service业务层处理
 *
 * @author wei
 * @date 2025-01-07
 */
@RequiredArgsConstructor
@Service
public class ImChannelMemberServiceImpl implements IImChannelMemberService {

    private final ImChannelMemberMapper baseMapper;

    /**
     * 查询频道成员
     *
     * @param id 主键
     * @return 频道成员
     */
    @Override
    public ImChannelMemberVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询频道成员列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 频道成员分页列表
     */
    @Override
    public TableDataInfo<ImChannelMemberVo> queryPageList(ImChannelMemberBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ImChannelMember> lqw = buildQueryWrapper(bo);
        Page<ImChannelMemberVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的频道成员列表
     *
     * @param bo 查询条件
     * @return 频道成员列表
     */
    @Override
    public List<ImChannelMemberVo> queryList(ImChannelMemberBo bo) {
        LambdaQueryWrapper<ImChannelMember> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ImChannelMember> buildQueryWrapper(ImChannelMemberBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ImChannelMember> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFkChannelId() != null, ImChannelMember::getFkChannelId, bo.getFkChannelId());
        lqw.eq(bo.getFkUserId() != null, ImChannelMember::getFkUserId, bo.getFkUserId());
        lqw.eq(bo.getMemberRole() != null, ImChannelMember::getMemberRole, bo.getMemberRole());
        lqw.eq(bo.getJoinTime() != null, ImChannelMember::getJoinTime, bo.getJoinTime());
        lqw.eq(bo.getNotificationLevel() != null, ImChannelMember::getNotificationLevel, bo.getNotificationLevel());
        lqw.eq(bo.getStarred() != null, ImChannelMember::getStarred, bo.getStarred());
        lqw.eq(bo.getDeleted() != null, ImChannelMember::getDeleted, bo.getDeleted());
        lqw.eq(StringUtils.isNotBlank(bo.getExtras()), ImChannelMember::getExtras, bo.getExtras());
        return lqw;
    }

    /**
     * 新增频道成员
     *
     * @param bo 频道成员
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ImChannelMemberBo bo) {
        ImChannelMember add = MapstructUtils.convert(bo, ImChannelMember.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改频道成员
     *
     * @param bo 频道成员
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ImChannelMemberBo bo) {
        ImChannelMember update = MapstructUtils.convert(bo, ImChannelMember.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ImChannelMember entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除频道成员信息
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
