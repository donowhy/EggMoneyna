package shinhan.EggMoneyna.account.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BankCode {

    // 은행 코드
    Shinhan("Shinhan", "088"),
    KBKookmin("KBKookmin", "004");

    private final String key;
    private final String bankCode;

}
