package com.imai.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imai.core.domain.ImSysConversationInit;
import com.imai.core.domain.bo.ImSysConversationInitBo;
import com.imai.core.domain.vo.ImSysConversationInitVo;
import com.imai.core.mapper.ImSysConversationInitMapper;
import com.imai.core.service.IImSysConversationInitService;
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
 * 系统会话初始化Service业务层处理
 *
 * @author wei
 * @date 2025-01-07
 */
@RequiredArgsConstructor
@Service
public class ImSysConversationInitServiceImpl implements IImSysConversationInitService {

    private final ImSysConversationInitMapper baseMapper;

    /**
     * 查询系统会话初始化
     *
     * @param id 主键
     * @return 系统会话初始化
     */
    @Override
    public ImSysConversationInitVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询系统会话初始化列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 系统会话初始化分页列表
     */
    @Override
    public TableDataInfo<ImSysConversationInitVo> queryPageList(ImSysConversationInitBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ImSysConversationInit> lqw = buildQueryWrapper(bo);
        Page<ImSysConversationInitVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的系统会话初始化列表
     *
     * @param bo 查询条件
     * @return 系统会话初始化列表
     */
    @Override
    public List<ImSysConversationInitVo> queryList(ImSysConversationInitBo bo) {
        LambdaQueryWrapper<ImSysConversationInit> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ImSysConversationInit> buildQueryWrapper(ImSysConversationInitBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ImSysConversationInit> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getConversationType() != null, ImSysConversationInit::getConversationType, bo.getConversationType());
        lqw.like(StringUtils.isNotBlank(bo.getConversationName()), ImSysConversationInit::getConversationName, bo.getConversationName());
        lqw.eq(StringUtils.isNotBlank(bo.getExtras()), ImSysConversationInit::getExtras, bo.getExtras());
        lqw.eq(StringUtils.isNotBlank(bo.getAvatar()), ImSysConversationInit::getAvatar, bo.getAvatar());
        lqw.eq(bo.getDeleted() != null, ImSysConversationInit::getDeleted, bo.getDeleted());
        return lqw;
    }

    /**
     * 新增系统会话初始化
     *
     * @param bo 系统会话初始化
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ImSysConversationInitBo bo) {
        ImSysConversationInit add = MapstructUtils.convert(bo, ImSysConversationInit.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改系统会话初始化
     *
     * @param bo 系统会话初始化
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ImSysConversationInitBo bo) {
        ImSysConversationInit update = MapstructUtils.convert(bo, ImSysConversationInit.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ImSysConversationInit entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除系统会话初始化信息
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
