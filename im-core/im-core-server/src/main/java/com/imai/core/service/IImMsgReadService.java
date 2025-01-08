package com.imai.core.service;

import com.imai.core.domain.bo.ImMsgReadBo;
import com.imai.core.domain.vo.ImMsgReadVo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 消息已读记录Service接口
 *
 * @author wei
 * @date 2025-01-07
 */
public interface IImMsgReadService {

    /**
     * 查询消息已读记录
     *
     * @param id 主键
     * @return 消息已读记录
     */
    ImMsgReadVo queryById(Long id);

    /**
     * 分页查询消息已读记录列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 消息已读记录分页列表
     */
    TableDataInfo<ImMsgReadVo> queryPageList(ImMsgReadBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的消息已读记录列表
     *
     * @param bo 查询条件
     * @return 消息已读记录列表
     */
    List<ImMsgReadVo> queryList(ImMsgReadBo bo);

    /**
     * 新增消息已读记录
     *
     * @param bo 消息已读记录
     * @return 是否新增成功
     */
    Boolean insertByBo(ImMsgReadBo bo);

    /**
     * 修改消息已读记录
     *
     * @param bo 消息已读记录
     * @return 是否修改成功
     */
    Boolean updateByBo(ImMsgReadBo bo);

    /**
     * 校验并批量删除消息已读记录信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
