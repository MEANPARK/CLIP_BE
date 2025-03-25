package CLIP.dto.Member;

import CLIP.Entity.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ResponseMembers {
    List<ResponseMember> memberDtoList;

    @Builder
    public ResponseMembers(List<Member> memberList) {
        this.memberDtoList = memberList.stream()
                .map(member -> ResponseMember.builder()
                        .member(member)
                        .build())
                .collect(Collectors.toList());
    }
}
