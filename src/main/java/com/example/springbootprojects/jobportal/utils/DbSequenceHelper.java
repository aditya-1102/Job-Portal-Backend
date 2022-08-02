package com.example.springbootprojects.jobportal.utils;

import com.example.springbootprojects.jobportal.constants.UserConstants;
import com.example.springbootprojects.jobportal.models.commons.DbSequence;
import com.example.springbootprojects.jobportal.repository.DbSequenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class DbSequenceHelper {
    public final int SEQUENCE_START_VALUE = 1;
    public final int START_VALUE = 1000;

    @Autowired
    DbSequenceRepository dbSequenceRepository;

    public int getNextSequenceValue(UserConstants.DB_SEQUENCE_TYPE db_sequence_type) {
        AtomicReference<DbSequence> dbSequenceRef = new AtomicReference<>();
        Optional<DbSequence> dbSequenceOptional = dbSequenceRepository.findById(db_sequence_type);
        dbSequenceOptional.ifPresentOrElse(dbSequence -> {
            dbSequence.setSeqValue(dbSequence.getSeqValue() + 1);
            dbSequenceRef.set(dbSequence);
        }, () -> {
            DbSequence dbSequence = new DbSequence();
            dbSequence.setItemType(db_sequence_type);
            dbSequence.setSeqValue(SEQUENCE_START_VALUE);
            dbSequenceRef.set(dbSequence);
        });
        return dbSequenceRepository.save(dbSequenceRef.get()).getSeqValue();
    }
}
