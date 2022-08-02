package com.example.springbootprojects.jobportal.models.commons;

import java.util.Date;
import java.util.List;

public class WorkDetails {

    private String companyName;
    private String role;
    private String roleDescription;
    private List<String> toolsUsed;
    private Date startDate;
    private Date endDate;
    private boolean isCurrentlyWorking;
    private String noticePeriod;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public List<String> getToolsUsed() {
        return toolsUsed;
    }

    public void setToolsUsed(List<String> toolsUsed) {
        this.toolsUsed = toolsUsed;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isCurrentlyWorking() {
        return isCurrentlyWorking;
    }

    public void setCurrentlyWorking(boolean currentlyWorking) {
        isCurrentlyWorking = currentlyWorking;
    }

    public String getNoticePeriod() {
        return noticePeriod;
    }

    public void setNoticePeriod(String noticePeriod) {
        this.noticePeriod = noticePeriod;
    }
}
