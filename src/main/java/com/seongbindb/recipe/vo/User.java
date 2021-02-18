package com.seongbindb.recipe.vo;

import lombok.*;
import java.util.Date;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@ToString(exclude = "password")
public class User {

    private String id;
    private String password;
    private String nickname;
    // joinedAt 가입날짜
    private Date createDate;
    private Date modDate;
    private String email;
    private String role;

    private boolean emailVerified;
    private String emailCheckToken;
    private String profileImage;

    public void generateEmailCheckToken() {
        this.emailCheckToken = UUID.randomUUID().toString();
    }
}
