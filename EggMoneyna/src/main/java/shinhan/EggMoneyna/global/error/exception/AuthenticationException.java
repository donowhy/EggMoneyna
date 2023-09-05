package shinhan.EggMoneyna.global.error.exception;


import shinhan.EggMoneyna.global.error.ErrorCode;

public class AuthenticationException extends BusinessException {

    public AuthenticationException(ErrorCode errorCode) {
        super(errorCode);
    }

}
