package com.imai.core.service;

import com.imai.core.domain.bo.ImCallbackRecordBo;
import com.imai.core.domain.vo.ImCallbackRecordVo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 消息回调记录Service接口
 *
 * @author wei
 * @date 2025-01-07
 */
public interface IImCallbackRecordService {

    /**
     * 查询消息回调记录
     *
     * @param id 主键
     * @return 消息回调记录
     */
    ImCallbackRecordVo queryById(Long id);

    /**
     * 分页查询消息回调记录列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 消息回调记录分页列表
     */
    TableDataInfo<ImCallbackRecordVo> queryPageList(ImCallbackRecordBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的消息回调记录列表
     *
     * @param bo 查询条件
     * @return 消息回调记录列表
     */
    List<ImCallbackRecordVo> queryList(ImCallbackRecordBo bo);

    /**
     * 新增消息回调记录
     *
     * @param bo 消息回调记录
     * @return 是否新增成功
     */
    Boolean insertByBo(ImCallbackRecordBo bo);

    /**
     * 修改消息回调记录
     *
     * @param bo 消息回调记录
     * @return 是否修改成功
     */
    Boolean updateByBo(ImCallbackRecordBo bo);

    /**
     * 校验并批量删除消息回调记录信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
