package org.haveagroup.xes.Dal.Repo;

import org.haveagroup.xes.Dal.Model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepo extends JpaRepository<Tag,String> {
    Tag findByTagId(String tagId);
    Tag findByTagName(String tagName);
    List<Tag> findAllByTagNameLike(String tagName);


}
