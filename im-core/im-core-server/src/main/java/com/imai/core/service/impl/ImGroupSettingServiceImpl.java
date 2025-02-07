package com.imai.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imai.core.domain.ImGroupSetting;
import com.imai.core.domain.bo.ImGroupSettingBo;
import com.imai.core.domain.vo.ImGroupSettingVo;
import com.imai.core.mapper.ImGroupSettingMapper;
import com.imai.core.service.IImGroupSettingService;
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
 * 群组设置Service业务层处理
 *
 * @author wei
 * @date 2025-01-07
 */
@RequiredArgsConstructor
@Service
public class ImGroupSettingServiceImpl implements IImGroupSettingService {

    private final ImGroupSettingMapper baseMapper;

    /**
     * 查询群组设置
     *
     * @param fkGroupId 主键
     * @return 群组设置
     */
    @Override
    public ImGroupSettingVo queryById(Long fkGroupId) {
        return baseMapper.selectVoById(fkGroupId);
    }

    /**
     * 分页查询群组设置列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 群组设置分页列表
     */
    @Override
    public TableDataInfo<ImGroupSettingVo> queryPageList(ImGroupSettingBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ImGroupSetting> lqw = buildQueryWrapper(bo);
        Page<ImGroupSettingVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的群组设置列表
     *
     * @param bo 查询条件
     * @return 群组设置列表
     */
    @Override
    public List<ImGroupSettingVo> queryList(ImGroupSettingBo bo) {
        LambdaQueryWrapper<ImGroupSetting> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ImGroupSetting> buildQueryWrapper(ImGroupSettingBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ImGroupSetting> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getAllMute() != null, ImGroupSetting::getAllMute, bo.getAllMute());
        lqw.eq(bo.getMemberInvite() != null, ImGroupSetting::getMemberInvite, bo.getMemberInvite());
        lqw.eq(bo.getMemberModify() != null, ImGroupSetting::getMemberModify, bo.getMemberModify());
        lqw.eq(bo.getMemberVisible() != null, ImGroupSetting::getMemberVisible, bo.getMemberVisible());
        lqw.eq(bo.getForbidAddFriend() != null, ImGroupSetting::getForbidAddFriend, bo.getForbidAddFriend());
        lqw.eq(bo.getForbidSendRedpacket() != null, ImGroupSetting::getForbidSendRedpacket, bo.getForbidSendRedpacket());
        lqw.eq(bo.getForbidSendImage() != null, ImGroupSetting::getForbidSendImage, bo.getForbidSendImage());
        lqw.eq(bo.getForbidSendLink() != null, ImGroupSetting::getForbidSendLink, bo.getForbidSendLink());
        lqw.eq(bo.getGroupDisbanded() != null, ImGroupSetting::getGroupDisbanded, bo.getGroupDisbanded());
        lqw.eq(StringUtils.isNotBlank(bo.getExtras()), ImGroupSetting::getExtras, bo.getExtras());
        return lqw;
    }

    /**
     * 新增群组设置
     *
     * @param bo 群组设置
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ImGroupSettingBo bo) {
        ImGroupSetting add = MapstructUtils.convert(bo, ImGroupSetting.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setFkGroupId(add.getFkGroupId());
        }
        return flag;
    }

    /**
     * 修改群组设置
     *
     * @param bo 群组设置
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ImGroupSettingBo bo) {
        ImGroupSetting update = MapstructUtils.convert(bo, ImGroupSetting.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ImGroupSetting entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除群组设置信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteByIds(ids) > 0;
    }
}
