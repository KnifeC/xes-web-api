package org.haveagroup.xes.Service.Interfaces;

import org.haveagroup.xes.Dal.Model.Part_User;

public interface Part_User_Service {
    Part_User addUserToPart(String userId,String partId);
    boolean deleteUserFromPart(String userId,String partId);


}
