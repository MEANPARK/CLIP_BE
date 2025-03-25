package CLIP.dto.Member;

import CLIP.Entity.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseMember {
    private Long id;
    private String username;
    private String password;
    private String phone;
    private String profileImg;
    private String nickname;
    private int userLevel;
    private int userEXP;
    private double latitude;
    private double longitude;

    @Builder
    public ResponseMember(Member member) {
        this.id = member.getId();
        this.username = member.getUsername();
        this.password = member.getPassword();
        this.phone = member.getPhone();
        this.profileImg = member.getProfileImg();
        this.nickname = member.getNickname();
        this.userLevel = member.getUserLevel();
        this.userEXP = member.getUserEXP();
        this.latitude = member.getLatitude();
        this.longitude = member.getLongitude();
    }
}
