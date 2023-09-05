package shinhan.EggMoneyna.global.util;

import org.springframework.util.StringUtils;
import shinhan.EggMoneyna.global.error.ErrorCode;
import shinhan.EggMoneyna.global.error.exception.AuthenticationException;
import shinhan.EggMoneyna.global.jwt.constant.GrantType;

public class AuthorizationHeaderUtils {

    public static void validateAuthorization(String authorizationHeader) {

        // 1. authorizationHeader 필수 체크
        if(!StringUtils.hasText(authorizationHeader)) {
            throw new AuthenticationException(ErrorCode.NOT_EXISTS_AUTHORIZATION);
        }

        // 2. authorizationHeader Bearer 체크
        String[] authorizations = authorizationHeader.split(" ");
        if(authorizations.length < 2 || (!GrantType.BEARER.getType().equals(authorizations[0]))) {
            throw new AuthenticationException(ErrorCode.NOT_VALID_BEARER_GRANT_TYPE);
        }
    }

}
