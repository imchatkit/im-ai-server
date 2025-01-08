package com.imai.core.service;

import com.imai.core.domain.bo.ImFriendRequestBo;
import com.imai.core.domain.vo.ImFriendRequestVo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 好友申请Service接口
 *
 * @author wei
 * @date 2025-01-07
 */
public interface IImFriendRequestService {

    /**
     * 查询好友申请
     *
     * @param id 主键
     * @return 好友申请
     */
    ImFriendRequestVo queryById(Long id);

    /**
     * 分页查询好友申请列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 好友申请分页列表
     */
    TableDataInfo<ImFriendRequestVo> queryPageList(ImFriendRequestBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的好友申请列表
     *
     * @param bo 查询条件
     * @return 好友申请列表
     */
    List<ImFriendRequestVo> queryList(ImFriendRequestBo bo);

    /**
     * 新增好友申请
     *
     * @param bo 好友申请
     * @return 是否新增成功
     */
    Boolean insertByBo(ImFriendRequestBo bo);

    /**
     * 修改好友申请
     *
     * @param bo 好友申请
     * @return 是否修改成功
     */
    Boolean updateByBo(ImFriendRequestBo bo);

    /**
     * 校验并批量删除好友申请信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
