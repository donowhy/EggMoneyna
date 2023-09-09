package shinhan.EggMoneyna.global.error.exception;


import lombok.Getter;
import shinhan.EggMoneyna.global.error.code.ErrorCode;

@Getter
public class BadRequestException extends RuntimeException {

    private ErrorCode errorCode;

    public BadRequestException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}