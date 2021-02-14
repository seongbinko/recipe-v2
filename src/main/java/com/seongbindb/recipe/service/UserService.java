package com.seongbindb.recipe.service;

import com.seongbindb.recipe.vo.User;

public interface UserService {

    User searchUserReturnUser(User userVO) throws Exception;
}
