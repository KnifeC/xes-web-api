package org.haveagroup.xes.Service.InterfaceImpl;

import org.haveagroup.xes.Dal.Model.Question;
import org.haveagroup.xes.Dal.Model.Question_Tag;
import org.haveagroup.xes.Dal.Model.Tag;
import org.haveagroup.xes.Dal.Repo.QuestionRepo;
import org.haveagroup.xes.Dal.Repo.Question_Tag_Repo;
import org.haveagroup.xes.Dal.Repo.TagRepo;
import org.haveagroup.xes.Service.Interfaces.Question_Tag_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Question_Tag_ServiceImpl implements Question_Tag_Service {
    @Autowired
    Question_Tag_Repo question_tag_repo;
    @Autowired
    QuestionRepo questionRepo;
    @Autowired
    TagRepo tagRepo;

    @Override
    public List<Question> findByTagName(String tagName){
        List<Question> questionList = new ArrayList<>();
        Tag tag = tagRepo.findByTagName(tagName);
        String tagId = tag.getTagId();
        List<Question_Tag> question_tags = question_tag_repo.findAllQuestionIdByTagId(tagId);
        for(Question_Tag question_tag : question_tags){
            questionList.add(questionRepo.findByQuestionId(question_tag.getQuestionId()));
        }
        return questionList;
    }
}
