package org.haveagroup.xes.Web.ResponseJson;

public class StatusJson {
    String status;
    String message;
    String next;

    @Override
    public String toString() {
        return "StatusJson{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", next='" + next + '\'' +
                '}';
    }

    public StatusJson(String status, String message, String next) {
        this.status = status;
        this.message = message;
        this.next = next;
    }

    public StatusJson() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }
}
