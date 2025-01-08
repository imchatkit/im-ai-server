package com.imai.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imai.core.domain.ImMsgReference;
import com.imai.core.domain.bo.ImMsgReferenceBo;
import com.imai.core.domain.vo.ImMsgReferenceVo;
import com.imai.core.mapper.ImMsgReferenceMapper;
import com.imai.core.service.IImMsgReferenceService;
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
 * 消息引用关系Service业务层处理
 *
 * @author wei
 * @date 2025-01-07
 */
@RequiredArgsConstructor
@Service
public class ImMsgReferenceServiceImpl implements IImMsgReferenceService {

    private final ImMsgReferenceMapper baseMapper;

    /**
     * 查询消息引用关系
     *
     * @param id 主键
     * @return 消息引用关系
     */
    @Override
    public ImMsgReferenceVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询消息引用关系列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 消息引用关系分页列表
     */
    @Override
    public TableDataInfo<ImMsgReferenceVo> queryPageList(ImMsgReferenceBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ImMsgReference> lqw = buildQueryWrapper(bo);
        Page<ImMsgReferenceVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的消息引用关系列表
     *
     * @param bo 查询条件
     * @return 消息引用关系列表
     */
    @Override
    public List<ImMsgReferenceVo> queryList(ImMsgReferenceBo bo) {
        LambdaQueryWrapper<ImMsgReference> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ImMsgReference> buildQueryWrapper(ImMsgReferenceBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ImMsgReference> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFkMsgId() != null, ImMsgReference::getFkMsgId, bo.getFkMsgId());
        lqw.eq(bo.getFkRefMsgId() != null, ImMsgReference::getFkRefMsgId, bo.getFkRefMsgId());
        lqw.eq(bo.getRefType() != null, ImMsgReference::getRefType, bo.getRefType());
        lqw.eq(StringUtils.isNotBlank(bo.getRefText()), ImMsgReference::getRefText, bo.getRefText());
        lqw.eq(bo.getDeleted() != null, ImMsgReference::getDeleted, bo.getDeleted());
        lqw.eq(StringUtils.isNotBlank(bo.getExtras()), ImMsgReference::getExtras, bo.getExtras());
        return lqw;
    }

    /**
     * 新增消息引用关系
     *
     * @param bo 消息引用关系
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ImMsgReferenceBo bo) {
        ImMsgReference add = MapstructUtils.convert(bo, ImMsgReference.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改消息引用关系
     *
     * @param bo 消息引用关系
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ImMsgReferenceBo bo) {
        ImMsgReference update = MapstructUtils.convert(bo, ImMsgReference.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ImMsgReference entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除消息引用关系信息
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
