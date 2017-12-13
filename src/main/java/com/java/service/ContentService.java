package com.java.service;


import com.xiaowo.Content;
import com.xiaowo.ContentType;
import com.xiaowo.Zone;

import java.util.List;

/**
 * @author answer
 *         2017/11/3
 */
public interface ContentService {
    /**
     * 根据栏目查询记录
     *
     * @param categoryId
     * @return
     */
    List<Content> findContentByCategory(Integer categoryId);

    /**
     * 查询部分记录展示
     *@param client
     * @return
     */
    List<Content> findContentByCategory(String client);

    /**
     * 获取所有的类型
     *
     * @return
     */
    List<ContentType> findAllTypes();

    /**
     * 获取所有的城市站点
     *
     * @return
     */
    List<Zone> findAllZones();

    /**
     * 保存tag 返回tagId
     *
     * @param tags
     * @return
     */
    String saveTags(List<String> tags);

    /**
     * 保存图片
     *
     * @param picId
     * @param picUrl
     * @param remark
     */
    void savePic(Integer picId, String picUrl, String remark);

    /**
     * 保存文章
     *
     * @param sourceUrl
     * @param remark
     * @param content
     * @return
     */
    Integer saveText(String sourceUrl, String remark, String content);

    /**
     * 保存网站更新的内容
     *@param client
     * @param editor
     * @param categoryId
     * @param title
     * @param shortTitle
     * @param tagIds
     * @param zoneId
     * @param typeId
     * @param textId
     * @param recommend
     * @param decorateMoney
     * @param decorateTime
     * @param monthRepayment
     * @param activeTime
     * @param cooperativeBusiness
     */
    void saveContet(String editor, Integer categoryId, Integer textId, Integer picId, String title, String shortTitle, String tagIds, Integer zoneId, Integer typeId, String recommend, String decorateMoney, String decorateTime, String monthRepayment, String client, String activeTime, String cooperativeBusiness);

    /**
     * 根据id查询文章
     *
     * @param id
     * @return
     */
    Content findContentById(Integer id);

    /**
     * 更新文章
     *
     * @param textId
     * @param sourceUrl
     * @param remark
     * @param content
     */
    void updateText(Integer textId, String sourceUrl, String remark, String content);

    /**
     * 更新content
     *
     * @param id
     * @param title
     * @param shortTitle
     * @param zoneId
     * @param typeId
     * @param picId
     * @param tagIds
     * @param recommend
     * @param decorateMoney
     * @param decorateTime
     * @param monthRepayment
     * @param client
     */
    void updateContent(Integer id, String title, String shortTitle, Integer zoneId, Integer typeId, Integer picId, String tagIds, String recommend, String decorateMoney, String decorateTime, String monthRepayment, String client, String activeTime, String cooperativeBusiness);

    /**
     * 删除content（将状态改为0）
     *
     * @param id
     */
    void deleteContent(Integer id);

    /**
     * 发布
     *
     * @param list
     */
    void publish(List list);
}
