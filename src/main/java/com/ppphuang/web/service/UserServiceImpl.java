package com.ppphuang.web.service;

import com.ppphuang.web.beans.User;
import com.ppphuang.web.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User checkUser(String username, String password) {
        User byUsernameAndPassword = userRepository.findByUsernameAndPassword(username, password);
        return byUsernameAndPassword;
    }
}
