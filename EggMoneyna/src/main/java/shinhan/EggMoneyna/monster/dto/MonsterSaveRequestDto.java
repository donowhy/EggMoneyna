package shinhan.EggMoneyna.monster.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shinhan.EggMoneyna.monster.entity.enumType.Benefit;

@Getter
@NoArgsConstructor
public class MonsterSaveRequestDto {
    private Benefit benefit;

    @Builder
    public MonsterSaveRequestDto(Benefit benefit) {
        this.benefit = benefit;
    }
}
