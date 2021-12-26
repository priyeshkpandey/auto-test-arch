package com.mock.docker.app.service;

import com.mock.docker.app.model.User;
import com.mock.docker.app.model.requests.UserLoginInRequest;
import com.mock.docker.app.model.requests.UserSignUpRequest;
import com.mock.docker.app.model.responses.UserLoginResponse;
import com.mock.docker.app.model.responses.UserSignUpResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public UserSignUpResponse signUpUser(final UserSignUpRequest userSignUpRequest);
    public UserLoginResponse logInUser(final UserLoginInRequest userLoginInRequest);
    public void logOutUser(final User user);
}
