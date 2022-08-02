package com.example.springbootprojects.jobportal.services;

import com.example.springbootprojects.jobportal.constants.UserConstants;
import com.example.springbootprojects.jobportal.models.User;
import com.example.springbootprojects.jobportal.repository.UserRepository;
import com.example.springbootprojects.jobportal.utils.DbSequenceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DbSequenceHelper dbSequenceHelper;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(UUID userId) {
        return userRepository.findById(userId);
    }

    public User createUser(User createRequest) {
        //generating sequence value for user and userDisplayId
        int displayCount = dbSequenceHelper.getNextSequenceValue(UserConstants.DB_SEQUENCE_TYPE.USERS);
        String displayId = "USER"+ "-";
        if (displayCount < 100) {
            displayId += String.format("%03d", displayCount);
        } else {
            displayId += String.valueOf(displayCount);
        }
        createRequest.setUserDisplayId(displayId);

        //setting user type
        if (createRequest.isAdmin()) {
            createRequest.setUserType(UserConstants.USER_TYPE.ADMIN);
        }else {
            createRequest.setUserType(UserConstants.USER_TYPE.USER);
        }

        //making end date if 'isCurrentlyWorking' is true
        if (!CollectionUtils.isEmpty(createRequest.getWorkDetails())) {
            createRequest.getWorkDetails().forEach(data->{
                if (data.isCurrentlyWorking()) {
                    data.setEndDate(null);
                }
            });
        }

        createRequest.setUpdatedTimeStamp(new Date());

        return userRepository.save(createRequest);
    }

    public String deleteUserById(UUID userId) {
        userRepository.deleteById(userId);
        return "User Deleted Successfully";
    }

    public Object updateUser(User updateRequest) {
        return null;
    }

    public User toggleUserStatus(UUID userId, boolean status) {
        AtomicReference<User> userAtomicReference = new AtomicReference<>();
        userRepository.findById(userId).ifPresentOrElse(data->{
            data.setActive(status);
            userAtomicReference.set(userRepository.save(data));
        },()->{
            try {
                throw new Exception("User Not Found");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return userAtomicReference.get();
    }
}
