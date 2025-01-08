package com.imai.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imai.core.domain.ImMsgRead;
import com.imai.core.domain.bo.ImMsgReadBo;
import com.imai.core.domain.vo.ImMsgReadVo;
import com.imai.core.mapper.ImMsgReadMapper;
import com.imai.core.service.IImMsgReadService;
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
 * 消息已读记录Service业务层处理
 *
 * @author wei
 * @date 2025-01-07
 */
@RequiredArgsConstructor
@Service
public class ImMsgReadServiceImpl implements IImMsgReadService {

    private final ImMsgReadMapper baseMapper;

    /**
     * 查询消息已读记录
     *
     * @param id 主键
     * @return 消息已读记录
     */
    @Override
    public ImMsgReadVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询消息已读记录列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 消息已读记录分页列表
     */
    @Override
    public TableDataInfo<ImMsgReadVo> queryPageList(ImMsgReadBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ImMsgRead> lqw = buildQueryWrapper(bo);
        Page<ImMsgReadVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的消息已读记录列表
     *
     * @param bo 查询条件
     * @return 消息已读记录列表
     */
    @Override
    public List<ImMsgReadVo> queryList(ImMsgReadBo bo) {
        LambdaQueryWrapper<ImMsgRead> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ImMsgRead> buildQueryWrapper(ImMsgReadBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ImMsgRead> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFkMsgId() != null, ImMsgRead::getFkMsgId, bo.getFkMsgId());
        lqw.eq(bo.getFkConversationId() != null, ImMsgRead::getFkConversationId, bo.getFkConversationId());
        lqw.eq(bo.getFkReceiverUserId() != null, ImMsgRead::getFkReceiverUserId, bo.getFkReceiverUserId());
        lqw.eq(bo.getFkFromUserId() != null, ImMsgRead::getFkFromUserId, bo.getFkFromUserId());
        lqw.eq(bo.getReadTime() != null, ImMsgRead::getReadTime, bo.getReadTime());
        lqw.eq(bo.getReceiverTime() != null, ImMsgRead::getReceiverTime, bo.getReceiverTime());
        lqw.eq(bo.getReadMsgStatus() != null, ImMsgRead::getReadMsgStatus, bo.getReadMsgStatus());
        lqw.eq(bo.getReceiverMsgStatus() != null, ImMsgRead::getReceiverMsgStatus, bo.getReceiverMsgStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getExtras()), ImMsgRead::getExtras, bo.getExtras());
        return lqw;
    }

    /**
     * 新增消息已读记录
     *
     * @param bo 消息已读记录
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ImMsgReadBo bo) {
        ImMsgRead add = MapstructUtils.convert(bo, ImMsgRead.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改消息已读记录
     *
     * @param bo 消息已读记录
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ImMsgReadBo bo) {
        ImMsgRead update = MapstructUtils.convert(bo, ImMsgRead.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ImMsgRead entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除消息已读记录信息
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
