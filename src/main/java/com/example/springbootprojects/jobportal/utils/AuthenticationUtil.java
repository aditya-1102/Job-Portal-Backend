package com.example.springbootprojects.jobportal.utils;

import com.example.springbootprojects.jobportal.models.User;
import com.example.springbootprojects.jobportal.models.login.OTPData;
import com.example.springbootprojects.jobportal.models.requests.ValidateOtpRequest;
import com.example.springbootprojects.jobportal.repository.OTPRepository;
import com.example.springbootprojects.jobportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class AuthenticationUtil {

    @Autowired
    private OTPRepository otpRepository;

    @Autowired
    private UserRepository userRepository;

    //GENERATE OTP FOR USER
    public OTPData generateOtp(User user) {
        Optional<OTPData> optionalOTPData = otpRepository.findById(user.getUserId());
        OTPData otpData = null;
        if (!optionalOTPData.isPresent()) {
            otpData = new OTPData(user.getUserId());
        }else {
            otpData = optionalOTPData.get();
        }
        otpRepository.save(otpData);
        return otpData;
    }

    //VALIDATE OTP FPR USER
    public String validateOtp(ValidateOtpRequest otpRequest, User user) {
        AtomicReference<User> userAtomicReference =  new AtomicReference<>();
        Optional<OTPData> optionalOTPData = otpRepository.findById(user.getUserId());
        if (optionalOTPData.isPresent()) {
            if (optionalOTPData.get().getOtp() == otpRequest.getOtp()) {
                user.setVerified(true);
                user.addDeviceInfo(otpRequest.getDeviceInfo());
                otpRepository.deleteById(user.getUserId());
                userAtomicReference.set(userRepository.save(user));
                return "OTP Verified, Login Successful";
            } else {
                try {
                    throw new Exception("Invalid OTP");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }else {
            try {
                throw new Exception("OTP Not Found!");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
