package com.brownfield.app.service;

import com.brownfield.app.entity.User;
import com.brownfield.app.request.UserRequest;

public interface UserService {

    User saveUserService(UserRequest userDto);

}
