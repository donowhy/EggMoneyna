package shinhan.EggMoneyna.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDto {

    private String sendUser;
    private String useWhere;
    private String memo;
    private int spentMoney;
    private LocalDateTime spentTime;
    private LocalDateTime modifyTime;

}
