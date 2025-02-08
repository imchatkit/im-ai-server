package com.imai.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imai.core.domain.ImFriendRequest;
import com.imai.core.domain.bo.ImFriendRequestBo;
import com.imai.core.domain.vo.ImFriendRequestVo;
import com.imai.core.mapper.ImFriendRequestMapper;
import com.imai.core.service.IImFriendRequestService;
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
 * 好友申请Service业务层处理
 *
 * @author wei
 * @date 2025-01-07
 */
@RequiredArgsConstructor
@Service
public class ImFriendRequestServiceImpl implements IImFriendRequestService {

    private final ImFriendRequestMapper baseMapper;

    /**
     * 查询好友申请
     *
     * @param id 主键
     * @return 好友申请
     */
    @Override
    public ImFriendRequestVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询好友申请列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 好友申请分页列表
     */
    @Override
    public TableDataInfo<ImFriendRequestVo> queryPageList(ImFriendRequestBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ImFriendRequest> lqw = buildQueryWrapper(bo);
        Page<ImFriendRequestVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的好友申请列表
     *
     * @param bo 查询条件
     * @return 好友申请列表
     */
    @Override
    public List<ImFriendRequestVo> queryList(ImFriendRequestBo bo) {
        LambdaQueryWrapper<ImFriendRequest> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ImFriendRequest> buildQueryWrapper(ImFriendRequestBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ImFriendRequest> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFromUserId() != null, ImFriendRequest::getFromUserId, bo.getFromUserId());
        lqw.like(StringUtils.isNotBlank(bo.getFromUserRemarkName()), ImFriendRequest::getFromUserRemarkName, bo.getFromUserRemarkName());
        lqw.eq(bo.getToUserId() != null, ImFriendRequest::getToUserId, bo.getToUserId());
        lqw.eq(StringUtils.isNotBlank(bo.getMessage()), ImFriendRequest::getMessage, bo.getMessage());
        lqw.eq(bo.getStatus() != null, ImFriendRequest::getStatus, bo.getStatus());
        lqw.eq(bo.getHandleTime() != null, ImFriendRequest::getHandleTime, bo.getHandleTime());
        lqw.eq(bo.getDeleted() != null, ImFriendRequest::getDeleted, bo.getDeleted());
        lqw.eq(StringUtils.isNotBlank(bo.getExtras()), ImFriendRequest::getExtras, bo.getExtras());
        return lqw;
    }

    /**
     * 新增好友申请
     *
     * @param bo 好友申请
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ImFriendRequestBo bo) {
        ImFriendRequest add = MapstructUtils.convert(bo, ImFriendRequest.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改好友申请
     *
     * @param bo 好友申请
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ImFriendRequestBo bo) {
        ImFriendRequest update = MapstructUtils.convert(bo, ImFriendRequest.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ImFriendRequest entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除好友申请信息
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
