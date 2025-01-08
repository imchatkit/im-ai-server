package com.imai.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imai.core.domain.ImUserStatus;
import com.imai.core.domain.bo.ImUserStatusBo;
import com.imai.core.domain.vo.ImUserStatusVo;
import com.imai.core.mapper.ImUserStatusMapper;
import com.imai.core.service.IImUserStatusService;
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
 * 用户状态Service业务层处理
 *
 * @author wei
 * @date 2025-01-07
 */
@RequiredArgsConstructor
@Service
public class ImUserStatusServiceImpl implements IImUserStatusService {

    private final ImUserStatusMapper baseMapper;

    /**
     * 查询用户状态
     *
     * @param userId 主键
     * @return 用户状态
     */
    @Override
    public ImUserStatusVo queryById(Long userId){
        return baseMapper.selectVoById(userId);
    }

    /**
     * 分页查询用户状态列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 用户状态分页列表
     */
    @Override
    public TableDataInfo<ImUserStatusVo> queryPageList(ImUserStatusBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ImUserStatus> lqw = buildQueryWrapper(bo);
        Page<ImUserStatusVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的用户状态列表
     *
     * @param bo 查询条件
     * @return 用户状态列表
     */
    @Override
    public List<ImUserStatusVo> queryList(ImUserStatusBo bo) {
        LambdaQueryWrapper<ImUserStatus> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ImUserStatus> buildQueryWrapper(ImUserStatusBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ImUserStatus> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getOnlineStatus() != null, ImUserStatus::getOnlineStatus, bo.getOnlineStatus());
        lqw.eq(bo.getLastActiveTime() != null, ImUserStatus::getLastActiveTime, bo.getLastActiveTime());
        lqw.eq(StringUtils.isNotBlank(bo.getDeviceInfo()), ImUserStatus::getDeviceInfo, bo.getDeviceInfo());
        lqw.eq(bo.getDeleted() != null, ImUserStatus::getDeleted, bo.getDeleted());
        lqw.eq(StringUtils.isNotBlank(bo.getExtras()), ImUserStatus::getExtras, bo.getExtras());
        return lqw;
    }

    /**
     * 新增用户状态
     *
     * @param bo 用户状态
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ImUserStatusBo bo) {
        ImUserStatus add = MapstructUtils.convert(bo, ImUserStatus.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setUserId(add.getUserId());
        }
        return flag;
    }

    /**
     * 修改用户状态
     *
     * @param bo 用户状态
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ImUserStatusBo bo) {
        ImUserStatus update = MapstructUtils.convert(bo, ImUserStatus.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ImUserStatus entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除用户状态信息
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
