package shinhan.EggMoneyna.users.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;

@Getter
@NoArgsConstructor
public class SignUpRequestDto {

    private String userId;
    private Boolean isParent;
    private String nickName;

    @Builder
    public SignUpRequestDto(String userId, Boolean isParent, String nickName) {
        this.userId = userId;
        this.isParent = isParent;
        this.nickName = nickName;
    }
}
