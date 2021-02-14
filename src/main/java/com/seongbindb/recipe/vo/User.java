package com.seongbindb.recipe.vo;

import lombok.*;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "password")
public class User {

    private String id;
    private String password;
    private String nickname;
    private Date createDate;
    private Date modDate;
    private String userEmail;
    private String role;
}
