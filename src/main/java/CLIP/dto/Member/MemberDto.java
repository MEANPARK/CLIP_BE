package CLIP.dto.Member;

import CLIP.Entity.member.Member;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {

    private Long id;
    private String username;
    //private String password;
    private String phone;
    private String profileImg;
    private String nickname;
    private int userLevel;
    private int userEXP;
    private double latitude;
    private double longitude;

    static public MemberDto toDto(Member member) {
        return MemberDto.builder()
                .id(member.getId())
                .username(member.getUsername())
                .phone(member.getPhone())
                .profileImg(member.getProfileImg())
                .nickname(member.getNickname())
                .userLevel(member.getUserLevel())
                .userEXP(member.getUserEXP())
                .latitude(member.getLatitude())
                .longitude(member.getLongitude())
                .build();
    }

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .username(username)
                .phone(phone)
                .profileImg(profileImg)
                .nickname(nickname)
                .userLevel(userLevel)
                .userEXP(userEXP)
                .latitude(latitude)
                .longitude(longitude)
                .build();
    }
}
