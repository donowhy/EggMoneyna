package shinhan.EggMoneyna.wishbox.service.dto;

import lombok.Getter;

@Getter
public class CreateWishItemDto {

    private String nickname;
    private String itemName;
    private int itemPrice;
    private int accountBalance;

}
