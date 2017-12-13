package com.java.service.impl;

import com.java.dao.*;
import com.java.service.ContentService;
import com.java.service.MenuNavService;
import com.utils.JsonUtils;
import com.xiaowo.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author answer
 *         2017/11/3
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Resource
    private ContentDao contentDao;
    @Resource
    private MenuNavService menuNavService;
    @Resource
    private ContentTypeDao contentTypeDao;
    @Resource
    private TagDao tagDao;
    @Resource
    private PictureDao pictureDao;
    @Resource
    private TextDao textDao;

    @Override
    public List<Content> findContentByCategory(Integer categoryId) {
        List<Content> contents = contentDao.findContentByCategoryId(categoryId);
//        if (contents != null && contents.size() > 0) {
//            formatContent(contents);
//        }
        return contents;
    }

    @Override
    public List<Content> findContentByCategory(String client) {
        List<Content> contents = contentDao.findContents(client);
//        if (contents != null && contents.size() > 0) {
//            formatContent(contents);
//        }
        return contents;
    }

    @Override
    public List<ContentType> findAllTypes() {
        return contentTypeDao.findContentTypes();
    }

    @Override
    public List<Zone> findAllZones() {
        return contentDao.findZones();
    }

    @Override
    public String saveTags(List<String> tags) {
        List<Integer> tagIds = new ArrayList<>();
        if (tags.size() > 0) {
            for (String tag : tags) {
                Tag t = tagDao.findTagByTag(tag);
                if (t != null) {
                    //标签已经存在，将标签引用的次数加1
                    tagDao.updateTagReferenceCount(t.getReferenceCount() + 1, t.getId());
                } else {
                    //插入新的标签
                    t = new Tag();
                    t.setReferenceCount(1);
                    t.setTag(tag);
                    tagDao.insertTag(t);
                }
                tagIds.add(t.getId());
            }
        }
        return JsonUtils.objectToJson(tagIds);
    }

    @Override
    public void savePic(Integer picId, String picUrl, String remark) {
        Picture picture = new Picture();
        picture.setId(picId);
        picture.setUrl(picUrl);
        picture.setDescription(remark);
        pictureDao.updatePicture(picture);
    }

    @Override
    public Integer saveText(String sourceUrl, String remark, String content) {
        if (StringUtils.isEmpty(sourceUrl) && StringUtils.isEmpty(remark) && StringUtils.isEmpty(content)) {
            return null;
        }
        Text text = new Text();
        text.setSourceUrl(sourceUrl);
        text.setRemark(remark);
        text.setContent(content);
        textDao.insertText(text);
        return text.getId();
    }

    @Override
    public void saveContet(String editor, Integer categoryId, Integer textId, Integer picId, String title,
                           String shortTitle, String tagIds, Integer zoneId, Integer typeId, String recommend,
                           String decorateMoney, String decorateTime, String monthRepayment, String client, String activeTime, String cooperativeBusiness) {
        Content content = new Content();
        content.setClient(client);
        content.setEditor(editor);
        content.setCategoryId(categoryId);
        content.setTextId(textId);
        content.setPicId(picId);
        content.setTitle(title);
        content.setShortTitle(shortTitle);
        content.setTags(tagIds);
        content.setZoneId(zoneId);
        content.setTypeId(typeId);
        content.setRecommend(recommend);
        content.setDecorateMoney(decorateMoney);
        content.setDecorateTime(decorateTime);
        content.setMonthRepayment(monthRepayment);
        content.setActiveTime(activeTime);
        content.setCooperativeBusiness(cooperativeBusiness);
        contentDao.insertContent(content);
    }

    @Override
    public Content findContentById(Integer id) {
        Content content = contentDao.findContentById(id);
        //标签
        String tags = content.getTags();
        if (tags != null) {
            String tagStr = "";
            List tagList = JsonUtils.listFormJSONStr(tags);
            for (int i = 0; i < tagList.size(); i++) {
                Integer tagId = (Integer) tagList.get(i);
                Tag tag = tagDao.findTagById(tagId);
                if (i == 0) {
                    tagStr = tag.getTag();
                } else {
                    tagStr = tagStr + "," + tag.getTag();
                }
            }
            content.setTags(tagStr);
        }

        //图片
        Integer picId = content.getPicId();
        if (picId != null) {
            Picture picture = pictureDao.findPictureById(picId);
            content.setPicture(picture);
        }

        //文章
        Integer textId = content.getTextId();
        if (textId != null) {
            Text text = textDao.findTextById(textId);
            content.setText(text);
        }
        return content;
    }

    @Override
    public void updateText(Integer textId, String sourceUrl, String remark, String content) {
        Text text = new Text();
        text.setId(textId);
        text.setSourceUrl(sourceUrl);
        text.setRemark(remark);
        text.setContent(content);
        textDao.updateText(text);
    }

    @Override
    public void updateContent(Integer id, String title, String shortTitle, Integer zoneId, Integer typeId,
                              Integer picId, String tagIds, String recommend, String decorateMoney,
                              String decorateTime, String monthRepayment, String client, String activeTime, String cooperativeBusiness) {
        Content content = new Content();
        content.setId(id);
        content.setClient(client);
        content.setPicId(picId);
        content.setTitle(title);
        content.setShortTitle(shortTitle);
        content.setTags(tagIds);
        content.setZoneId(zoneId);
        content.setTypeId(typeId);
        content.setRecommend(recommend);
        content.setDecorateMoney(decorateMoney);
        content.setDecorateTime(decorateTime);
        content.setMonthRepayment(monthRepayment);
        content.setActiveTime(activeTime);
        content.setCooperativeBusiness(cooperativeBusiness);
        contentDao.updateContent(content);
    }

    @Override
    public void deleteContent(Integer id) {
        contentDao.updateContentStatus(id, "0");
    }

    @Override
    public void publish(List list) {
        for (Object str : list) {
            Integer id = Integer.parseInt(str.toString());
            contentDao.updateContentStatus(id, "2");
        }
    }

    private void formatContent(List<Content> contents) {
        for (Content content : contents) {
            Integer categoryId = content.getCategoryId();
            String menuNav = menuNavService.findMenuNav(categoryId);
            content.setCategory(menuNav);
            Integer typeId = content.getTypeId();
            ContentType type = contentTypeDao.findContentTypeById(typeId);
            content.setContentType(type);
        }
    }
}
