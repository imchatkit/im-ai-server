package com.imai.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imai.core.domain.ImSensitiveWords;
import com.imai.core.domain.bo.ImSensitiveWordsBo;
import com.imai.core.domain.vo.ImSensitiveWordsVo;
import com.imai.core.mapper.ImSensitiveWordsMapper;
import com.imai.core.service.IImSensitiveWordsService;
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
 * 敏感词过滤Service业务层处理
 *
 * @author wei
 * @date 2025-01-07
 */
@RequiredArgsConstructor
@Service
public class ImSensitiveWordsServiceImpl implements IImSensitiveWordsService {

    private final ImSensitiveWordsMapper baseMapper;

    /**
     * 查询敏感词过滤
     *
     * @param id 主键
     * @return 敏感词过滤
     */
    @Override
    public ImSensitiveWordsVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询敏感词过滤列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 敏感词过滤分页列表
     */
    @Override
    public TableDataInfo<ImSensitiveWordsVo> queryPageList(ImSensitiveWordsBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ImSensitiveWords> lqw = buildQueryWrapper(bo);
        Page<ImSensitiveWordsVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的敏感词过滤列表
     *
     * @param bo 查询条件
     * @return 敏感词过滤列表
     */
    @Override
    public List<ImSensitiveWordsVo> queryList(ImSensitiveWordsBo bo) {
        LambdaQueryWrapper<ImSensitiveWords> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ImSensitiveWords> buildQueryWrapper(ImSensitiveWordsBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ImSensitiveWords> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getWord()), ImSensitiveWords::getWord, bo.getWord());
        lqw.eq(bo.getDeleted() != null, ImSensitiveWords::getDeleted, bo.getDeleted());
        lqw.eq(StringUtils.isNotBlank(bo.getExtras()), ImSensitiveWords::getExtras, bo.getExtras());
        return lqw;
    }

    /**
     * 新增敏感词过滤
     *
     * @param bo 敏感词过滤
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ImSensitiveWordsBo bo) {
        ImSensitiveWords add = MapstructUtils.convert(bo, ImSensitiveWords.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改敏感词过滤
     *
     * @param bo 敏感词过滤
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ImSensitiveWordsBo bo) {
        ImSensitiveWords update = MapstructUtils.convert(bo, ImSensitiveWords.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ImSensitiveWords entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除敏感词过滤信息
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
