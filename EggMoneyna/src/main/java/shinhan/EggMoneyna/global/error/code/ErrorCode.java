package shinhan.EggMoneyna.global.error.code;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    TEST(HttpStatus.INTERNAL_SERVER_ERROR, "001", "exception test"),
    NOT_EXISTS_DATA(HttpStatus.BAD_REQUEST, "002", "존재하지 않는 데이터입니다."),
    NOT_VALID_DATA(HttpStatus.BAD_REQUEST, "003", "유효하지 않는 데이터입니다."),
    INVALID_DATA_TYPE(HttpStatus.BAD_REQUEST, "004", "잘못된 데이터 타입입니다."),
    ALREADY_REGISTERED_DATA(HttpStatus.BAD_REQUEST, "005", "이미 존재하는 데이터입니다."),
//    FORBIDDEN_ROLE(HttpStatus.FORBIDDEN, "006", "해당 Role이 아닙니다."),
    UNSUPPORTED_MEDIA_TYPE(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "007", "지원하지 않는 미디어 유형입니다."),

    // 인증 && 인가
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "A-001", "토큰이 만료되었습니다."),
    NOT_VALID_TOKEN(HttpStatus.UNAUTHORIZED, "A-002", "해당 토큰은 유효한 토큰이 아닙니다."),
    NOT_EXISTS_AUTHORIZATION(HttpStatus.UNAUTHORIZED, "A-003", "Authorization Header가 빈 값입니다."),
    NOT_VALID_BEARER_GRANT_TYPE(HttpStatus.UNAUTHORIZED, "A-004", "인증 타입이 Bearer 타입이 아닙니다."),
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "A-005", "해당 refresh token은 존재하지 않습니다."),
    REFRESH_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "A-006", "해당 refresh token은 만료됐습니다."),
    NOT_ACCESS_TOKEN_TYPE(HttpStatus.UNAUTHORIZED, "A-007", "해당 토큰은 ACCESS TOKEN이 아닙니다."),
    FORBIDDEN_ROLE(HttpStatus.FORBIDDEN, "A-008", "해당 Role이 아닙니다."),

    // 유저
    NOT_EXISTS_USER_ID(HttpStatus.BAD_REQUEST, "U-001", "존재하지 않는 유저 아이디입니다."),
    NOT_EXISTS_USER_NICKNAME(HttpStatus.BAD_REQUEST, "U-002", "존재하지 않는 유저 닉네임입니다."),
    NOT_EXISTS_USER_EMAIL(HttpStatus.BAD_REQUEST, "U-003", "존재하지 않는 유저 이메일입니다."),
    NOT_EXISTS_PROVIDER(HttpStatus.BAD_REQUEST, "U-004", "존재하지 않는 소셜입니다."),
    ALREADY_REGISTERED_USER_ID(HttpStatus.BAD_REQUEST, "U-006", "이미 존재하는 유저 아이디입니다."),
    NOT_EXISTS_USER_PASSWORD(HttpStatus.BAD_REQUEST, "U-007", "존재하지 않는 유저 비밀번호입니다."),
    INVALID_USER_DATA(HttpStatus.BAD_REQUEST, "U-008", "잘못된 유저 정보입니다."),
    NOT_EXISTS_FOLLOWER(HttpStatus.BAD_REQUEST, "U-009", "존재하지 않는 팔로워입니다."),
    NOT_EXISTS_FOLLOWING(HttpStatus.BAD_REQUEST, "U-010", "존재하지 않는 팔로윙입니다."),
    INVALID_ADMIN(HttpStatus.BAD_REQUEST, "U-011", "Admin은 제외 시켜주세요."),
    INVALID_PARENT(HttpStatus.BAD_REQUEST, "U-012", "잘못된 부모 정보입니다."),

    // 입출금
    NOT_EXISTS_INPUTOUTPUT_ID(HttpStatus.BAD_REQUEST, "I-001", "존재하지 않는 입출금 아이디입니다."),

    // 코멘트
    NOT_EXISTS_COMMENT_ID(HttpStatus.BAD_REQUEST, "C-001", "존재하지 않는 코멘트 아이디입니다."),
    NOT_EXISTS_COMPLIMENT_ID(HttpStatus.BAD_REQUEST, "C-002", "존재하지 않는 칭찬 아이디입니다."),

    // 관계 (Relation)
    NOT_EXISTS_EGGMONEY_RELATION(HttpStatus.BAD_REQUEST, "R-001", "에그머니나 관계 된 자녀가 없습니다.");


    private HttpStatus httpStatus;
    private String errorCode;
    private String message;

    ErrorCode(HttpStatus httpStatus, String errorCode, String message) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.message = message;
    }
}
