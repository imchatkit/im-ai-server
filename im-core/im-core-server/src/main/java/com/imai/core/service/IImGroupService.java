package com.imai.core.service;

import com.imai.core.domain.bo.ImGroupBo;
import com.imai.core.domain.vo.ImGroupVo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 群组Service接口
 *
 * @author wei
 * @date 2025-01-07
 */
public interface IImGroupService {

    /**
     * 查询群组
     *
     * @param id 主键
     * @return 群组
     */
    ImGroupVo queryById(Long id);

    /**
     * 分页查询群组列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 群组分页列表
     */
    TableDataInfo<ImGroupVo> queryPageList(ImGroupBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的群组列表
     *
     * @param bo 查询条件
     * @return 群组列表
     */
    List<ImGroupVo> queryList(ImGroupBo bo);

    /**
     * 新增群组
     *
     * @param bo 群组
     * @return 是否新增成功
     */
    Boolean insertByBo(ImGroupBo bo);

    /**
     * 修改群组
     *
     * @param bo 群组
     * @return 是否修改成功
     */
    Boolean updateByBo(ImGroupBo bo);

    /**
     * 校验并批量删除群组信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 查询我加入的群组列表
     *
     * @param userId 用户ID
     * @return 群组列表
     */
    List<ImGroupVo> queryMyJoinedGroups(Long userId);

    /**
     * 查询我创建的群组列表
     *
     * @param userId 用户ID
     * @return 群组列表
     */
    List<ImGroupVo> queryMyCreatedGroups(Long userId);
}
