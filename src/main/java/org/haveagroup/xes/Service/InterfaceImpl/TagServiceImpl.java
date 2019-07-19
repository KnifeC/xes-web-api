package org.haveagroup.xes.Service.InterfaceImpl;


import org.haveagroup.xes.Dal.Model.Tag;
import org.haveagroup.xes.Dal.Repo.QuestionRepo;
import org.haveagroup.xes.Dal.Repo.Question_Tag_Repo;
import org.haveagroup.xes.Dal.Repo.TagRepo;
import org.haveagroup.xes.Service.Interfaces.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    QuestionRepo questionRepo;
    @Autowired
    TagRepo tagRepo;
    @Autowired
    Question_Tag_Repo question_tag_repo;

    @Override
    public Tag findByTagId(String tagId){
        return tagRepo.findByTagId(tagId);
    }

    @Override
    public List<Tag> findAllByTagNameLike(String tagName){
        try{
            List<Tag> allByTagName = tagRepo.findAllByTagNameLike("%"+tagName+"%");
            return allByTagName;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Tag createTag(String tagName){
        try{
            Tag tag = new Tag();
            tag.setTagName(tagName);
            tagRepo.save(tag);
            return tag;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Tag findByTagName(String tagName){
        try{
            return tagRepo.findByTagName(tagName);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
