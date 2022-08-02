package com.example.springbootprojects.jobportal.repository;

import com.example.springbootprojects.jobportal.constants.UserConstants;
import com.example.springbootprojects.jobportal.models.commons.DbSequence;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DbSequenceRepository extends MongoRepository<DbSequence, UserConstants.DB_SEQUENCE_TYPE> {
}
