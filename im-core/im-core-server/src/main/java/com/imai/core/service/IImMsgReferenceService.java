package com.imai.core.service;

import com.imai.core.domain.bo.ImMsgReferenceBo;
import com.imai.core.domain.vo.ImMsgReferenceVo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 消息引用关系Service接口
 *
 * @author wei
 * @date 2025-01-07
 */
public interface IImMsgReferenceService {

    /**
     * 查询消息引用关系
     *
     * @param id 主键
     * @return 消息引用关系
     */
    ImMsgReferenceVo queryById(Long id);

    /**
     * 分页查询消息引用关系列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 消息引用关系分页列表
     */
    TableDataInfo<ImMsgReferenceVo> queryPageList(ImMsgReferenceBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的消息引用关系列表
     *
     * @param bo 查询条件
     * @return 消息引用关系列表
     */
    List<ImMsgReferenceVo> queryList(ImMsgReferenceBo bo);

    /**
     * 新增消息引用关系
     *
     * @param bo 消息引用关系
     * @return 是否新增成功
     */
    Boolean insertByBo(ImMsgReferenceBo bo);

    /**
     * 修改消息引用关系
     *
     * @param bo 消息引用关系
     * @return 是否修改成功
     */
    Boolean updateByBo(ImMsgReferenceBo bo);

    /**
     * 校验并批量删除消息引用关系信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
