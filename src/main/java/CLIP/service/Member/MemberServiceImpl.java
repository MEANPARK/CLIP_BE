package CLIP.service.Member;

import CLIP.Entity.member.Member;
import CLIP.config.JWT.JwtTokenProvider;
import CLIP.dto.Items.ResponseLikeActions;
import CLIP.dto.Items.ResponseUserTradeHistorys;
import CLIP.dto.JWT.JwtToken;
import CLIP.dto.Member.MemberDto;
import CLIP.dto.Member.ResponseMembers;
import CLIP.dto.Member.SignUpDto;
import CLIP.repository.Items.ItemsRepository;
import CLIP.repository.Items.LikeActionRepository;
import CLIP.repository.Items.UserTradeHistoryRepository;
import CLIP.repository.Member.MemberRepository;
import CLIP.service.AwsS3.AwsS3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberServiceImpl {
    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final ItemsRepository itemsRepository;
    private final UserTradeHistoryRepository userTradeHistoryRepository;
    private final LikeActionRepository likeActionRepository;
    private final AwsS3Service awsS3Service;

    @Transactional
    public JwtToken signIn(String username, String password) {
        // 1. username + password 를 기반으로 Authentication 객체 생성
        // 이때 authentication 은 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        // 2. 실제 검증. authenticate() 메서드를 통해 요청된 Member 에 대한 검증 진행
        // authenticate 메서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        JwtToken jwtToken = jwtTokenProvider.generateToken(authentication);

        return jwtToken;
    }

    @Transactional
    public String signUp(SignUpDto signUpDto) {
        if (memberRepository.existsByUsername(signUpDto.getUsername())) {
            throw new IllegalArgumentException("이미 사용 중인 사용자 이름입니다.");
        }
        // Password 암호화
        String encodedPassword = passwordEncoder.encode(signUpDto.getPassword());
        List<String> roles = new ArrayList<>();
        roles.add("USER");  // USER 권한 부여
        memberRepository.save(signUpDto.toEntity(encodedPassword, roles));
        return "success";
    }

    @Transactional
    public void updateProfileImg(String username, String profileImgUrl) {
        String preProfileImgUrl = memberRepository.findProfileImgByUsername(username);
        if(preProfileImgUrl != null) {
            String preProfileImg = preProfileImgUrl.substring(preProfileImgUrl.lastIndexOf("/") + 1);
            awsS3Service.deleteFile(preProfileImg);
        }
        memberRepository.updateProfileImg(username, profileImgUrl);
    }

    @Transactional
    public int getUsername(String username) {
        if (memberRepository.existsByUsername(username)) {
            return 1;
        }
        return 0;
    }

    @Transactional
    public ResponseMembers getMembers() {
        return new ResponseMembers(memberRepository.findAll());
    }

    @Transactional
    public MemberDto getMemberInfo(String username) {
        Member member = memberRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("no such data"));
        MemberDto memberDto = MemberDto.toDto(member);
        return memberDto;
    }

    //지금은 현재 Member테이블에서만 삭제 => 유저 정보만 삭제
    @Transactional
    public void deleteMember(String username){
        memberRepository.deleteByUsername(username);
        itemsRepository.deleteByItemUpLoaderUuid(username);
    }

    @Transactional
    public int updateExp(String username, int exp) {
        int userExp = memberRepository.findUserEXPByUsername(username) + exp;
        if(userExp >= 100) {
            int level = userExp / 100;
            updateLevel(username, level);
            userExp = userExp - level * 100;
        }
        return memberRepository.updateExp(username, userExp);
    }

    @Transactional
    public int updateLevel(String username, int level) {
        return memberRepository.updateLevel(username, level);
    }

    @Transactional
    public ResponseUserTradeHistorys getTradeHistory(String username) {
        return new ResponseUserTradeHistorys(userTradeHistoryRepository.findByUserID1OrUserID2(username, username));
    }

    @Transactional
    public ResponseLikeActions getLikeActions(String username) {
        return new ResponseLikeActions(likeActionRepository.findByLikeSenderUUID(username));
    }

    @Transactional
    public ResponseLikeActions getReceivedLikeActions(String username) {
        return new ResponseLikeActions(likeActionRepository.findByLikeReceiverUUID(username));
    }
}
