package shinhan.EggMoneyna.users.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateRequestDto {

    private String nickName;
    private int money;
    private int date;
}
