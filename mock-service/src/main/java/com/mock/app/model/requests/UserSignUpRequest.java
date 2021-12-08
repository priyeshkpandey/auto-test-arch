package com.mock.app.model.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserSignUpRequest {
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
}
