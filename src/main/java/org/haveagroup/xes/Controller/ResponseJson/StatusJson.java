package org.haveagroup.xes.Controller.ResponseJson;

public class StatusJson {
    String status;
    String username;

    public StatusJson() {
    }

    public StatusJson(String status, String username) {
        this.status = status;
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
