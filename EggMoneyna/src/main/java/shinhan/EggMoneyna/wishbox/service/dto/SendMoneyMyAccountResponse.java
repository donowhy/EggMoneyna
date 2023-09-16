package shinhan.EggMoneyna.wishbox.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendMoneyMyAccountResponse {
    private Long realAccountNumber;
    private int virtualBalance;

    private Long virtualAccountNumber;
    private int realAccountBalance;


}
