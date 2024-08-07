package com.yalanin.springboot_mall.dao;

import com.yalanin.springboot_mall.dto.UserRegisterRequest;
import com.yalanin.springboot_mall.model.User;

public interface UserDao {
    Integer createUser(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);

    User getUserByEmail(String email);
}
