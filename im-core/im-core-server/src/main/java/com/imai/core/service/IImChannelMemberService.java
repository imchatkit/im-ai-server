package com.imai.core.service;

import com.imai.core.domain.bo.ImChannelMemberBo;
import com.imai.core.domain.vo.ImChannelMemberVo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 频道成员Service接口
 *
 * @author wei
 * @date 2025-01-07
 */
public interface IImChannelMemberService {

    /**
     * 查询频道成员
     *
     * @param id 主键
     * @return 频道成员
     */
    ImChannelMemberVo queryById(Long id);

    /**
     * 分页查询频道成员列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 频道成员分页列表
     */
    TableDataInfo<ImChannelMemberVo> queryPageList(ImChannelMemberBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的频道成员列表
     *
     * @param bo 查询条件
     * @return 频道成员列表
     */
    List<ImChannelMemberVo> queryList(ImChannelMemberBo bo);

    /**
     * 新增频道成员
     *
     * @param bo 频道成员
     * @return 是否新增成功
     */
    Boolean insertByBo(ImChannelMemberBo bo);

    /**
     * 修改频道成员
     *
     * @param bo 频道成员
     * @return 是否修改成功
     */
    Boolean updateByBo(ImChannelMemberBo bo);

    /**
     * 校验并批量删除频道成员信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
