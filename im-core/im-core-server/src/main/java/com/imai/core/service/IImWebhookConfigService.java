package com.imai.core.service;

import com.imai.core.domain.bo.ImWebhookConfigBo;
import com.imai.core.domain.vo.ImWebhookConfigVo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * Webhook配置Service接口
 *
 * @author wei
 * @date 2025-01-07
 */
public interface IImWebhookConfigService {

    /**
     * 查询Webhook配置
     *
     * @param id 主键
     * @return Webhook配置
     */
    ImWebhookConfigVo queryById(Long id);

    /**
     * 分页查询Webhook配置列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return Webhook配置分页列表
     */
    TableDataInfo<ImWebhookConfigVo> queryPageList(ImWebhookConfigBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的Webhook配置列表
     *
     * @param bo 查询条件
     * @return Webhook配置列表
     */
    List<ImWebhookConfigVo> queryList(ImWebhookConfigBo bo);

    /**
     * 新增Webhook配置
     *
     * @param bo Webhook配置
     * @return 是否新增成功
     */
    Boolean insertByBo(ImWebhookConfigBo bo);

    /**
     * 修改Webhook配置
     *
     * @param bo Webhook配置
     * @return 是否修改成功
     */
    Boolean updateByBo(ImWebhookConfigBo bo);

    /**
     * 校验并批量删除Webhook配置信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
