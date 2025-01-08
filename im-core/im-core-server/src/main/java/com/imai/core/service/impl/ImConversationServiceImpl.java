package com.imai.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imai.core.domain.ImConversation;
import com.imai.core.domain.bo.ImConversationBo;
import com.imai.core.domain.vo.ImConversationVo;
import com.imai.core.mapper.ImConversationMapper;
import com.imai.core.service.IImConversationService;
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
 * 聊天会话基础Service业务层处理
 *
 * @author wei
 * @date 2025-01-07
 */
@RequiredArgsConstructor
@Service
public class ImConversationServiceImpl implements IImConversationService {

    private final ImConversationMapper baseMapper;

    /**
     * 查询聊天会话基础
     *
     * @param id 主键
     * @return 聊天会话基础
     */
    @Override
    public ImConversationVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询聊天会话基础列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 聊天会话基础分页列表
     */
    @Override
    public TableDataInfo<ImConversationVo> queryPageList(ImConversationBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ImConversation> lqw = buildQueryWrapper(bo);
        Page<ImConversationVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的聊天会话基础列表
     *
     * @param bo 查询条件
     * @return 聊天会话基础列表
     */
    @Override
    public List<ImConversationVo> queryList(ImConversationBo bo) {
        LambdaQueryWrapper<ImConversation> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ImConversation> buildQueryWrapper(ImConversationBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ImConversation> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getAvatar()), ImConversation::getAvatar, bo.getAvatar());
        lqw.eq(bo.getConversationType() != null, ImConversation::getConversationType, bo.getConversationType());
        lqw.eq(bo.getConversationStatus() != null, ImConversation::getConversationStatus, bo.getConversationStatus());
        lqw.eq(bo.getDeleted() != null, ImConversation::getDeleted, bo.getDeleted());
        lqw.eq(StringUtils.isNotBlank(bo.getExtras()), ImConversation::getExtras, bo.getExtras());
        return lqw;
    }

    /**
     * 新增聊天会话基础
     *
     * @param bo 聊天会话基础
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ImConversationBo bo) {
        ImConversation add = MapstructUtils.convert(bo, ImConversation.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改聊天会话基础
     *
     * @param bo 聊天会话基础
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ImConversationBo bo) {
        ImConversation update = MapstructUtils.convert(bo, ImConversation.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ImConversation entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除聊天会话基础信息
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
