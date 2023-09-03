package shinhan.EggMoneyna.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserSaveRequestDto {

    private String name;
    private String password;
    private String nickName;
    private Boolean isParent;
    private int pocketMoneyDate;

    @Builder
    public UserSaveRequestDto(String name, String password, String nickName, Boolean isParent, int pocketMoneyDate) {
        this.name = name;
        this.password = password;
        this.nickName = nickName;
        this.isParent = isParent;
        this.pocketMoneyDate = pocketMoneyDate;
    }
}
