package com.mock.app.service;

import com.mock.app.model.Permission;
import com.mock.app.model.Role;
import com.mock.app.model.User;
import com.mock.app.model.requests.UserLoginInRequest;
import com.mock.app.model.requests.UserSignUpRequest;
import com.mock.app.model.responses.UserLoginResponse;
import com.mock.app.model.responses.UserSignUpResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public UserSignUpResponse signUpUser(final UserSignUpRequest userSignUpRequest);
    public UserLoginResponse logInUser(final UserLoginInRequest userLoginInRequest);
    public void logOutUser(final User user);
}
