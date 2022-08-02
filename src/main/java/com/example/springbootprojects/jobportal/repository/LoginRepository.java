package com.example.springbootprojects.jobportal.repository;

import com.example.springbootprojects.jobportal.models.login.LoginDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LoginRepository extends MongoRepository<LoginDetails, UUID> {
}
