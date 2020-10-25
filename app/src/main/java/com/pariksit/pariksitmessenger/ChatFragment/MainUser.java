package com.pariksit.pariksitmessenger.ChatFragment;

public class MainUser {
    String name, group, registeredBy, userType, id, dp, dpStamp;

    public MainUser() {
    }

    public MainUser(String name, String group, String registeredBy, String userType, String id, String dp, String dpStamp) {
        this.name = name;
        this.group = group;
        this.registeredBy = registeredBy;
        this.userType = userType;
        this.id = id;
        this.dp = dp;
        this.dpStamp = dpStamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getRegisteredBy() {
        return registeredBy;
    }

    public void setRegisteredBy(String registeredBy) {
        this.registeredBy = registeredBy;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDp() {
        return dp;
    }

    public void setDp(String dp) {
        this.dp = dp;
    }

    public String getDpStamp() {
        return dpStamp;
    }

    public void setDpStamp(String dpStamp) {
        this.dpStamp = dpStamp;
    }
}
