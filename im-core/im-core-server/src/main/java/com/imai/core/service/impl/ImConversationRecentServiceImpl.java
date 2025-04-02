package com.imai.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imai.core.domain.ImConversationRecent;
import com.imai.core.domain.bo.ImConversationRecentBo;
import com.imai.core.domain.bo.ImMsgReadBo;
import com.imai.core.domain.vo.ImConversationRecentVo;
import com.imai.core.domain.vo.ImMsgReadVo;
import com.imai.core.mapper.ImConversationRecentMapper;
import com.imai.core.service.IImConversationRecentService;
import com.imai.core.service.IImMsgReadService;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 首页对话列Service业务层处理,负责维护最近会话列表,消息未读数
 *
 * @author wei
 * @date 2025-01-07
 */
@Service
public class ImConversationRecentServiceImpl implements IImConversationRecentService {
    @Lazy
    @Resource
    private ImConversationRecentMapper baseMapper;
    @Lazy
    @Resource
    private IImMsgReadService imMsgReadService;

    /**
     * 更新会话已读状态
     *
     * @param conversationId 会话ID
     * @param userId 用户ID
     * @return 是否更新成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateConversationRead(Long conversationId, Long userId) {
        // 1. 更新会话的未读消息数为0
        LambdaQueryWrapper<ImConversationRecent> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(ImConversationRecent::getFkConversationId, conversationId)
               .eq(ImConversationRecent::getFkUserId, userId);

        ImConversationRecent update = new ImConversationRecent();
        update.setNoReadCount(0L);
        boolean updateResult = baseMapper.update(update, wrapper) > 0;

        // 2. 更新消息已读状态
        ImMsgReadBo readBo = new ImMsgReadBo();
        readBo.setFkConversationId(conversationId);
        readBo.setFkReceiverUserId(userId);
        readBo.setReadMsgStatus(0L);
        List<ImMsgReadVo> unreadList = imMsgReadService.queryList(readBo);

        if (!unreadList.isEmpty()) {
            for (ImMsgReadVo vo : unreadList) {
                ImMsgReadBo updateBo = new ImMsgReadBo();
                updateBo.setId(vo.getId());
                updateBo.setReadMsgStatus(1L);
                updateBo.setReadTime(new Date());
                imMsgReadService.updateByBo(updateBo);
            }
        }

        return updateResult;
    }

    /**
     * 查询首页对话列
     *
     * @param id 主键
     * @return 首页对话列
     */
    @Override
    public ImConversationRecentVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询首页对话列列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 首页对话列分页列表
     */
    @Override
    public TableDataInfo<ImConversationRecentVo> queryPageList(ImConversationRecentBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ImConversationRecent> lqw = buildQueryWrapper(bo);
        Page<ImConversationRecentVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的首页对话列列表
     *
     * @param bo 查询条件
     * @return 首页对话列列表
     */
    @Override
    public List<ImConversationRecentVo> queryList(ImConversationRecentBo bo) {
        LambdaQueryWrapper<ImConversationRecent> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ImConversationRecent> buildQueryWrapper(ImConversationRecentBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ImConversationRecent> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFkUserId() != null, ImConversationRecent::getFkUserId, bo.getFkUserId());
        lqw.eq(bo.getFkConversationId() != null, ImConversationRecent::getFkConversationId, bo.getFkConversationId());
        lqw.eq(bo.getLastMsgId() != null, ImConversationRecent::getLastMsgId, bo.getLastMsgId());
        lqw.eq(bo.getLastMsgTime() != null, ImConversationRecent::getLastMsgTime, bo.getLastMsgTime());
        lqw.eq(bo.getNoReadCount() != null, ImConversationRecent::getNoReadCount, bo.getNoReadCount());
        lqw.eq(bo.getTopFlag() != null, ImConversationRecent::getTopFlag, bo.getTopFlag());
        lqw.eq(bo.getTopTime() != null, ImConversationRecent::getTopTime, bo.getTopTime());
        lqw.eq(bo.getRemovedFlag() != null, ImConversationRecent::getRemovedFlag, bo.getRemovedFlag());
        lqw.eq(bo.getRemovedTime() != null, ImConversationRecent::getRemovedTime, bo.getRemovedTime());
        lqw.eq(bo.getAtMeFlag() != null, ImConversationRecent::getAtMeFlag, bo.getAtMeFlag());
        lqw.eq(bo.getAtMeMsgId() != null, ImConversationRecent::getAtMeMsgId, bo.getAtMeMsgId());
        lqw.eq(bo.getConversationType() != null, ImConversationRecent::getConversationType, bo.getConversationType());
        return lqw;
    }

    /**
     * 新增首页对话列
     *
     * @param bo 首页对话列
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ImConversationRecentBo bo) {
        ImConversationRecent add = MapstructUtils.convert(bo, ImConversationRecent.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改首页对话列
     *
     * @param bo 首页对话列
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ImConversationRecentBo bo) {
        ImConversationRecent update = MapstructUtils.convert(bo, ImConversationRecent.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ImConversationRecent entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除首页对话列信息
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
