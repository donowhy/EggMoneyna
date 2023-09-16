package shinhan.EggMoneyna.monster.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shinhan.EggMoneyna.monster.entity.History;
import shinhan.EggMoneyna.monster.entity.enumType.Feel;
import shinhan.EggMoneyna.monster.entity.enumType.MonsterStatus;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MonsterDetail {

    private int exp;
    private MonsterStatus status;
    private Feel feel;
    private List<History> historyList;
}
