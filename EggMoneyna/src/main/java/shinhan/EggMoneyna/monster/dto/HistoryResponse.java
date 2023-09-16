package shinhan.EggMoneyna.monster.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoryResponse {
    private String content;
    private int exp;
    private int prefix;
}
