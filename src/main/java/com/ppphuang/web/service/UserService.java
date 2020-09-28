package com.ppphuang.web.service;

import com.ppphuang.web.beans.User;

public interface UserService {
    User checkUser(String username, String password);
}
