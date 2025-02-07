package com.imai.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imai.core.domain.ImConversationSeq;
import com.imai.core.domain.bo.ImConversationSeqBo;
import com.imai.core.domain.vo.ImConversationSeqVo;
import com.imai.core.mapper.ImConversationSeqMapper;
import com.imai.core.service.IImConversationSeqService;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 会话序列号Service业务层处理
 *
 * @author wei
 * @date 2025-01-07
 */
@RequiredArgsConstructor
@Service
public class ImConversationSeqServiceImpl implements IImConversationSeqService {

    private final ImConversationSeqMapper baseMapper;

    /**
     * 查询会话序列号
     *
     * @param conversationId 主键
     * @return 会话序列号
     */
    @Override
    public ImConversationSeqVo queryById(Long conversationId) {
        return baseMapper.selectVoById(conversationId);
    }

    /**
     * 分页查询会话序列号列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 会话序列号分页列表
     */
    @Override
    public TableDataInfo<ImConversationSeqVo> queryPageList(ImConversationSeqBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ImConversationSeq> lqw = buildQueryWrapper(bo);
        Page<ImConversationSeqVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的会话序列号列表
     *
     * @param bo 查询条件
     * @return 会话序列号列表
     */
    @Override
    public List<ImConversationSeqVo> queryList(ImConversationSeqBo bo) {
        LambdaQueryWrapper<ImConversationSeq> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ImConversationSeq> buildQueryWrapper(ImConversationSeqBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ImConversationSeq> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getConversationSeq() != null, ImConversationSeq::getConversationSeq, bo.getConversationSeq());
        return lqw;
    }

    /**
     * 新增会话序列号
     *
     * @param bo 会话序列号
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ImConversationSeqBo bo) {
        ImConversationSeq add = MapstructUtils.convert(bo, ImConversationSeq.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setConversationId(add.getConversationId());
        }
        return flag;
    }

    /**
     * 修改会话序列号
     *
     * @param bo 会话序列号
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ImConversationSeqBo bo) {
        ImConversationSeq update = MapstructUtils.convert(bo, ImConversationSeq.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ImConversationSeq entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除会话序列号信息
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
     * 获取并递增会话序列号
     *
     * @param conversationId 会话ID
     * @return 递增后的序列号
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long getAndIncrementSeq(Long conversationId) {
        // 获取当前序列号
        ImConversationSeq seq = baseMapper.selectById(conversationId);
        if (seq == null) {
            // 如果不存在，则初始化
            seq = new ImConversationSeq();
            seq.setConversationId(conversationId);
            seq.setConversationSeq(1L);
            baseMapper.insert(seq);
            return 1L;
        } else {
            // 递增序列号
            Long newSeq = seq.getConversationSeq() + 1;
            seq.setConversationSeq(newSeq);
            baseMapper.updateById(seq);
            return newSeq;
        }
    }
}
