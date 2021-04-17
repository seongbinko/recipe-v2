package com.seongbindb.recipe.mapper;

import com.seongbindb.recipe.vo.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
@Transactional(readOnly = true) // Todo list
public interface UserMapper {

    boolean existByEmail(String email);

    boolean existByNickName(String nickname);

    void insertUser(User user);

    User findByEmail(String email);

    int countAllUser();

    void updateEmailVerified(User user);

    User findByNickname(String nickname);

    void updateUserProfile(User user);

    void updateUserPassword(User user);

    void updateUserNickname(User user);

    void updateEmailCheckTokenWithTime(User user);

    void deleteByNickname(String nickname);
}
