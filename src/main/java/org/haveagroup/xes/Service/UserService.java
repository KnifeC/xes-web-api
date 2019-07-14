package org.haveagroup.xes.Service;

import org.haveagroup.xes.Dal.Model.User;

public interface UserService {

    User login(String email,String password);
    void register(String email,String username,String password);
    boolean isEmailUsed(String email);

}
