package com.doan.webservices.demo;

public class HelloWorldBean {

    private String message;
    private String rapper;

    public HelloWorldBean() {
    }

    public HelloWorldBean(String message, String rapper) {
        this.message = message;
        this.rapper = rapper;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRapper() {
        return rapper;
    }

    public void setRapper(String rapper) {
        this.rapper = rapper;
    }

    @Override
    public String toString() {
        return "HelloWorldBean{" +
                "message='" + message + '\'' +
                ", rapper='" + rapper + '\'' +
                '}';
    }

}
