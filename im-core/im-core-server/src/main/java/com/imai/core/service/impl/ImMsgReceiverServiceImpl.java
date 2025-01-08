package com.imai.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imai.core.domain.ImMsgReceiver;
import com.imai.core.domain.bo.ImMsgReceiverBo;
import com.imai.core.domain.vo.ImMsgReceiverVo;
import com.imai.core.mapper.ImMsgReceiverMapper;
import com.imai.core.service.IImMsgReceiverService;
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
 * 消息接收Service业务层处理
 *
 * @author wei
 * @date 2025-01-07
 */
@RequiredArgsConstructor
@Service
public class ImMsgReceiverServiceImpl implements IImMsgReceiverService {

    private final ImMsgReceiverMapper baseMapper;

    /**
     * 查询消息接收
     *
     * @param id 主键
     * @return 消息接收
     */
    @Override
    public ImMsgReceiverVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询消息接收列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 消息接收分页列表
     */
    @Override
    public TableDataInfo<ImMsgReceiverVo> queryPageList(ImMsgReceiverBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ImMsgReceiver> lqw = buildQueryWrapper(bo);
        Page<ImMsgReceiverVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的消息接收列表
     *
     * @param bo 查询条件
     * @return 消息接收列表
     */
    @Override
    public List<ImMsgReceiverVo> queryList(ImMsgReceiverBo bo) {
        LambdaQueryWrapper<ImMsgReceiver> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ImMsgReceiver> buildQueryWrapper(ImMsgReceiverBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ImMsgReceiver> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFkMsgId() != null, ImMsgReceiver::getFkMsgId, bo.getFkMsgId());
        lqw.eq(bo.getFkReceiverId() != null, ImMsgReceiver::getFkReceiverId, bo.getFkReceiverId());
        lqw.eq(bo.getDeleted() != null, ImMsgReceiver::getDeleted, bo.getDeleted());
        lqw.eq(StringUtils.isNotBlank(bo.getExtras()), ImMsgReceiver::getExtras, bo.getExtras());
        return lqw;
    }

    /**
     * 新增消息接收
     *
     * @param bo 消息接收
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ImMsgReceiverBo bo) {
        ImMsgReceiver add = MapstructUtils.convert(bo, ImMsgReceiver.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改消息接收
     *
     * @param bo 消息接收
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ImMsgReceiverBo bo) {
        ImMsgReceiver update = MapstructUtils.convert(bo, ImMsgReceiver.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ImMsgReceiver entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除消息接收信息
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
