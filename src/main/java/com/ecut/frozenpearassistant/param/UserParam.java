package com.ecut.frozenpearassistant.param;

public class UserParam {
    private String userId;
    private String userName;
    private Integer sex;
    private String avatar;
    private String phone;
    private String userPw;
    private String newPwd;
    private String email;

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserParam{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", sex=" + sex +
                ", avatar='" + avatar + '\'' +
                ", phone='" + phone + '\'' +
                ", userPw='" + userPw + '\'' +
                ", newPwd='" + newPwd + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
