package com.imai.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imai.core.domain.ImMsgReferencePath;
import com.imai.core.domain.bo.ImMsgReferencePathBo;
import com.imai.core.domain.vo.ImMsgReferencePathVo;
import com.imai.core.mapper.ImMsgReferencePathMapper;
import com.imai.core.service.IImMsgReferencePathService;
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
 * 消息引用路径Service业务层处理
 *
 * @author wei
 * @date 2025-01-07
 */
@RequiredArgsConstructor
@Service
public class ImMsgReferencePathServiceImpl implements IImMsgReferencePathService {

    private final ImMsgReferencePathMapper baseMapper;

    /**
     * 查询消息引用路径
     *
     * @param id 主键
     * @return 消息引用路径
     */
    @Override
    public ImMsgReferencePathVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询消息引用路径列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 消息引用路径分页列表
     */
    @Override
    public TableDataInfo<ImMsgReferencePathVo> queryPageList(ImMsgReferencePathBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ImMsgReferencePath> lqw = buildQueryWrapper(bo);
        Page<ImMsgReferencePathVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的消息引用路径列表
     *
     * @param bo 查询条件
     * @return 消息引用路径列表
     */
    @Override
    public List<ImMsgReferencePathVo> queryList(ImMsgReferencePathBo bo) {
        LambdaQueryWrapper<ImMsgReferencePath> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ImMsgReferencePath> buildQueryWrapper(ImMsgReferencePathBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ImMsgReferencePath> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFkMsgId() != null, ImMsgReferencePath::getFkMsgId, bo.getFkMsgId());
        lqw.eq(bo.getAncestorMsgId() != null, ImMsgReferencePath::getAncestorMsgId, bo.getAncestorMsgId());
        lqw.eq(bo.getDistance() != null, ImMsgReferencePath::getDistance, bo.getDistance());
        lqw.eq(StringUtils.isNotBlank(bo.getPath()), ImMsgReferencePath::getPath, bo.getPath());
        lqw.eq(bo.getDeleted() != null, ImMsgReferencePath::getDeleted, bo.getDeleted());
        lqw.eq(StringUtils.isNotBlank(bo.getExtras()), ImMsgReferencePath::getExtras, bo.getExtras());
        return lqw;
    }

    /**
     * 新增消息引用路径
     *
     * @param bo 消息引用路径
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ImMsgReferencePathBo bo) {
        ImMsgReferencePath add = MapstructUtils.convert(bo, ImMsgReferencePath.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改消息引用路径
     *
     * @param bo 消息引用路径
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ImMsgReferencePathBo bo) {
        ImMsgReferencePath update = MapstructUtils.convert(bo, ImMsgReferencePath.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ImMsgReferencePath entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除消息引用路径信息
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
