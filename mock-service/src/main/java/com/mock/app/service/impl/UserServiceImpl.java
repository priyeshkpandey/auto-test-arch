package com.mock.app.service.impl;

import com.mock.app.model.Permission;
import com.mock.app.model.Role;
import com.mock.app.model.User;
import com.mock.app.model.requests.UserLoginInRequest;
import com.mock.app.model.requests.UserSignUpRequest;
import com.mock.app.model.responses.UserLoginResponse;
import com.mock.app.model.responses.UserSignUpResponse;
import com.mock.app.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {
    @Override
    public UserSignUpResponse signUpUser(UserSignUpRequest userSignUpRequest) {
        return null;
    }

    @Override
    public UserLoginResponse logInUser(UserLoginInRequest userLoginInRequest) {
        return null;
    }

    @Override
    public void logOutUser(User user) {

    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public void assignUserPermissions(User user, List<Permission> permissions) {

    }

    @Override
    public void assignUserRoles(User user, List<Role> roles) {

    }

    @Override
    public void deleteUser(User user) {

    }
}
