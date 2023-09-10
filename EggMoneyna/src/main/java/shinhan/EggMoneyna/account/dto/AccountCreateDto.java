package shinhan.EggMoneyna.account.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountCreateDto {

    private String nickName;

    @Builder
    public AccountCreateDto(String nickName) {
        this.nickName = nickName;
    }
}
