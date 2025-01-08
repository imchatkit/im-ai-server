package com.imai.core.service;

import com.imai.core.domain.bo.ImGroupSettingBo;
import com.imai.core.domain.vo.ImGroupSettingVo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 群组设置Service接口
 *
 * @author wei
 * @date 2025-01-07
 */
public interface IImGroupSettingService {

    /**
     * 查询群组设置
     *
     * @param fkGroupId 主键
     * @return 群组设置
     */
    ImGroupSettingVo queryById(Long fkGroupId);

    /**
     * 分页查询群组设置列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 群组设置分页列表
     */
    TableDataInfo<ImGroupSettingVo> queryPageList(ImGroupSettingBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的群组设置列表
     *
     * @param bo 查询条件
     * @return 群组设置列表
     */
    List<ImGroupSettingVo> queryList(ImGroupSettingBo bo);

    /**
     * 新增群组设置
     *
     * @param bo 群组设置
     * @return 是否新增成功
     */
    Boolean insertByBo(ImGroupSettingBo bo);

    /**
     * 修改群组设置
     *
     * @param bo 群组设置
     * @return 是否修改成功
     */
    Boolean updateByBo(ImGroupSettingBo bo);

    /**
     * 校验并批量删除群组设置信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
