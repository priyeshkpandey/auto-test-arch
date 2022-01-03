package com.mock.app.controller;

import com.mock.app.model.User;
import com.mock.app.model.requests.UserLoginInRequest;
import com.mock.app.model.requests.UserSignUpRequest;
import com.mock.app.model.responses.UserLoginResponse;
import com.mock.app.model.responses.UserSignUpResponse;
import com.mock.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.util.constant.Endpoint.PathVariable.USER_ID;
import static com.util.constant.Endpoint.UserEndpoint.*;

@RestController(USER_ROOT)
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST, value = USER_SIGNUP)
    public @ResponseBody UserSignUpResponse signUp(final @RequestBody UserSignUpRequest userSignUpRequest) {
        return this.userService.signUpUser(userSignUpRequest);
    }

    @RequestMapping(method = RequestMethod.POST, value = USER_LOGIN)
    public @ResponseBody UserLoginResponse logInUser(final @RequestBody UserLoginInRequest userLoginInRequest) {
        return this.userService.logInUser(userLoginInRequest);
    }

    @RequestMapping(method = RequestMethod.POST, value = USER_LOGOUT)
    public void logOutUser(final @PathVariable(USER_ID) Long userId) {
        final User user = new User();
        user.setId(userId);
        this.userService.logOutUser(user);
    }
}
