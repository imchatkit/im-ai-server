package com.imai.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imai.core.domain.ImCallbackRecord;
import com.imai.core.domain.bo.ImCallbackRecordBo;
import com.imai.core.domain.vo.ImCallbackRecordVo;
import com.imai.core.mapper.ImCallbackRecordMapper;
import com.imai.core.service.IImCallbackRecordService;
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
 * 消息回调记录Service业务层处理
 *
 * @author wei
 * @date 2025-01-07
 */
@RequiredArgsConstructor
@Service
public class ImCallbackRecordServiceImpl implements IImCallbackRecordService {

    private final ImCallbackRecordMapper baseMapper;

    /**
     * 查询消息回调记录
     *
     * @param id 主键
     * @return 消息回调记录
     */
    @Override
    public ImCallbackRecordVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询消息回调记录列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 消息回调记录分页列表
     */
    @Override
    public TableDataInfo<ImCallbackRecordVo> queryPageList(ImCallbackRecordBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ImCallbackRecord> lqw = buildQueryWrapper(bo);
        Page<ImCallbackRecordVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的消息回调记录列表
     *
     * @param bo 查询条件
     * @return 消息回调记录列表
     */
    @Override
    public List<ImCallbackRecordVo> queryList(ImCallbackRecordBo bo) {
        LambdaQueryWrapper<ImCallbackRecord> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ImCallbackRecord> buildQueryWrapper(ImCallbackRecordBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ImCallbackRecord> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getCallbackType() != null, ImCallbackRecord::getCallbackType, bo.getCallbackType());
        lqw.eq(StringUtils.isNotBlank(bo.getCallbackUrl()), ImCallbackRecord::getCallbackUrl, bo.getCallbackUrl());
        lqw.eq(StringUtils.isNotBlank(bo.getRequestBody()), ImCallbackRecord::getRequestBody, bo.getRequestBody());
        lqw.eq(StringUtils.isNotBlank(bo.getResponseBody()), ImCallbackRecord::getResponseBody, bo.getResponseBody());
        lqw.eq(bo.getCallbackStatus() != null, ImCallbackRecord::getCallbackStatus, bo.getCallbackStatus());
        lqw.eq(bo.getRetryCount() != null, ImCallbackRecord::getRetryCount, bo.getRetryCount());
        lqw.eq(bo.getDeleted() != null, ImCallbackRecord::getDeleted, bo.getDeleted());
        lqw.eq(StringUtils.isNotBlank(bo.getExtras()), ImCallbackRecord::getExtras, bo.getExtras());
        return lqw;
    }

    /**
     * 新增消息回调记录
     *
     * @param bo 消息回调记录
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ImCallbackRecordBo bo) {
        ImCallbackRecord add = MapstructUtils.convert(bo, ImCallbackRecord.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改消息回调记录
     *
     * @param bo 消息回调记录
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ImCallbackRecordBo bo) {
        ImCallbackRecord update = MapstructUtils.convert(bo, ImCallbackRecord.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ImCallbackRecord entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除消息回调记录信息
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
