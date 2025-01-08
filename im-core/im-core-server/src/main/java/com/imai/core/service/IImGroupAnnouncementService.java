package com.imai.core.service;

import com.imai.core.domain.bo.ImGroupAnnouncementBo;
import com.imai.core.domain.vo.ImGroupAnnouncementVo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 群公告Service接口
 *
 * @author wei
 * @date 2025-01-07
 */
public interface IImGroupAnnouncementService {

    /**
     * 查询群公告
     *
     * @param id 主键
     * @return 群公告
     */
    ImGroupAnnouncementVo queryById(Long id);

    /**
     * 分页查询群公告列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 群公告分页列表
     */
    TableDataInfo<ImGroupAnnouncementVo> queryPageList(ImGroupAnnouncementBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的群公告列表
     *
     * @param bo 查询条件
     * @return 群公告列表
     */
    List<ImGroupAnnouncementVo> queryList(ImGroupAnnouncementBo bo);

    /**
     * 新增群公告
     *
     * @param bo 群公告
     * @return 是否新增成功
     */
    Boolean insertByBo(ImGroupAnnouncementBo bo);

    /**
     * 修改群公告
     *
     * @param bo 群公告
     * @return 是否修改成功
     */
    Boolean updateByBo(ImGroupAnnouncementBo bo);

    /**
     * 校验并批量删除群公告信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
