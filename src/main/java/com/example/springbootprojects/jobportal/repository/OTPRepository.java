package com.example.springbootprojects.jobportal.repository;

import com.example.springbootprojects.jobportal.models.login.OTPData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OTPRepository extends MongoRepository<OTPData, UUID> {
}
