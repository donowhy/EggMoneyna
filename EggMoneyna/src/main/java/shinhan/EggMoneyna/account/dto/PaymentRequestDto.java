package shinhan.EggMoneyna.account.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PaymentRequestDto {
    private String sendUser;
    private String useWhere;
    private String memo;
    private int spentMoney;


}
