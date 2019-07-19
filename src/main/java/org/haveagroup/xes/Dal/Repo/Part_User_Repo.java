package org.haveagroup.xes.Dal.Repo;

import org.haveagroup.xes.Dal.Model.Part_User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Part_User_Repo extends JpaRepository<Part_User,String> {
    Part_User findByUserIdAndPartId(String userId,String partId);
    List<Part_User> findAllByUserId(String userId);
    List<Part_User> findAllByPartId(String partId);
}
