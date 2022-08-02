package com.example.springbootprojects.jobportal.controllers;

import com.example.springbootprojects.jobportal.models.User;
import com.example.springbootprojects.jobportal.models.commons.Response;
import com.example.springbootprojects.jobportal.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.MediaPrintableArea;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("Get All Users")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getAllUsers() {
        return new Response(userService.getAllUsers());
    }

    @ApiOperation("Get User By Id")
    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getUserById(@PathVariable UUID userId) {
        return new Response(userService.getUserById(userId));
    }

    @ApiOperation("Create User")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response createUser(@RequestBody User createRequest) {
        return new Response(userService.createUser(createRequest));
    }

    @ApiOperation("Delete User")
    @DeleteMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response deleteUserById(@PathVariable UUID userId) {
        return new Response(userService.deleteUserById(userId));
    }

    @ApiOperation("Update User")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response updateUser(@RequestBody User updateRequest) {
        return new Response(userService.updateUser(updateRequest));
    }

    @ApiOperation("Toggle User Status - (Active/Inactive)")
    @PutMapping(value = "/status/toggle", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response toggleUserStatus(@RequestParam UUID userId, @RequestParam boolean status) {
        return new Response(userService.toggleUserStatus(userId, status));
    }
}
