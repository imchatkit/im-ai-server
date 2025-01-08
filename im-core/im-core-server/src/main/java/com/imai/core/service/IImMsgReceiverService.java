package com.imai.core.service;

import com.imai.core.domain.bo.ImMsgReceiverBo;
import com.imai.core.domain.vo.ImMsgReceiverVo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 消息接收Service接口
 *
 * @author wei
 * @date 2025-01-07
 */
public interface IImMsgReceiverService {

    /**
     * 查询消息接收
     *
     * @param id 主键
     * @return 消息接收
     */
    ImMsgReceiverVo queryById(Long id);

    /**
     * 分页查询消息接收列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 消息接收分页列表
     */
    TableDataInfo<ImMsgReceiverVo> queryPageList(ImMsgReceiverBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的消息接收列表
     *
     * @param bo 查询条件
     * @return 消息接收列表
     */
    List<ImMsgReceiverVo> queryList(ImMsgReceiverBo bo);

    /**
     * 新增消息接收
     *
     * @param bo 消息接收
     * @return 是否新增成功
     */
    Boolean insertByBo(ImMsgReceiverBo bo);

    /**
     * 修改消息接收
     *
     * @param bo 消息接收
     * @return 是否修改成功
     */
    Boolean updateByBo(ImMsgReceiverBo bo);

    /**
     * 校验并批量删除消息接收信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
