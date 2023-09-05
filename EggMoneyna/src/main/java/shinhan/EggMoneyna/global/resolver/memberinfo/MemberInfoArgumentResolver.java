package shinhan.EggMoneyna.global.resolver.memberinfo;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import shinhan.EggMoneyna.domain.member.constant.Role;
import shinhan.EggMoneyna.global.jwt.service.TokenManager;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class MemberInfoArgumentResolver implements HandlerMethodArgumentResolver {

    private final TokenManager tokenManager;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasMemberInfoAnnotation = parameter.hasParameterAnnotation(MemberInfo.class);
        boolean hasMemberInfoDto = MemberInfoDto.class.isAssignableFrom(parameter.getParameterType());
        return hasMemberInfoAnnotation && hasMemberInfoDto;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String authorizationHeader = request.getHeader("Authorization");
        String token = authorizationHeader.split(" ")[1];

        Claims tokenClaims = tokenManager.getTokenClaims(token);
        Long memberId = Long.valueOf( (Integer) tokenClaims.get("memberId"));
        String role = (String) tokenClaims.get("role");

        return MemberInfoDto.builder()
                .memberId(memberId)
                .role(Role.from(role))
                .build();
    }

}
