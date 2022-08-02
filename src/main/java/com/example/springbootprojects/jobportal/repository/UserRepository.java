package com.example.springbootprojects.jobportal.repository;

import com.example.springbootprojects.jobportal.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends MongoRepository<User, UUID> {

    @Query("{'contactDetails.userMobile.number':'?0'}")
    Optional<User> findByMobile(String mobile);
}
