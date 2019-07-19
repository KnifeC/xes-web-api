package org.haveagroup.xes.Service.Interfaces;

import org.haveagroup.xes.Dal.Model.Question;
import org.haveagroup.xes.Dal.Model.Question_Tag;

import java.util.List;

public interface Question_Tag_Service {
    List<Question> findByTagName(String tagName);

}
