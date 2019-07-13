package org.haveagroup.xes.Web.ResponseJson;

import org.haveagroup.xes.Dal.Model.User;

public class UserDataJson {
    String username;
    String email;

    public UserDataJson(User user){
        this.username = user.getUsername();
        this.email = user.getEmail();
    }

    public UserDataJson() {
    }

    public UserDataJson(String username, String email) {
        this.username = username;
        this.email = email;
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
}
