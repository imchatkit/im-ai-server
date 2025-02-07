package com.imai.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imai.core.domain.ImConversationMember;
import com.imai.core.domain.bo.ImConversationMemberBatchAddBo;
import com.imai.core.domain.bo.ImConversationMemberBo;
import com.imai.core.domain.vo.ImConversationMemberVo;
import com.imai.core.mapper.ImConversationMemberMapper;
import com.imai.core.service.IImConversationMemberService;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 会话成员Service业务层处理
 *
 * @author wei
 * @date 2025-01-07
 */
@RequiredArgsConstructor
@Service
public class ImConversationMemberServiceImpl implements IImConversationMemberService {

    private final ImConversationMemberMapper baseMapper;

    /**
     * 查询会话成员
     *
     * @param id 主键
     * @return 会话成员
     */
    @Override
    public ImConversationMemberVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询会话成员列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 会话成员分页列表
     */
    @Override
    public TableDataInfo<ImConversationMemberVo> queryPageList(ImConversationMemberBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ImConversationMember> lqw = buildQueryWrapper(bo);
        Page<ImConversationMemberVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的会话成员列表
     *
     * @param bo 查询条件
     * @return 会话成员列表
     */
    @Override
    public List<ImConversationMemberVo> queryList(ImConversationMemberBo bo) {
        LambdaQueryWrapper<ImConversationMember> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ImConversationMember> buildQueryWrapper(ImConversationMemberBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ImConversationMember> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFkConversationId() != null, ImConversationMember::getFkConversationId, bo.getFkConversationId());
        lqw.eq(bo.getFkUserId() != null, ImConversationMember::getFkUserId, bo.getFkUserId());
        lqw.eq(StringUtils.isNotBlank(bo.getExtras()), ImConversationMember::getExtras, bo.getExtras());
        lqw.like(StringUtils.isNotBlank(bo.getUserRemarkName()), ImConversationMember::getUserRemarkName, bo.getUserRemarkName());
        lqw.eq(bo.getRole() != null, ImConversationMember::getRole, bo.getRole());
        lqw.eq(bo.getDisturbFlag() != null, ImConversationMember::getDisturbFlag, bo.getDisturbFlag());
        lqw.eq(bo.getTopFlag() != null, ImConversationMember::getTopFlag, bo.getTopFlag());
        lqw.eq(bo.getDeleted() != null, ImConversationMember::getDeleted, bo.getDeleted());
        lqw.eq(bo.getMuteAtAll() != null, ImConversationMember::getMuteAtAll, bo.getMuteAtAll());
        lqw.eq(bo.getMuted() != null, ImConversationMember::getMuted, bo.getMuted());
        lqw.eq(bo.getMuteEndTime() != null, ImConversationMember::getMuteEndTime, bo.getMuteEndTime());
        return lqw;
    }

    /**
     * 新增会话成员
     *
     * @param bo 会话成员
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ImConversationMemberBo bo) {
        ImConversationMember add = MapstructUtils.convert(bo, ImConversationMember.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改会话成员
     *
     * @param bo 会话成员
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ImConversationMemberBo bo) {
        ImConversationMember update = MapstructUtils.convert(bo, ImConversationMember.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ImConversationMember entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除会话成员信息
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

    /**
     * 批量添加用户到会话
     *
     * @param bo 批量添加会话成员参数
     * @return 是否添加成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean batchAddUsersToConversationForApi(ImConversationMemberBatchAddBo bo) {
        if (bo == null || bo.getConversationId() == null || bo.getUserIds() == null || bo.getUserIds().isEmpty()) {
            return false;
        }

        // 检查这些用户是否已经在会话中
        LambdaQueryWrapper<ImConversationMember> checkWrapper = Wrappers.lambdaQuery();
        checkWrapper.eq(ImConversationMember::getFkConversationId, bo.getConversationId())
            .in(ImConversationMember::getFkUserId, bo.getUserIds());
        List<ImConversationMember> existingMembers = baseMapper.selectList(checkWrapper);

        // 过滤掉已经存在的用户
        Set<Long> existingUserIds = existingMembers.stream()
            .map(ImConversationMember::getFkUserId)
            .collect(Collectors.toSet());
        List<Long> newUserIds = bo.getUserIds().stream()
            .filter(userId -> !existingUserIds.contains(userId))
            .collect(Collectors.toList());

        if (newUserIds.isEmpty()) {
            return true; // 所有用户都已经在会话中
        }

        // 批量插入新成员
        List<ImConversationMember> membersToAdd = newUserIds.stream()
            .map(userId -> {
                ImConversationMember member = new ImConversationMember();
                member.setFkConversationId(bo.getConversationId());
                member.setFkUserId(userId);
                member.setRole(bo.getRole() != null ? bo.getRole() : 0L);
                member.setDisturbFlag(bo.getDisturbFlag() != null ? bo.getDisturbFlag() : 0L);
                member.setTopFlag(bo.getTopFlag() != null ? bo.getTopFlag() : 0L);
                member.setMuteAtAll(bo.getMuteAtAll() != null ? bo.getMuteAtAll() : 0L);
                member.setMuted(bo.getMuted() != null ? bo.getMuted() : 0L);
                return member;
            })
            .collect(Collectors.toList());

        return baseMapper.insertBatch(membersToAdd);
    }

    /**
     * 根据会话ID查询成员列表
     *
     * @param conversationId 会话ID
     * @return 会话成员列表
     */
    @Override
    public List<ImConversationMemberVo> queryListByConversationIdForApi(Long conversationId) {
        LambdaQueryWrapper<ImConversationMember> lqw = Wrappers.lambdaQuery();
        lqw.eq(ImConversationMember::getFkConversationId, conversationId)
            .eq(ImConversationMember::getDeleted, 0L); // 只查询未删除的成员
        return baseMapper.selectVoList(lqw);
    }

    @Override
    public Boolean contains(Long conversationId, Long userId) {
        LambdaQueryWrapper<ImConversationMember> lqw = Wrappers.lambdaQuery();
        lqw.eq(ImConversationMember::getFkConversationId, conversationId)
            .eq(ImConversationMember::getFkUserId, userId)
            .eq(ImConversationMember::getDeleted, 0L);
        return baseMapper.exists(lqw);
    }
}
