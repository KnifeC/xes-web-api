package org.haveagroup.xes.Web.Controller;


import org.haveagroup.xes.Commom.SessionKey;
import org.haveagroup.xes.Commom.Status;
import org.haveagroup.xes.Dal.Model.Tag;
import org.haveagroup.xes.Service.Interfaces.TagService;
import org.haveagroup.xes.Web.ResponseJson.StatusJson;
import org.haveagroup.xes.Web.ResponseJson.TagDataJson;
import org.haveagroup.xes.Web.ResponseJson.TagJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TagController {
    @Autowired
    TagService tagService;

    @PostMapping(value="webapi/createTag")
    public TagJson createTag(String tagName, HttpSession session){
        List<TagDataJson> tagDataList = new ArrayList<>();
        if(session.getAttribute(SessionKey.USER_TYPE)==null){
            return new TagJson(new StatusJson(Status.ERROR,"没有登陆","THIS"),tagDataList);
        }
        Tag tag = tagService.createTag(tagName);
        TagDataJson tagDataJson = new TagDataJson(tag.getTagId(),tag.getTagName());
        tagDataList.add(tagDataJson);
        return new TagJson(new StatusJson(Status.SUCCESS,"新建Tag成功","THIS"),tagDataList);
    }




}
