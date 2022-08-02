package com.example.springbootprojects.jobportal.models;

import com.example.springbootprojects.jobportal.models.commons.Address;
import org.apache.logging.log4j.core.util.UuidUtil;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Document
public class Jobs {

    @Id
    private UUID jobId;
    private String jobDisplayId;
    private String jobTitle;
    private String companyName;
    private String jobRole;
    private String jobDescription;
    private Address jobAddress;
    private Date createdTimeStamp;
    private Date updatedTimeStamp;
    private boolean isActive;

    //default constructor
    public Jobs() {
        this.jobId = UuidUtil.getTimeBasedUuid();
        this.createdTimeStamp = new Date();
        this.isActive = true;
    }

    //getters and setters
    public UUID getJobId() {
        return jobId;
    }
    public void setJobId(UUID jobId) {
        this.jobId = jobId;
    }
    public String getJobDisplayId() {
        return jobDisplayId;
    }
    public void setJobDisplayId(String jobDisplayId) {
        this.jobDisplayId = jobDisplayId;
    }
    public String getJobTitle() {
        return jobTitle;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getJobRole() {
        return jobRole;
    }
    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }
    public String getJobDescription() {
        return jobDescription;
    }
    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }
    public Address getJobAddress() {
        return jobAddress;
    }
    public void setJobAddress(Address jobAddress) {
        this.jobAddress = jobAddress;
    }
    public Date getCreatedTimeStamp() {
        return createdTimeStamp;
    }
    public void setCreatedTimeStamp(Date createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }
    public Date getUpdatedTimeStamp() {
        return updatedTimeStamp;
    }
    public void setUpdatedTimeStamp(Date updatedTimeStamp) {
        this.updatedTimeStamp = updatedTimeStamp;
    }
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }

    //toString() method - (to print data while printing object directly)
    @Override
    public String toString() {
        return "Jobs{" +
                "jobId=" + jobId +
                ", jobDisplayId='" + jobDisplayId + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", companyName='" + companyName + '\'' +
                ", jobRole='" + jobRole + '\'' +
                ", jobDescription='" + jobDescription + '\'' +
                ", jobAddress=" + jobAddress +
                ", createdTimeStamp=" + createdTimeStamp +
                ", updatedTimeStamp=" + updatedTimeStamp +
                ", isActive=" + isActive +
                '}';
    }
}
