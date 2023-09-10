package shinhan.EggMoneyna.account.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class DetailAccountResponseDto {

    private String sendUser;

    private String memo;

    private int money;

    @Builder
    public DetailAccountResponseDto(String sendUser, String memo, int money) {
        this.sendUser = sendUser;
        this.memo = memo;
        this.money = money;
    }
}
