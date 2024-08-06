package com.yalanin.springboot_mall.dao.impl;

import com.yalanin.springboot_mall.dao.UserDao;
import com.yalanin.springboot_mall.dto.UserRegisterRequest;
import com.yalanin.springboot_mall.model.User;
import com.yalanin.springboot_mall.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createUser(UserRegisterRequest userRegisterRequest) {
        String sql = "INSERT INTO user(email, password, created_date, last_modified_date)" +
                " VALUES (:email, :password, :createDate, :lastModifiedDate)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Date now = new Date();

        Map<String, Object>  map = new HashMap<>();
        map.put("email", userRegisterRequest.getEmail());
        map.put("password", userRegisterRequest.getPassword());
        map.put("createDate", now);
        map.put("lastModifiedDate", now);

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public User getUserById(Integer userId) {
        String sql = "SELECT user_id, email, password, created_date, last_modified_date FROM user WHERE 1=1";
        sql = sql + " AND user_id = :userId";

        Map<String, Object>  map = new HashMap<>();
        map.put("userId", userId);

        List<User> list = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());

        if(list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }
}
