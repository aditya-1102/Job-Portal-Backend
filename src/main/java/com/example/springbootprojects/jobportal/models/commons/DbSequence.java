package com.example.springbootprojects.jobportal.models.commons;

import com.example.springbootprojects.jobportal.constants.UserConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class DbSequence {

    @Id
    private UserConstants.DB_SEQUENCE_TYPE itemType;
    private int seqValue;

    public UserConstants.DB_SEQUENCE_TYPE getItemType() {
        return itemType;
    }

    public void setItemType(UserConstants.DB_SEQUENCE_TYPE itemType) {
        this.itemType = itemType;
    }

    public int getSeqValue() {
        return seqValue;
    }

    public void setSeqValue(int seqValue) {
        this.seqValue = seqValue;
    }
}
