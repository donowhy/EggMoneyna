package shinhan.EggMoneyna.user.dto;

import lombok.Builder;
import shinhan.EggMoneyna.user.entity.User;


public class UserResponseDto {
    private String name;
    private String nickName;
    private Boolean isParent;
    private int pocketMoney;
    private int pocketMoneyDate;

    @Builder
    public UserResponseDto(String name, String nickName, Boolean isParent, int pocketMoney, int pocketMoneyDate) {
        this.name = name;
        this.nickName = nickName;
        this.isParent = isParent;
        this.pocketMoney = pocketMoney;
        this.pocketMoneyDate = pocketMoneyDate;
    }

    @Builder
    public static UserResponseDto userResponseDto(User user) {

        return new UserResponseDto(
                user.getUserId(),
                user.getNickName(),
                user.getIsParent(),
                user.getPocketMoney(),
                user.getPocketMoneyDate());

    }
}
