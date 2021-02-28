package com.seongbindb.recipe.form;

import com.seongbindb.recipe.vo.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

@Data
//@NoArgsConstructor
public class UserProfileForm {

    @Length(max = 35)
    private String bio;

    @Length(max = 50)
    @URL
    private String url;

    @Length(max = 30)
    private String occupation;

    @Length(max = 30)
    private String location;

    private String userImage;


    // public UserProfileForm() { } // @NoArgsConstructor와 동일 , 없으면 UserProfileForm을 생성할때 User를 받을 수 없기에 NullpointException이 난다 User가 없기때문

/*
    public UserProfileForm(User user) {
        this.bio = user.getBio();
        this.url = user.getUrl();
        this.occupation = user.getOccupation();
        this.location = user.getLocation();
        this.userImage = user.getUserImage();
    }
*/
}
