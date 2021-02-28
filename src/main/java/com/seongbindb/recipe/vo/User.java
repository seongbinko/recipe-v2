package com.seongbindb.recipe.vo;

import lombok.*;

import java.time.LocalDateTime;
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
    private LocalDateTime createDate;
    private LocalDateTime modDate;
    private String email;
    private String role;

    private String bio;
    private String url;
    private String occupation;
    private String location;
    private String userImage;

    private boolean emailVerified;
    private String emailCheckToken;

    private LocalDateTime emailCheckTokenGeneratedAt;

    public void generateEmailCheckToken() {
        this.emailCheckToken = UUID.randomUUID().toString();
        this.emailCheckTokenGeneratedAt = LocalDateTime.now();
    }

    public boolean isValidToken(String token) {
        return this.emailCheckToken.equals(token);
    }

    public boolean canSendConfirmEmail() {
        return this.emailCheckTokenGeneratedAt.isBefore(LocalDateTime.now().minusHours(1));
    }
}
