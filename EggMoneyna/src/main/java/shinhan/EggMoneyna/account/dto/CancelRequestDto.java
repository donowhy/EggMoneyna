package shinhan.EggMoneyna.account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CancelRequestDto {
    private String useWhere;
    private String sendUser;
    private int money;
}
