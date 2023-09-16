package shinhan.EggMoneyna.monster.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shinhan.EggMoneyna.monster.entity.Monster;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MonsterSaveResponseDto {

    private String name;
    private String feel;
    private String benefit;

    public static MonsterSaveResponseDto of(Monster monster) {
        return new MonsterSaveResponseDto(
                monster.getName().toString(),
                monster.getFeel().toString(),
                monster.getBenefit().toString());
    }
}
