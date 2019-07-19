package org.haveagroup.xes.Service.Interfaces;

import org.haveagroup.xes.Dal.Model.Tag;

import java.util.List;

public interface TagService {
    Tag findByTagId(String tagId);
    List<Tag> findAllByTagNameLike(String tagName);
    Tag createTag(String tagName);
    Tag findByTagName(String tagName);
}
