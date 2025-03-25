package CLIP.controller.Member;

import CLIP.dto.JWT.JwtToken;
import CLIP.dto.Member.SignInDto;
import CLIP.dto.Member.SignUpDto;
import CLIP.response.Response;
import CLIP.service.AwsS3.AwsS3Service;
import CLIP.service.Member.MemberServiceImpl;
import CLIP.service.Member.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberServiceImpl memberService;
    private final AwsS3Service awsS3Service;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    public Response getMembers() {
        return Response.success(memberService.getMembers());
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/sign-in")
    public Response signIn(@RequestBody SignInDto signInDto) {
        String username = signInDto.getUsername();
        String password = signInDto.getPassword();
        JwtToken jwtToken = memberService.signIn(username, password);
        log.info("request username = {}, password = {}", username, password);
        log.info("jwtToken accessToken = {}, refreshToken = {}", jwtToken.getAccessToken(), jwtToken.getRefreshToken());
        return Response.success(jwtToken);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/sign-up")
    public Response signUp(@RequestBody SignUpDto signUpDto) {
        String result = memberService.signUp(signUpDto);
        return Response.success();
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/profileImg")
    public Response updateProfileImg(@RequestPart("profileImg") List<MultipartFile> profileImg){
        String username = SecurityUtil.getCurrentUsername();
        String profileImgUrl = awsS3Service.uploadFile(profileImg).getFirst();
        memberService.updateProfileImg(username, profileImgUrl);
        return Response.success(profileImgUrl);
    }

    // /members/{username} : username(email)이 현재 db에 존재하는지 확인 후 => 있다 : 1 / 없다 : 0 반환
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{username}")
    public Response getUsername(@PathVariable("username") String username) {
        return Response.success(memberService.getUsername(username));
    }

    //현재 사용자의 정보를 반환
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    public Response getMemberInfo(){
        String username = SecurityUtil.getCurrentUsername();
        return Response.success(memberService.getMemberInfo(username));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/")
    public Response deleteMember() {
        String username = SecurityUtil.getCurrentUsername();
        memberService.deleteMember(username);
        return Response.success();
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/exp/{addExp}")
    public Response updateExp(@PathVariable("addExp") int exp) {
        String username = SecurityUtil.getCurrentUsername();
        return Response.success(memberService.updateExp(username, exp));
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/level/{addLevel}")
    public Response updateLevel(@PathVariable("addLevel") int level) {
        String username = SecurityUtil.getCurrentUsername();
        return Response.success(memberService.updateLevel(username, level));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/trade-history")
    public Response getTradeHistory() {
        String username = SecurityUtil.getCurrentUsername();
        return Response.success(memberService.getTradeHistory(username));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/liked-items")
    public Response getLikeActions() {
        String username = SecurityUtil.getCurrentUsername();
        return Response.success(memberService.getLikeActions(username));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/received-liked-items")
    public Response getReceivedLikeActions() {
        String username = SecurityUtil.getCurrentUsername();
        return Response.success(memberService.getReceivedLikeActions(username));
    }


    //username 반환하는 값
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/test")
    public Response test() {
        return Response.success(SecurityUtil.getCurrentUsername());
    }
}
