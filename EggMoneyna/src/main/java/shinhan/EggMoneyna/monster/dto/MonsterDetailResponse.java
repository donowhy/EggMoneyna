package shinhan.EggMoneyna.monster.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MonsterDetailResponse {
    private String chracterName;
    private String story;
    private String registerDate;
    private String withMeTime;
}
