package shinhan.EggMoneyna.account.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Check1CertChildResponse {

    private Boolean isRight;
    private String childToken;
}
