package com.imai.core.service;

import com.imai.core.domain.bo.ImChannelBo;
import com.imai.core.domain.vo.ImChannelVo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 频道Service接口
 *
 * @author wei
 * @date 2025-01-07
 */
public interface IImChannelService {

    /**
     * 查询频道
     *
     * @param id 主键
     * @return 频道
     */
    ImChannelVo queryById(Long id);

    /**
     * 分页查询频道列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 频道分页列表
     */
    TableDataInfo<ImChannelVo> queryPageList(ImChannelBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的频道列表
     *
     * @param bo 查询条件
     * @return 频道列表
     */
    List<ImChannelVo> queryList(ImChannelBo bo);

    /**
     * 新增频道
     *
     * @param bo 频道
     * @return 是否新增成功
     */
    Boolean insertByBo(ImChannelBo bo);

    /**
     * 修改频道
     *
     * @param bo 频道
     * @return 是否修改成功
     */
    Boolean updateByBo(ImChannelBo bo);

    /**
     * 校验并批量删除频道信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
