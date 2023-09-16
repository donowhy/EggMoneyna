package shinhan.EggMoneyna.user.parent.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParentResponse {

    private String parentId;


    private List<String> eggMoneynaChild;

    // 용돈
    private int pocketMoney;

    // 용돈 주는 날
    private int pocketMoneyDate;
}
