package shinhan.EggMoneyna.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthorizationArgumentResolver implements HandlerMethodArgumentResolver {

    private final JwtProvider jwtProvider;

    // @JwtAuthorization 어노테이션이 있을 경우 동작
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(UserInfo.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        log.info("JwtAuthorizationArgumentResolver 동작!!");

        HttpServletRequest httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);

        // 헤더 값 체크
        if (httpServletRequest != null) {
            String token = httpServletRequest.getHeader("Authorization");

            if (token != null && !token.trim().equals("")) {
                // 토큰 있을 경우 검증
                if (jwtProvider.validateToken(token)) {
                    // 검증 후 MemberInfo 리턴
                    return jwtProvider.getClaim(token);
                }
            }

            // 토큰은 없지만 필수가 아닌 경우 체크
            UserInfo annotation = parameter.getParameterAnnotation(UserInfo.class);
            if (annotation != null && !annotation.required()) {
                // 필수가 아닌 경우 기본 객체 리턴
                return new UsersInfo();
            }
        }

        // 토큰 값이 없으면 에러
        throw new RuntimeException("권한 없음.");
    }
}
