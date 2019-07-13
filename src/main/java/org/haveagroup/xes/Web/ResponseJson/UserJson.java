package org.haveagroup.xes.Web.ResponseJson;

public class UserJson {
    StatusJson status;
    UserDataJson user;

    public UserJson(StatusJson status, UserDataJson user) {
        this.status = status;
        this.user = user;
    }

    public StatusJson getStatus() {
        return status;
    }

    public void setStatus(StatusJson status) {
        this.status = status;
    }

    public UserDataJson getUser() {
        return user;
    }

    public void setUser(UserDataJson user) {
        this.user = user;
    }
}

