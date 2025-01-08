package com.imai.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imai.core.domain.ImConversationMember;
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

import java.util.Collection;
import java.util.List;
import java.util.Map;

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
    public ImConversationMemberVo queryById(Long id){
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
    private void validEntityBeforeSave(ImConversationMember entity){
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
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteByIds(ids) > 0;
    }
}
