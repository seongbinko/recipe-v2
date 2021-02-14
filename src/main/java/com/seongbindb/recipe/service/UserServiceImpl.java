package com.seongbindb.recipe.service;


import com.seongbindb.recipe.mapper.UserMapper;
import com.seongbindb.recipe.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User searchUserReturnUser(User userVO) throws Exception {
        User user = userMapper.searchUserReturnUser(userVO);

        return user;
    }
}



	

