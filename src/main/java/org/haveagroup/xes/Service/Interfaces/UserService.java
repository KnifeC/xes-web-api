package org.haveagroup.xes.Service.Interfaces;

import org.haveagroup.xes.Dal.Model.User;

public interface UserService {

    User login(String email,String password);
    boolean register(String email,String username,String password);
    boolean isEmailUsed(String email);
    User editInfo(String userId,String email,String username);

    boolean changeType(String userId,String type);
    boolean editUsername(String userId,String username);
    boolean editPassword(String userId,String username);
    //boolean changePart(String userId,String partId);

    User findByUserId(String userId);
    User findByEmail(String email);
}
