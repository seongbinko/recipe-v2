package com.seongbindb.recipe.mapper;

import com.seongbindb.recipe.vo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    User getUserById(String id);

    User searchUserReturnUser(User userVO) throws Exception;

}
