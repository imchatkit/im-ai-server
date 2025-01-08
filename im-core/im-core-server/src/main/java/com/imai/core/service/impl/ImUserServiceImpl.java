package com.imai.core.service.impl;

import com.imai.core.domain.ImUser;
import com.imai.core.domain.bo.ImUserBo;
import com.imai.core.domain.vo.ImUserVo;
import com.imai.core.mapper.ImUserMapper;
import com.imai.core.service.IImUserService;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 用户Service业务层处理
 *
 * @author wei
 * @date 2025-01-07
 */
@RequiredArgsConstructor
@Service
public class ImUserServiceImpl implements IImUserService {

    private final ImUserMapper baseMapper;

    /**
     * 查询用户
     *
     * @param id 主键
     * @return 用户
     */
    @Override
    public ImUserVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询用户列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 用户分页列表
     */
    @Override
    public TableDataInfo<ImUserVo> queryPageList(ImUserBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ImUser> lqw = buildQueryWrapper(bo);
        Page<ImUserVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的用户列表
     *
     * @param bo 查询条件
     * @return 用户列表
     */
    @Override
    public List<ImUserVo> queryList(ImUserBo bo) {
        LambdaQueryWrapper<ImUser> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ImUser> buildQueryWrapper(ImUserBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ImUser> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getAddressCode() != null, ImUser::getAddressCode, bo.getAddressCode());
        lqw.eq(StringUtils.isNotBlank(bo.getPhone()), ImUser::getPhone, bo.getPhone());
        lqw.eq(StringUtils.isNotBlank(bo.getIdCardNo()), ImUser::getIdCardNo, bo.getIdCardNo());
        lqw.eq(StringUtils.isNotBlank(bo.getEmail()), ImUser::getEmail, bo.getEmail());
        lqw.eq(StringUtils.isNotBlank(bo.getPassword()), ImUser::getPassword, bo.getPassword());
        lqw.eq(bo.getSex() != null, ImUser::getSex, bo.getSex());
        lqw.eq(StringUtils.isNotBlank(bo.getAvatar()), ImUser::getAvatar, bo.getAvatar());
        lqw.like(StringUtils.isNotBlank(bo.getNickname()), ImUser::getNickname, bo.getNickname());
        lqw.eq(bo.getLastOfflineTime() != null, ImUser::getLastOfflineTime, bo.getLastOfflineTime());
        lqw.eq(StringUtils.isNotBlank(bo.getAttributes()), ImUser::getAttributes, bo.getAttributes());
        lqw.eq(bo.getAllValid() != null, ImUser::getAllValid, bo.getAllValid());
        lqw.eq(bo.getUserStatus() != null, ImUser::getUserStatus, bo.getUserStatus());
        lqw.eq(bo.getLastLoginTime() != null, ImUser::getLastLoginTime, bo.getLastLoginTime());
        lqw.eq(StringUtils.isNotBlank(bo.getLastLoginIp()), ImUser::getLastLoginIp, bo.getLastLoginIp());
        lqw.eq(bo.getDeleted() != null, ImUser::getDeleted, bo.getDeleted());
        lqw.eq(StringUtils.isNotBlank(bo.getExtras()), ImUser::getExtras, bo.getExtras());
        return lqw;
    }

    /**
     * 新增用户
     *
     * @param bo 用户
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ImUserBo bo) {
        ImUser add = MapstructUtils.convert(bo, ImUser.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改用户
     *
     * @param bo 用户
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ImUserBo bo) {
        ImUser update = MapstructUtils.convert(bo, ImUser.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ImUser entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除用户信息
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
