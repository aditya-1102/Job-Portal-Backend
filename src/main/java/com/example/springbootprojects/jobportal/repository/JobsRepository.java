package com.example.springbootprojects.jobportal.repository;

import com.example.springbootprojects.jobportal.models.Jobs;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JobsRepository extends MongoRepository<Jobs, UUID> {

    @Query("{ $or: [ {'companyName': {'$regex':'(?i)?0'}} , {'jobRole': {'$regex':'(?i)?0'}} ] }")
    List<Jobs> searchJob(String searchRequest);


}
