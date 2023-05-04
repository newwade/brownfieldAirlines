package com.brownfield.app.service;


import com.brownfield.app.constant.Role;
import com.brownfield.app.entity.User;
import com.brownfield.app.exception.BadRequestException;
import com.brownfield.app.exception.RecordNotFoundException;
import com.brownfield.app.model.request.LoginRequest;
import com.brownfield.app.model.request.UserRegRequest;
import com.brownfield.app.model.response.LoginResponse;
import com.brownfield.app.model.response.UserDetailResponse;
import com.brownfield.app.repository.UserRepository;
import com.brownfield.app.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
            Optional<User> user=userRepository.findByEmailAddress(loginRequest.getUsername());
            String token = jwtUtil.generateToken(user.get());
            logger.info("loginUser method invoked");
            return LoginResponse.builder().token(token).build();
        }
        catch (DisabledException e) {
            throw new BadRequestException("invalid username or password");
        } catch (BadCredentialsException e) {
            throw new BadRequestException("invalid username or password");
        }
    }

    @Override
    public User saveUserService(UserRegRequest userDto) {
        User user = new User();
        Optional<User> user_db1 =userRepository.findByEmailAddress(userDto.getEmailAddress());
        if(user_db1.isPresent()) {
            throw new BadRequestException("username or email already exists");
        }
        Optional<User> user_db2 =userRepository.findByMobileNumber(userDto.getMobileNumber());
        if(user_db2.isPresent()) {
            throw new BadRequestException("invalid phone");
        }
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmailAddress(userDto.getEmailAddress());
        user.setMobileNumber(userDto.getMobileNumber());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(Role.USER);
        logger.info("saveUserService method invoked");
        return userRepository.save(user);
    }

    @Override
    public User findById(long id) {
        Optional<User> user=userRepository.findById(id);
        if(user.isEmpty()){
            throw new RecordNotFoundException("Record not found for user : " + id);
        }
        logger.info("findById method invoked");
        return user.get();
    }

    @Override
    public UserDetailResponse getUserFromToken() {
        logger.info("getUserFromToken method invoked");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        return UserDetailResponse.builder().id(user.getId()).firstName(user.getFirstName()).lastName(user.getLastName())
                .emailAddress(user.getEmailAddress()).mobileNumber(user.getMobileNumber()).build();
    }


}
