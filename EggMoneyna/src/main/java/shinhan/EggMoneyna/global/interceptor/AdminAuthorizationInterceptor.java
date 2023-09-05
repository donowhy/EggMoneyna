package shinhan.EggMoneyna.global.interceptor;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import shinhan.EggMoneyna.domain.member.constant.Role;
import shinhan.EggMoneyna.global.error.ErrorCode;
import shinhan.EggMoneyna.global.error.exception.AuthenticationException;
import shinhan.EggMoneyna.global.jwt.service.TokenManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class AdminAuthorizationInterceptor implements HandlerInterceptor {

    private final TokenManager tokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String authorizationHeader = request.getHeader("Authorization");
        String accessToken = authorizationHeader.split(" ")[1];

        // 이전 인터셉터(AuthenticationInterceptor) 에서 토큰 검증은 했으므로 Role 이 ADMIN 인지만 확인

        Claims tokenClaims = tokenManager.getTokenClaims(accessToken);
        String role = (String)tokenClaims.get("role");
        if(!Role.ADMIN.equals(Role.valueOf(role))) {
            throw new AuthenticationException(ErrorCode.FORBIDDEN_ADMIN);
        }

        return true;
    }

}
