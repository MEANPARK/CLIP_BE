package CLIP.dto.Member;

import CLIP.Entity.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpDto {
    private String username;
    private String password;
    private String phone;
    private String profileImg;
    private String nickname;
    private int userLevel;
    private int userEXP;

    private List<String> roles = new ArrayList<>();

    public Member toEntity(String encodedPassword, List<String> roles) {
        return Member.builder()
                .username(username)
                .password(encodedPassword)
                .phone(phone)
                .profileImg(profileImg)
                .nickname(nickname)
                .userLevel(4)
                .userEXP(0)
                .roles(roles)
                .build();
    }
}
