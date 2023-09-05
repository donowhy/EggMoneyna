package shinhan.EggMoneyna.api;

import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shinhan.EggMoneyna.api.token.dto.AccessTokenResponseDto;
import shinhan.EggMoneyna.api.token.service.TokenService;
import shinhan.EggMoneyna.domain.member.entity.Member;
import shinhan.EggMoneyna.domain.member.service.MemberService;
import shinhan.EggMoneyna.global.jwt.dto.JwtTokenDto;
import shinhan.EggMoneyna.global.jwt.service.TokenManager;
import shinhan.EggMoneyna.global.util.AuthorizationHeaderUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;
    private final TokenManager tokenManager;
    private final TokenService tokenService;


    @PostMapping("/v1/member/save")
    public Member save (@RequestBody Member member){
        return memberService.registerMember(member);
    }

    @PostMapping("/v1/member/login")
    public JwtTokenDto login (HttpServletRequest httpServletRequest){
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        AuthorizationHeaderUtils.validateAuthorization(authorizationHeader);

        String substring = authorizationHeader.substring(7);
        Claims tokenClaims = tokenManager.getTokenClaims(substring);
        String id = tokenClaims.getId();
        log.info("String id = {}", id);
        return memberService.login(Long.valueOf(id));
    }

}
