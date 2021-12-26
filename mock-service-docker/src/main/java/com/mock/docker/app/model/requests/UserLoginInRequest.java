package com.mock.docker.app.model.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserLoginInRequest {
    private String username;
    private String password;
}
