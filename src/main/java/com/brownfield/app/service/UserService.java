package com.brownfield.app.service;

import com.brownfield.app.entity.User;
import com.brownfield.app.request.UserRegRequest;

public interface UserService {

    User saveUserService(UserRegRequest userDto);

}
