package shinhan.EggMoneyna.wishbox.service.dto;

import lombok.Getter;

@Getter
public class SendMoneyMyAccountRequest {
    private Long virtualAccountNumber;
    private int money;
}

