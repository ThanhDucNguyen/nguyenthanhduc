package com.witbus.demo.dto;

public class HelpDTO {
    private String name;
    private String email;
    private String emailUser;
    private String short_info;
    private String info;

    @Override
    public String toString() {
        return "HelpDTO{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", short_info='" + short_info + '\'' +
                ", info='" + info + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getShort_info() {
        return short_info;
    }

    public void setShort_info(String short_info) {
        this.short_info = short_info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }
}
