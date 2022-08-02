package com.example.springbootprojects.jobportal.models;

import com.example.springbootprojects.jobportal.constants.UserConstants;
import com.example.springbootprojects.jobportal.models.commons.ContactDetails;
import com.example.springbootprojects.jobportal.models.commons.PersonalDetails;
import com.example.springbootprojects.jobportal.models.commons.UserAddress;
import com.example.springbootprojects.jobportal.models.commons.WorkDetails;
import com.example.springbootprojects.jobportal.models.login.DeviceInfo;
import org.apache.logging.log4j.core.util.UuidUtil;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Document
public class User {

    @Id
    private UUID userId;
    private String userDisplayId;
    private UserConstants.USER_TYPE userType;
    private boolean isAdmin;
    private String userName;
    private UserAddress currentAddress;
    private UserAddress permanentAddress;
    private ContactDetails contactDetails;
    private PersonalDetails personalDetails;
    private List<WorkDetails> workDetails;
    private Date createdTimeStamp;
    private Date updatedTimeStamp;
    private boolean isActive;
    private boolean isVerified;
    private List<DeviceInfo> deviceInfoList;

    public User() {
        this.userId = UuidUtil.getTimeBasedUuid();
        this.createdTimeStamp = new Date();
        this.isActive = true;
        this.isVerified = false;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUserDisplayId() {
        return userDisplayId;
    }

    public void setUserDisplayId(String userDisplayId) {
        this.userDisplayId = userDisplayId;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public List<DeviceInfo> getDeviceInfoList() {
        return deviceInfoList;
    }

    public void setDeviceInfoList(List<DeviceInfo> deviceInfoList) {
        this.deviceInfoList = deviceInfoList;
    }

    public UserConstants.USER_TYPE getUserType() {
        return userType;
    }

    public void setUserType(UserConstants.USER_TYPE userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserAddress getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(UserAddress currentAddress) {
        this.currentAddress = currentAddress;
    }

    public UserAddress getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(UserAddress permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public com.example.springbootprojects.jobportal.models.commons.ContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(com.example.springbootprojects.jobportal.models.commons.ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }

    public PersonalDetails getPersonalDetails() {
        return personalDetails;
    }

    public void setPersonalDetails(PersonalDetails personalDetails) {
        this.personalDetails = personalDetails;
    }

    public List<WorkDetails> getWorkDetails() {
        return workDetails;
    }

    public void setWorkDetails(List<WorkDetails> workDetails) {
        this.workDetails = workDetails;
    }

    public Date getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    public void setCreatedTimeStamp(Date createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Date getUpdatedTimeStamp() {
        return updatedTimeStamp;
    }

    public void setUpdatedTimeStamp(Date updatedTimeStamp) {
        this.updatedTimeStamp = updatedTimeStamp;
    }

    //setting device information
    public void addDeviceInfo(DeviceInfo deviceInfo) {
        if(deviceInfo != null) {
            if(deviceInfoList != null) {
                for (int i = 0; i < deviceInfoList.size(); i++) {
                    if (deviceInfoList.get(i).getDeviceId().equals(deviceInfo.getDeviceId())) {
                        deviceInfoList.remove(i);
                        break;
                    }
                }
            } else {
                deviceInfoList = new ArrayList<>();
            }
            deviceInfoList.add(deviceInfo);
        }
    }

    //remove device information
    public void removeDeviceInfo(DeviceInfo deviceInfo) {
        if (deviceInfo != null && deviceInfoList != null) {
            for (int i = 0; i < deviceInfoList.size(); i++) {
                if (deviceInfoList.get(i).getDeviceId().equals(deviceInfo.getDeviceId())) {
                    deviceInfoList.remove(i);
                    break;
                }
            }
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userDisplayId='" + userDisplayId + '\'' +
                ", userType=" + userType +
                ", isAdmin=" + isAdmin +
                ", userName='" + userName + '\'' +
                ", currentAddress=" + currentAddress +
                ", permanentAddress=" + permanentAddress +
                ", contactDetails=" + contactDetails +
                ", personalDetails=" + personalDetails +
                ", workDetails=" + workDetails +
                ", createdTimeStamp=" + createdTimeStamp +
                ", updatedTimeStamp=" + updatedTimeStamp +
                ", isActive=" + isActive +
                ", deviceInfoList=" + deviceInfoList +
                '}';
    }
}
