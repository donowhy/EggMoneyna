package shinhan.EggMoneyna.monster.entity.enumType;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MonsterStatus {

    Egg(0, "아기"),
    Child(10, "아이"),
    Adult(300,"어른");

    private final int key;
    private final String title;

}
