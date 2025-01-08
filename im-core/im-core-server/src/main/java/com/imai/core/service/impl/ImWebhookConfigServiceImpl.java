package com.imai.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imai.core.domain.ImWebhookConfig;
import com.imai.core.domain.bo.ImWebhookConfigBo;
import com.imai.core.domain.vo.ImWebhookConfigVo;
import com.imai.core.mapper.ImWebhookConfigMapper;
import com.imai.core.service.IImWebhookConfigService;
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
 * Webhook配置Service业务层处理
 *
 * @author wei
 * @date 2025-01-07
 */
@RequiredArgsConstructor
@Service
public class ImWebhookConfigServiceImpl implements IImWebhookConfigService {

    private final ImWebhookConfigMapper baseMapper;

    /**
     * 查询Webhook配置
     *
     * @param id 主键
     * @return Webhook配置
     */
    @Override
    public ImWebhookConfigVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询Webhook配置列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return Webhook配置分页列表
     */
    @Override
    public TableDataInfo<ImWebhookConfigVo> queryPageList(ImWebhookConfigBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ImWebhookConfig> lqw = buildQueryWrapper(bo);
        Page<ImWebhookConfigVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的Webhook配置列表
     *
     * @param bo 查询条件
     * @return Webhook配置列表
     */
    @Override
    public List<ImWebhookConfigVo> queryList(ImWebhookConfigBo bo) {
        LambdaQueryWrapper<ImWebhookConfig> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ImWebhookConfig> buildQueryWrapper(ImWebhookConfigBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ImWebhookConfig> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getWebhookUrl()), ImWebhookConfig::getWebhookUrl, bo.getWebhookUrl());
        lqw.eq(bo.getWebhookType() != null, ImWebhookConfig::getWebhookType, bo.getWebhookType());
        lqw.eq(StringUtils.isNotBlank(bo.getSecretKey()), ImWebhookConfig::getSecretKey, bo.getSecretKey());
        lqw.eq(bo.getWebhookStatus() != null, ImWebhookConfig::getWebhookStatus, bo.getWebhookStatus());
        lqw.eq(bo.getDeleted() != null, ImWebhookConfig::getDeleted, bo.getDeleted());
        lqw.eq(StringUtils.isNotBlank(bo.getExtras()), ImWebhookConfig::getExtras, bo.getExtras());
        return lqw;
    }

    /**
     * 新增Webhook配置
     *
     * @param bo Webhook配置
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ImWebhookConfigBo bo) {
        ImWebhookConfig add = MapstructUtils.convert(bo, ImWebhookConfig.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改Webhook配置
     *
     * @param bo Webhook配置
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ImWebhookConfigBo bo) {
        ImWebhookConfig update = MapstructUtils.convert(bo, ImWebhookConfig.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ImWebhookConfig entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除Webhook配置信息
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
