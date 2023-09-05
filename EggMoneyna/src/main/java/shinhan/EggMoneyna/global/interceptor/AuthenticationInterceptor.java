package shinhan.EggMoneyna.global.interceptor;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import shinhan.EggMoneyna.global.error.ErrorCode;
import shinhan.EggMoneyna.global.error.exception.AuthenticationException;
import shinhan.EggMoneyna.global.jwt.constant.TokenType;
import shinhan.EggMoneyna.global.jwt.service.TokenManager;
import shinhan.EggMoneyna.global.util.AuthorizationHeaderUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {

    private final TokenManager tokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 1. Authorization Header 검증
        String authorizationHeader = request.getHeader("Authorization");
        AuthorizationHeaderUtils.validateAuthorization(authorizationHeader);

        // 2. 토큰 검증
        String token = authorizationHeader.split(" ")[1];
        tokenManager.validateToken(token);

        // 3. 토큰 타입 (AccessToken 일 때만 통과)
        Claims tokenClaims = tokenManager.getTokenClaims(token);
        String tokenType = tokenClaims.getSubject();
        if(!TokenType.isAccessToken(tokenType)) {
            throw new AuthenticationException(ErrorCode.NOT_ACCESS_TOKEN_TYPE);
        }

        return true;
    }

}
