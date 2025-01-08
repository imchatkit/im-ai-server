package com.imai.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imai.core.domain.ImGroupAnnouncement;
import com.imai.core.domain.bo.ImGroupAnnouncementBo;
import com.imai.core.domain.vo.ImGroupAnnouncementVo;
import com.imai.core.mapper.ImGroupAnnouncementMapper;
import com.imai.core.service.IImGroupAnnouncementService;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 群公告Service业务层处理
 *
 * @author wei
 * @date 2025-01-07
 */
@RequiredArgsConstructor
@Service
public class ImGroupAnnouncementServiceImpl implements IImGroupAnnouncementService {

    private final ImGroupAnnouncementMapper baseMapper;

    /**
     * 查询群公告
     *
     * @param id 主键
     * @return 群公告
     */
    @Override
    public ImGroupAnnouncementVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询群公告列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 群公告分页列表
     */
    @Override
    public TableDataInfo<ImGroupAnnouncementVo> queryPageList(ImGroupAnnouncementBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ImGroupAnnouncement> lqw = buildQueryWrapper(bo);
        Page<ImGroupAnnouncementVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的群公告列表
     *
     * @param bo 查询条件
     * @return 群公告列表
     */
    @Override
    public List<ImGroupAnnouncementVo> queryList(ImGroupAnnouncementBo bo) {
        LambdaQueryWrapper<ImGroupAnnouncement> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ImGroupAnnouncement> buildQueryWrapper(ImGroupAnnouncementBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ImGroupAnnouncement> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFkGroupId() != null, ImGroupAnnouncement::getFkGroupId, bo.getFkGroupId());
        lqw.eq(StringUtils.isNotBlank(bo.getContent()), ImGroupAnnouncement::getContent, bo.getContent());
        lqw.eq(bo.getDeleted() != null, ImGroupAnnouncement::getDeleted, bo.getDeleted());
        lqw.eq(StringUtils.isNotBlank(bo.getExtras()), ImGroupAnnouncement::getExtras, bo.getExtras());
        return lqw;
    }

    /**
     * 新增群公告
     *
     * @param bo 群公告
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ImGroupAnnouncementBo bo) {
        ImGroupAnnouncement add = MapstructUtils.convert(bo, ImGroupAnnouncement.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改群公告
     *
     * @param bo 群公告
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ImGroupAnnouncementBo bo) {
        ImGroupAnnouncement update = MapstructUtils.convert(bo, ImGroupAnnouncement.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ImGroupAnnouncement entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除群公告信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteByIds(ids) > 0;
    }
}
