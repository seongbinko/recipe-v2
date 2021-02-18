package com.seongbindb.recipe.mapper;

import com.seongbindb.recipe.vo.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true) // Todo list
public interface UserMapper {

    boolean existByEmail(String email);

    boolean existByNickName(String nickname);

    void insertUser(User user);

    User findByEmail(String s);

    int countAllUser();

    void updateEmailVerified(User user);
}
