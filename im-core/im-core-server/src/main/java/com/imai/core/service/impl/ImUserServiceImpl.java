package com.imai.core.service.impl;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imai.core.domain.ImUser;
import com.imai.core.domain.bo.ImUserBo;
import com.imai.core.domain.vo.ImUserVo;
import com.imai.core.mapper.ImUserMapper;
import com.imai.core.openapi.bo.OpenApiImUseRegisterBo;
import com.imai.core.openapi.vo.OpenApiImUserVo;
import com.imai.core.service.IImUserService;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.enums.DeviceType;
import org.dromara.common.core.enums.UserType;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.satoken.utils.LoginHelper;
import org.dromara.system.api.model.LoginUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 用户Service业务层处理
 *
 * @author wei
 * @date 2025-01-07
 */
@RequiredArgsConstructor
@Service
public class ImUserServiceImpl implements IImUserService {

    private final ImUserMapper imUserMapper;

    /**
     * 查询用户
     *
     * @param id 主键
     * @return 用户
     */
    @Override
    public ImUserVo queryById(Long id) {
        return imUserMapper.selectVoById(id);
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
        Page<ImUserVo> result = imUserMapper.selectVoPage(pageQuery.build(), lqw);
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
        return imUserMapper.selectVoList(lqw);
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
        boolean flag = imUserMapper.insert(add) > 0;
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
        return imUserMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ImUser entity) {
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
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return imUserMapper.deleteByIds(ids) > 0;
    }

    /**
     * 用户注册实现
     *
     * @param bo 注册信息
     * @return 注册用户信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public OpenApiImUserVo login(OpenApiImUseRegisterBo bo) {
        // 将注册BO转换为用户BO
        ImUserBo userBo = MapstructUtils.convert(bo, ImUserBo.class);

        // 检查用户是否已注册
        LambdaQueryWrapper<ImUser> lqw = Wrappers.lambdaQuery();
        lqw.eq(ImUser::getId, userBo.getId());
        ImUser existUser = imUserMapper.selectOne(lqw);

        Long userId;
        if (existUser != null) {
            // 用户已存在,直接使用已有用户ID
            userId = existUser.getId();
        } else {
            // 执行注册逻辑
            Boolean success = insertByBo(userBo);
            if (!success) {
                throw new RuntimeException("注册失败");
            }
            userId = userBo.getId();
        }

        // 创建IM用户token
        LoginUser imUser = new LoginUser();
        imUser.setUserId(userId);
        imUser.setUserType(UserType.IM_USER.getUserType()); // 设置为IM用户类型
        imUser.setUsername(userBo.getNickname());
        imUser.setNickname(userBo.getNickname());

        // 生成token
        SaLoginModel model = new SaLoginModel();
        // 验证设备类型是否有效
        try {
            DeviceType deviceType = DeviceType.getDeviceType(bo.getDevice());  // 验证设备类型是否存在于枚举中
            if (deviceType == null) {
                throw new RuntimeException("无效的设备类型: " + bo.getDevice());
            }

            model.setDevice(deviceType.getDevice());     // 设置设备类型
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("无效的设备类型: " + bo.getDevice());
        }
        model.setTimeout(30 * 24 * 60 * 60L); // IM token可以设置更长时间
        LoginHelper.login(imUser, model);

        ImUserVo imUserVo = queryById(userId);

        OpenApiImUserVo openApiImUserVo = new OpenApiImUserVo();
        openApiImUserVo.setImUserVo(imUserVo);
        openApiImUserVo.setToken("Bearer " + StpUtil.getTokenValue());

        // 返回用户信息
        return openApiImUserVo;
    }
}
