package com.imai.core.service;

import com.imai.core.domain.bo.ImMsgReferencePathBo;
import com.imai.core.domain.vo.ImMsgReferencePathVo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 消息引用路径Service接口
 *
 * @author wei
 * @date 2025-01-07
 */
public interface IImMsgReferencePathService {

    /**
     * 查询消息引用路径
     *
     * @param id 主键
     * @return 消息引用路径
     */
    ImMsgReferencePathVo queryById(Long id);

    /**
     * 分页查询消息引用路径列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 消息引用路径分页列表
     */
    TableDataInfo<ImMsgReferencePathVo> queryPageList(ImMsgReferencePathBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的消息引用路径列表
     *
     * @param bo 查询条件
     * @return 消息引用路径列表
     */
    List<ImMsgReferencePathVo> queryList(ImMsgReferencePathBo bo);

    /**
     * 新增消息引用路径
     *
     * @param bo 消息引用路径
     * @return 是否新增成功
     */
    Boolean insertByBo(ImMsgReferencePathBo bo);

    /**
     * 修改消息引用路径
     *
     * @param bo 消息引用路径
     * @return 是否修改成功
     */
    Boolean updateByBo(ImMsgReferencePathBo bo);

    /**
     * 校验并批量删除消息引用路径信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
