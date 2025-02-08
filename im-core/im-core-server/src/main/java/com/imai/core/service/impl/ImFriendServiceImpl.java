package com.imai.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imai.core.domain.ImFriend;
import com.imai.core.domain.bo.ImFriendBo;
import com.imai.core.domain.vo.ImFriendVo;
import com.imai.core.mapper.ImFriendMapper;
import com.imai.core.service.IImFriendService;
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
 * 好友关系Service业务层处理
 *
 * @author wei
 * @date 2025-01-07
 */
@RequiredArgsConstructor
@Service
public class ImFriendServiceImpl implements IImFriendService {

    private final ImFriendMapper baseMapper;

    /**
     * 查询好友关系
     *
     * @param id 主键
     * @return 好友关系
     */
    @Override
    public ImFriendVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询好友关系列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 好友关系分页列表
     */
    @Override
    public TableDataInfo<ImFriendVo> queryPageList(ImFriendBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ImFriend> lqw = buildQueryWrapper(bo);
        Page<ImFriendVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的好友关系列表
     *
     * @param bo 查询条件
     * @return 好友关系列表
     */
    @Override
    public List<ImFriendVo> queryList(ImFriendBo bo) {
        LambdaQueryWrapper<ImFriend> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ImFriend> buildQueryWrapper(ImFriendBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ImFriend> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFkUserId() != null, ImFriend::getFkUserId, bo.getFkUserId());
        lqw.eq(bo.getFkFriendId() != null, ImFriend::getFkFriendId, bo.getFkFriendId());
        lqw.eq(bo.getConversationId() != null, ImFriend::getConversationId, bo.getConversationId());
        lqw.eq(bo.getSource() != null, ImFriend::getSource, bo.getSource());
        lqw.eq(bo.getStatus() != null, ImFriend::getStatus, bo.getStatus());
        lqw.eq(bo.getDeleted() != null, ImFriend::getDeleted, bo.getDeleted());
        lqw.eq(StringUtils.isNotBlank(bo.getExtras()), ImFriend::getExtras, bo.getExtras());
        return lqw;
    }

    /**
     * 新增好友关系
     *
     * @param bo 好友关系
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ImFriendBo bo) {
        ImFriend add = MapstructUtils.convert(bo, ImFriend.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改好友关系
     *
     * @param bo 好友关系
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ImFriendBo bo) {
        ImFriend update = MapstructUtils.convert(bo, ImFriend.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ImFriend entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除好友关系信息
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
