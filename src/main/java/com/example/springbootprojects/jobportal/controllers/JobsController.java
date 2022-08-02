package com.example.springbootprojects.jobportal.controllers;

import com.example.springbootprojects.jobportal.models.Jobs;
import com.example.springbootprojects.jobportal.models.commons.Response;
import com.example.springbootprojects.jobportal.models.requests.JobFilterRequest;
import com.example.springbootprojects.jobportal.models.requests.JobUpdateRequest;
import com.example.springbootprojects.jobportal.services.JobsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.UUID;

@RestController
@RequestMapping("/api/jobs")
public class JobsController {

    @Autowired
    private JobsService jobsService;

    @ApiOperation("Create Jobs")
    @PostMapping(value = "",produces = MediaType.APPLICATION_JSON_VALUE)
    public Response createJob(@RequestBody Jobs createRequest) {
        return new Response(jobsService.createJob(createRequest));
    }

    @ApiOperation("Get all Jobs")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getAllJobs(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "10") int pageSize){
        return new Response(jobsService.getAllJobs(pageNumber,pageSize));
    }

    @ApiOperation("Get Job by Id")
    @GetMapping(value = "/{jobId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getJobById(@PathVariable UUID jobId){
        return new Response(jobsService.getJobById(jobId));
    }

    @ApiOperation("Delete Job by Id")
    @DeleteMapping(value = "/{jobId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response deleteJobById(@PathVariable UUID jobId){
        return new Response(jobsService.deleteJobById(jobId));
    }

    @ApiOperation("Update Job Basic Details")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response updateJob(@RequestBody JobUpdateRequest updateRequest){
        return new Response(jobsService.updateJob(updateRequest));
    }

    @ApiOperation("Search Jobs by Company Name & Job Role")
    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response searchJob(@RequestParam String searchRequest) {
        return new Response(jobsService.searchJob(searchRequest));
    }

    @ApiIgnore
    @ApiOperation("Filter Jobs")
    @PutMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response filterJob(@RequestBody JobFilterRequest filterRequest){
        return new Response(jobsService.filterJob(filterRequest));
    }

    @ApiOperation("Toggle Job Status - (Active/Inactive)")
    @PutMapping(value = "/status/toggle", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response toggleJobStatus(@RequestParam UUID jobId, @RequestParam boolean status) {
        return new Response(jobsService.toggleJobStatus(jobId,status));
    }
}
