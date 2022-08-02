package com.example.springbootprojects.jobportal.services;

import com.example.springbootprojects.jobportal.constants.UserConstants;
import com.example.springbootprojects.jobportal.models.Jobs;
import com.example.springbootprojects.jobportal.models.requests.JobFilterRequest;
import com.example.springbootprojects.jobportal.models.requests.JobUpdateRequest;
import com.example.springbootprojects.jobportal.repository.JobsRepository;
import com.example.springbootprojects.jobportal.utils.DbSequenceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class JobsService {

    @Autowired
    private JobsRepository jobsRepository;

    @Autowired
    private DbSequenceHelper dbSequenceHelper;

    //CREATE JOB
    public Jobs createJob(Jobs createRequest) {

        //generating sequence value for job and jobDisplayId
        int displayCount = dbSequenceHelper.getNextSequenceValue(UserConstants.DB_SEQUENCE_TYPE.JOBS);
        String displayId = "JOB"+ "-";
        if (displayCount < 100) {
            displayId += String.format("%03d", displayCount);
        } else {
            displayId += String.valueOf(displayCount);
        }
        createRequest.setJobDisplayId(displayId);

        return jobsRepository.save(createRequest);
    }

    //GET ALL JOBS - (WITH PAGINATION)
    public Page<Jobs> getAllJobs(int pageNumber, int pageSize) {
        Sort sort = Sort.by("createdTimeStamp").descending();
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);
        return jobsRepository.findAll(pageable);
    }

    //GET JOB BY JOB-ID
    public Optional<Jobs> getJobById(UUID jobId) {
        return jobsRepository.findById(jobId);
    }

    //DELETE JOB BY JOB-ID
    public String deleteJobById(UUID jobId) {
        jobsRepository.deleteById(jobId);
        return "Job Deleted";
    }

    //UPDATE JOB
    public Jobs updateJob(JobUpdateRequest updateRequest) {
        AtomicReference<Jobs> jobsAtomicReference = new AtomicReference<>();
        if (updateRequest.getJobId() != null){
            jobsRepository.findById(updateRequest.getJobId()).ifPresentOrElse(data->{
                data.setUpdatedTimeStamp(new Date());
                if (updateRequest.getJobDescription() != null && !updateRequest.getJobDescription().isBlank()) {
                    data.setJobDescription(updateRequest.getJobDescription());
                }
                if (updateRequest.getJobRole() != null && !updateRequest.getJobRole().isBlank()) {
                    data.setJobRole(updateRequest.getJobRole());
                }
                if (updateRequest.getJobTitle() != null && !updateRequest.getJobTitle().isBlank()) {
                    data.setJobTitle(updateRequest.getJobTitle());
                }
                if (updateRequest.getCompanyName() != null && !updateRequest.getCompanyName().isBlank()) {
                    data.setCompanyName(updateRequest.getCompanyName());
                }
                if (!CollectionUtils.isEmpty(updateRequest.getCities())) {
                    updateRequest.getCities().forEach(looper->{
                        if (!data.getJobAddress().getCities().contains(looper)) {
                            data.getJobAddress().getCities().add(looper);
                        }
                    });
                }
                if (!CollectionUtils.isEmpty(updateRequest.getStates())) {
                    updateRequest.getStates().forEach(looper->{
                        if (!data.getJobAddress().getStates().contains(looper)) {
                            data.getJobAddress().getStates().add(looper);
                        }
                    });
                }
                if (!CollectionUtils.isEmpty(updateRequest.getCountries())) {
                    updateRequest.getCountries().forEach(looper->{
                        if (!data.getJobAddress().getCountries().contains(looper)) {
                            data.getJobAddress().getCountries().add(looper);
                        }
                    });
                }
                jobsAtomicReference.set(jobsRepository.save(data));
            },()->{
                try {
                    throw new Exception("Data Not Found!");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }else {
            try {
                throw new Exception("Please Provide Job Id!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }

        return jobsAtomicReference.get();
    }

    //SEARCH JOB
    public List<Jobs> searchJob(String searchRequest) {
        searchRequest = searchRequest.replaceAll("\\(","\\\\(");
        searchRequest = searchRequest.replaceAll("\\)","\\\\)");
        return jobsRepository.searchJob(searchRequest);
    }

    //FILTER JOB
    public List<Jobs> filterJob(JobFilterRequest filterRequest) {
        return null;
    }

    public Object toggleJobStatus(UUID jobId, boolean status) {
        AtomicReference<Jobs> jobsAtomicReference = new AtomicReference<>();
        jobsRepository.findById(jobId).ifPresentOrElse(data->{
            data.setActive(status);
            jobsAtomicReference.set(jobsRepository.save(data));
        },()->{
            try {
                throw new Exception("Data Not Found!");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return jobsAtomicReference.get();
    }

}
