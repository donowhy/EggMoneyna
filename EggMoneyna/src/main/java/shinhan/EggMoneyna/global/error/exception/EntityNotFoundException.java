package shinhan.EggMoneyna.global.error.exception;


import shinhan.EggMoneyna.global.error.ErrorCode;

public class EntityNotFoundException extends BusinessException {

    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

}
