package shinhan.EggMoneyna.users.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class returnTokenDto {
    private String parentToken;
    private String childToken;
}
