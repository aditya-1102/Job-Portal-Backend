package com.example.springbootprojects.jobportal.models.login;

import com.example.springbootprojects.jobportal.constants.UserConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class LoginDetails {
    @Id
    private UUID userId;
    private UserConstants.USER_LOGIN_TYPE userType;
    private String password;
    private String userName;
    private boolean isReset;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UserConstants.USER_LOGIN_TYPE getUserType() {
        return userType;
    }

    public void setUserType(UserConstants.USER_LOGIN_TYPE userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isReset() {
        return isReset;
    }

    public void setReset(boolean reset) {
        isReset = reset;
    }

}
