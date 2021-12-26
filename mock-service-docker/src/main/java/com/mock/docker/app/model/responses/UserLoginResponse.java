package com.mock.docker.app.model.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserLoginResponse {
    private Long userId;
    private Boolean isLoginSuccess;
}
