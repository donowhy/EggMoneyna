package shinhan.EggMoneyna.monster.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MonstersResponse {
    private String showName;
    private String certName;
    private Boolean haveI;

}
