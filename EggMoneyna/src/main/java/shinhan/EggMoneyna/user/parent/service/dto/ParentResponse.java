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

    // 아이 닉네임
    private List<String> childNicknames;

    private List<String> eggMoneynaChild;

    // 용돈
    private int pocketMoney;

    // 용돈 주는 날
    private int pocketMoneyDate;
}
