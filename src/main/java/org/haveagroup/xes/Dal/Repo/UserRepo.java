package org.haveagroup.xes.Dal.Repo;

import org.haveagroup.xes.Dal.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,String> {
    User findByEmailAndPassword(String email,String password);
    User findByEmail(String email);
    User findByUserId(String userId);
    //User findByUsername(String userId);
}
