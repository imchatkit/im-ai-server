package com.imai.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imai.core.domain.ImMsgRecall;
import com.imai.core.domain.bo.ImMsgRecallBo;
import com.imai.core.domain.vo.ImMsgRecallVo;
import com.imai.core.mapper.ImMsgRecallMapper;
import com.imai.core.service.IImMsgRecallService;
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
 * 消息撤回记录Service业务层处理
 *
 * @author wei
 * @date 2025-01-07
 */
@RequiredArgsConstructor
@Service
public class ImMsgRecallServiceImpl implements IImMsgRecallService {

    private final ImMsgRecallMapper baseMapper;

    /**
     * 查询消息撤回记录
     *
     * @param id 主键
     * @return 消息撤回记录
     */
    @Override
    public ImMsgRecallVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询消息撤回记录列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 消息撤回记录分页列表
     */
    @Override
    public TableDataInfo<ImMsgRecallVo> queryPageList(ImMsgRecallBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ImMsgRecall> lqw = buildQueryWrapper(bo);
        Page<ImMsgRecallVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的消息撤回记录列表
     *
     * @param bo 查询条件
     * @return 消息撤回记录列表
     */
    @Override
    public List<ImMsgRecallVo> queryList(ImMsgRecallBo bo) {
        LambdaQueryWrapper<ImMsgRecall> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ImMsgRecall> buildQueryWrapper(ImMsgRecallBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ImMsgRecall> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFkMsgId() != null, ImMsgRecall::getFkMsgId, bo.getFkMsgId());
        lqw.eq(bo.getFkUserId() != null, ImMsgRecall::getFkUserId, bo.getFkUserId());
        lqw.eq(bo.getRecallTime() != null, ImMsgRecall::getRecallTime, bo.getRecallTime());
        lqw.eq(bo.getDeleted() != null, ImMsgRecall::getDeleted, bo.getDeleted());
        lqw.eq(StringUtils.isNotBlank(bo.getExtras()), ImMsgRecall::getExtras, bo.getExtras());
        return lqw;
    }

    /**
     * 新增消息撤回记录
     *
     * @param bo 消息撤回记录
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ImMsgRecallBo bo) {
        ImMsgRecall add = MapstructUtils.convert(bo, ImMsgRecall.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改消息撤回记录
     *
     * @param bo 消息撤回记录
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ImMsgRecallBo bo) {
        ImMsgRecall update = MapstructUtils.convert(bo, ImMsgRecall.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ImMsgRecall entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除消息撤回记录信息
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
