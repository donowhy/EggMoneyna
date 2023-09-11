package shinhan.EggMoneyna.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAccountBalanceDto {

    private Long accountNumber;
    private int balance;

}
