package shinhan.EggMoneyna.global.error.exception;


import lombok.Getter;
import shinhan.EggMoneyna.global.error.code.ErrorCode;

@Getter
public class AuthenticationException extends RuntimeException{
    private ErrorCode errorCode;

    public AuthenticationException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
