package com.seongbindb.recipe.dto;

import com.seongbindb.recipe.vo.User;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@Getter
public class UserAccountDto extends org.springframework.security.core.userdetails.User {

    private User user;

    public UserAccountDto(User user) {
        super(user.getNickname(), user.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
        this.user = user;
    }
}
