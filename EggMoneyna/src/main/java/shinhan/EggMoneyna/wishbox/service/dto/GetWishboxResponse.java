package shinhan.EggMoneyna.wishbox.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shinhan.EggMoneyna.account.entity.BankCode;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetWishboxResponse {
    private String nickName;

    private String wishName;

    private int price;

    private int balance;

    private Long virtualNumber;

    private String createTime;
    private String modifyingTime;
}
