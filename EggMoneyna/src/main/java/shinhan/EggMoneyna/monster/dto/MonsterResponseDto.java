package shinhan.EggMoneyna.monster.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shinhan.EggMoneyna.monster.entity.Monster;

@Getter
@NoArgsConstructor
public class MonsterResponseDto {
    private String name;

    private String status;

    private String feel;

    private int exp;

    private String benefit;

    @Builder
    public MonsterResponseDto(String name, String status, int point, String feel, int exp, String benefit) {
        this.name = name;
        this.status = status;
        this.feel = feel;
        this.exp = exp;
        this.benefit = benefit;
    }

}
