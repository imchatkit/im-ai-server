package com.imai.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imai.core.domain.ImWorkspace;
import com.imai.core.domain.bo.ImWorkspaceBo;
import com.imai.core.domain.vo.ImWorkspaceVo;
import com.imai.core.mapper.ImWorkspaceMapper;
import com.imai.core.service.IImWorkspaceService;
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
 * 工作空间Service业务层处理
 *
 * @author wei
 * @date 2025-01-07
 */
@RequiredArgsConstructor
@Service
public class ImWorkspaceServiceImpl implements IImWorkspaceService {

    private final ImWorkspaceMapper baseMapper;

    /**
     * 查询工作空间
     *
     * @param id 主键
     * @return 工作空间
     */
    @Override
    public ImWorkspaceVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询工作空间列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 工作空间分页列表
     */
    @Override
    public TableDataInfo<ImWorkspaceVo> queryPageList(ImWorkspaceBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ImWorkspace> lqw = buildQueryWrapper(bo);
        Page<ImWorkspaceVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的工作空间列表
     *
     * @param bo 查询条件
     * @return 工作空间列表
     */
    @Override
    public List<ImWorkspaceVo> queryList(ImWorkspaceBo bo) {
        LambdaQueryWrapper<ImWorkspace> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ImWorkspace> buildQueryWrapper(ImWorkspaceBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ImWorkspace> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getWorkspaceName()), ImWorkspace::getWorkspaceName, bo.getWorkspaceName());
        lqw.eq(bo.getCreatorUserId() != null, ImWorkspace::getCreatorUserId, bo.getCreatorUserId());
        lqw.eq(StringUtils.isNotBlank(bo.getDescription()), ImWorkspace::getDescription, bo.getDescription());
        lqw.eq(StringUtils.isNotBlank(bo.getDomain()), ImWorkspace::getDomain, bo.getDomain());
        lqw.eq(StringUtils.isNotBlank(bo.getLogoUrl()), ImWorkspace::getLogoUrl, bo.getLogoUrl());
        lqw.eq(bo.getWorkspaceStatus() != null, ImWorkspace::getWorkspaceStatus, bo.getWorkspaceStatus());
        lqw.eq(bo.getDeleted() != null, ImWorkspace::getDeleted, bo.getDeleted());
        lqw.eq(StringUtils.isNotBlank(bo.getExtras()), ImWorkspace::getExtras, bo.getExtras());
        return lqw;
    }

    /**
     * 新增工作空间
     *
     * @param bo 工作空间
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ImWorkspaceBo bo) {
        ImWorkspace add = MapstructUtils.convert(bo, ImWorkspace.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改工作空间
     *
     * @param bo 工作空间
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ImWorkspaceBo bo) {
        ImWorkspace update = MapstructUtils.convert(bo, ImWorkspace.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ImWorkspace entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除工作空间信息
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
