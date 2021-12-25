package com.mock.app.service.impl;

import com.mock.app.model.Permission;
import com.mock.app.model.User;
import com.mock.app.model.entities.UserTable;
import com.mock.app.model.requests.UserLoginInRequest;
import com.mock.app.model.requests.UserSignUpRequest;
import com.mock.app.model.responses.UserLoginResponse;
import com.mock.app.model.responses.UserSignUpResponse;
import com.mock.app.repositories.UserRepository;
import com.mock.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserSignUpResponse signUpUser(UserSignUpRequest userSignUpRequest) {
        final UserTable user = new UserTable();
        user.setId(userSignUpRequest.getId());
        user.setUserName(userSignUpRequest.getUserName());
        user.setPassword(userSignUpRequest.getPassword());
        user.setFirstName(userSignUpRequest.getFirstName());
        user.setLastName(userSignUpRequest.getLastName());
        user.setRoleName("customer");
        final UserTable createdUser = this.userRepository.saveAndFlush(user);
        final UserSignUpResponse userSignUpResponse = new UserSignUpResponse();
        userSignUpResponse.setUserId(createdUser.getId());
        final List<Permission> userPermissions = new ArrayList<>();
        for (String permission : createdUser.getPermissionName().split(",")) {
            final Permission userPermission = new Permission();
            userPermission.setName(permission);
            userPermissions.add(userPermission);
        }
        userSignUpResponse.setPermissions(userPermissions);
        userSignUpResponse.setRoles(Arrays.asList(createdUser.getRoleName().split(",")));
        return userSignUpResponse;
    }

    @Override
    public UserLoginResponse logInUser(UserLoginInRequest userLoginInRequest) {
        final UserTable user = this.userRepository.findUserTableByUserName(userLoginInRequest.getUsername());
        final UserLoginResponse userLoginResponse = new UserLoginResponse();
        userLoginResponse.setUserId(user.getId());
        userLoginResponse.setIsLoginSuccess(user.getPassword().equals(userLoginInRequest.getPassword()));
        return userLoginResponse;
    }

    @Override
    public void logOutUser(User user) {
        // DO NOTHING
    }

}
