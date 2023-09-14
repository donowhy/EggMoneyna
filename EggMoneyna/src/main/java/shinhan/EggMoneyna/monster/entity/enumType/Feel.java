package shinhan.EggMoneyna.monster.entity.enumType;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Feel {

    // 추가적인 기준점이 필요함
    NOTHING(3, "행복해"),
    SAD(0, "슬퍼 흑흑"),
    HAPPY(5,"행복해");

    private final int key;
    private final String title;
}
