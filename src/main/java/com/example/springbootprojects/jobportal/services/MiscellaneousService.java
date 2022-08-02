package com.example.springbootprojects.jobportal.services;

import com.example.springbootprojects.jobportal.models.User;
import com.example.springbootprojects.jobportal.models.commons.Response;
import com.example.springbootprojects.jobportal.models.login.DeviceInfo;
import com.example.springbootprojects.jobportal.models.requests.LoginRequest;
import com.example.springbootprojects.jobportal.models.requests.ValidateOtpRequest;
import com.example.springbootprojects.jobportal.repository.JobsRepository;
import com.example.springbootprojects.jobportal.repository.UserRepository;
import com.example.springbootprojects.jobportal.utils.AuthenticationUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class MiscellaneousService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobsRepository jobsRepository;

    @Autowired
    private AuthenticationUtil authenticationUtil;

    public String login(LoginRequest loginRequest) throws Exception {
        Optional<User> optionalUser = userRepository.findByMobile(loginRequest.getMobile());
        if (optionalUser != null) {
            authenticationUtil.generateOtp(optionalUser.get());
            return "OTP Sent Successfully";
        }
        throw new Exception("User Not Found!");
    }

    public String verifyOtp(ValidateOtpRequest otpRequest) {
        Optional<User> optionalUser = userRepository.findByMobile(otpRequest.getMobile());
        if (optionalUser.isPresent()) {
           return authenticationUtil.validateOtp(otpRequest,optionalUser.get());
        }
        return "Error in Validating OTP";
    }

    public String logout(UUID userId, DeviceInfo deviceInfo) {
        AtomicReference<User> userAtomicReference = new AtomicReference<>();
        userRepository.findById(userId).ifPresentOrElse(data->{
            data.removeDeviceInfo(deviceInfo);
            userAtomicReference.set(userRepository.save(data));
        },()->{
            try {
                throw new Exception("Logout Failed!");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return "Logout Successful";
    }
}
