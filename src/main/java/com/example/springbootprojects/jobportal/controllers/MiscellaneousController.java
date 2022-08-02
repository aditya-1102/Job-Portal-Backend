package com.example.springbootprojects.jobportal.controllers;

import com.example.springbootprojects.jobportal.models.commons.Response;
import com.example.springbootprojects.jobportal.models.login.DeviceInfo;
import com.example.springbootprojects.jobportal.models.requests.LoginRequest;
import com.example.springbootprojects.jobportal.models.requests.ValidateOtpRequest;
import com.example.springbootprojects.jobportal.services.MiscellaneousService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RestController
@RequestMapping("/api/miscellaneous")
public class MiscellaneousController {

    @Autowired
    private MiscellaneousService miscellaneousService;

    @ApiOperation("User login")
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response login(@RequestBody LoginRequest loginRequest) throws Exception {
        return new Response(miscellaneousService.login(loginRequest));
    }

    @ApiOperation("Validate OTP")
    @PostMapping(path = "/otp/validate", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response verifyOtp(@RequestBody ValidateOtpRequest otpRequest) {
        return new Response(miscellaneousService.verifyOtp(otpRequest));
    }

    @ApiOperation("User Logout")
    @PutMapping(value = "/{userId}/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response logout(@PathVariable UUID userId, @RequestBody DeviceInfo deviceInfo){
        return new Response(miscellaneousService.logout(userId, deviceInfo));
    }
}
