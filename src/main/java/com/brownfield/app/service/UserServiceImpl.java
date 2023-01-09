package com.brownfield.app.service;


import com.brownfield.app.entity.User;
import com.brownfield.app.model.request.UserRegRequest;
import com.brownfield.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUserService(UserRegRequest userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         Optional<User> user=userRepository.findByEmail(username);
         if(user.isEmpty()){
             throw new UsernameNotFoundException(username);
         }
        return new org.springframework.security.core.userdetails.User(user.get().getEmail(),user.get().getPassword(),new ArrayList<>());    }
}
