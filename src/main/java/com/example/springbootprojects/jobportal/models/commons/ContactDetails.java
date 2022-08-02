package com.example.springbootprojects.jobportal.models.commons;

public class ContactDetails {

    private Mobile userMobile;
    private Mobile alternateMobile;

    public Mobile getUserMobile() {
        return userMobile;
    }
    public void setUserMobile(Mobile userMobile) {
        this.userMobile = userMobile;
    }
    public Mobile getAlternateMobile() {
        return alternateMobile;
    }
    public void setAlternateMobile(Mobile alternateMobile) {
        this.alternateMobile = alternateMobile;
    }
}
