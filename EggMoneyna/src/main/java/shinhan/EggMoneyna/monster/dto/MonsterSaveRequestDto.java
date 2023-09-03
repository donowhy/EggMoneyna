package shinhan.EggMoneyna.monster.dto;

import lombok.Builder;
import lombok.Getter;
import shinhan.EggMoneyna.monster.entity.enumType.Benefit;

@Getter
public class MonsterSaveRequestDto {
    private String nickName;
    private String feel;
    private String benefit;

    @Builder
    public MonsterSaveRequestDto( String nickName, String feel, String benefit) {
        this.nickName = nickName;
        this.feel = feel;
        this.benefit = benefit;
    }

    public Benefit getBenefitEnum() {
        try {
            return Benefit.valueOf(benefit);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid benefit value: " + benefit);
        }
    }
}
