package shinhan.EggMoneyna.user.child.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
public class GetChildHomeResponse {
    private String childName;
    private String shinhanMongDate;
    private int balance;
    private int limitMoney;
    private int leftMoneyToLimit;
    private String attemptDate;
    private int compliment;
    private Boolean HaveWishbox;
    private Long wishboxNumber;
}
