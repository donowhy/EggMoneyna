package shinhan.EggMoneyna.wishbox.service.dto;

import lombok.Getter;

@Getter
public class SendMoneyMyAccountRequest {
    private Long myAccountNumber;
    private int bankCode;
    private Long myRealAccountNumber;
    private int money;
}
