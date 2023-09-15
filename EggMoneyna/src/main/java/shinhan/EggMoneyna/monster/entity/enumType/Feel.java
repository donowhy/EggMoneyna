package shinhan.EggMoneyna.monster.entity.enumType;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Feel {

    // 추가적인 기준점이 필요함
    NOMAL(3, "NOMAL"),
    SAD(0, "SAD"),
    HAPPY(5,"HAPPY");

    private final int key;
    private final String title;
}
