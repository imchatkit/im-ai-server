package com.imai.core.service;

import com.imai.core.domain.bo.ImFriendBo;
import com.imai.core.domain.vo.ImFriendVo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 好友关系Service接口
 *
 * @author wei
 * @date 2025-01-07
 */
public interface IImFriendService {

    /**
     * 查询好友关系
     *
     * @param id 主键
     * @return 好友关系
     */
    ImFriendVo queryById(Long id);

    /**
     * 分页查询好友关系列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 好友关系分页列表
     */
    TableDataInfo<ImFriendVo> queryPageList(ImFriendBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的好友关系列表
     *
     * @param bo 查询条件
     * @return 好友关系列表
     */
    List<ImFriendVo> queryList(ImFriendBo bo);

    /**
     * 新增好友关系
     *
     * @param bo 好友关系
     * @return 是否新增成功
     */
    Boolean insertByBo(ImFriendBo bo);

    /**
     * 修改好友关系
     *
     * @param bo 好友关系
     * @return 是否修改成功
     */
    Boolean updateByBo(ImFriendBo bo);

    /**
     * 校验并批量删除好友关系信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
