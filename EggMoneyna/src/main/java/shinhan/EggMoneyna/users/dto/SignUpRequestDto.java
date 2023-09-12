package shinhan.EggMoneyna.users.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;

@Getter
@NoArgsConstructor
public class SignUpRequestDto {

    private String parentId;

    private Boolean isParent;

    private String childId;

    private String nickName;

    @Builder
    public SignUpRequestDto(String parentId, Boolean isParent, String childId, String nickName) {
        this.parentId = parentId;
        this.isParent = isParent;
        this.childId = childId;
        this.nickName = nickName;
    }
}
