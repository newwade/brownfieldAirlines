package com.brownfield.app.service;

import com.brownfield.app.entity.User;
import com.brownfield.app.model.request.LoginRequest;
import com.brownfield.app.model.request.UserRegRequest;
import com.brownfield.app.model.response.LoginResponse;
import com.brownfield.app.model.response.UserDetailResponse;

public interface UserService {
    User saveUserService(UserRegRequest userDto);
    LoginResponse loginUser(LoginRequest loginRequest);
    User findById(long id);
    UserDetailResponse getUserFromToken();
}
