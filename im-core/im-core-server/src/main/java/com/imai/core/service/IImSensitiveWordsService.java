package com.imai.core.service;

import com.imai.core.domain.bo.ImSensitiveWordsBo;
import com.imai.core.domain.vo.ImSensitiveWordsVo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 敏感词过滤Service接口
 *
 * @author wei
 * @date 2025-01-07
 */
public interface IImSensitiveWordsService {

    /**
     * 查询敏感词过滤
     *
     * @param id 主键
     * @return 敏感词过滤
     */
    ImSensitiveWordsVo queryById(Long id);

    /**
     * 分页查询敏感词过滤列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 敏感词过滤分页列表
     */
    TableDataInfo<ImSensitiveWordsVo> queryPageList(ImSensitiveWordsBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的敏感词过滤列表
     *
     * @param bo 查询条件
     * @return 敏感词过滤列表
     */
    List<ImSensitiveWordsVo> queryList(ImSensitiveWordsBo bo);

    /**
     * 新增敏感词过滤
     *
     * @param bo 敏感词过滤
     * @return 是否新增成功
     */
    Boolean insertByBo(ImSensitiveWordsBo bo);

    /**
     * 修改敏感词过滤
     *
     * @param bo 敏感词过滤
     * @return 是否修改成功
     */
    Boolean updateByBo(ImSensitiveWordsBo bo);

    /**
     * 校验并批量删除敏感词过滤信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
