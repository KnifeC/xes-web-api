package org.haveagroup.xes.Web.ResponseJson;

import org.haveagroup.xes.Dal.Model.User;

public class UserDataJson {
    String userUuid;
    String username;
    String email;
    String type;

    public UserDataJson(User user){
        this.userUuid = user.getUserId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.type =  user.getType();
    }

    public UserDataJson() {
    }

    public UserDataJson(String userUuid, String username, String email, String type) {
        this.userUuid = userUuid;
        this.username = username;
        this.email = email;
        this.type = type;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
