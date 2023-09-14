package shinhan.EggMoneyna.monster.entity.enumType;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MonsterStatus {

    Egg(0, "알"),
    Adult(500, "어른"),
    register(1000,"등록");

    private final int key;
    private final String title;

}
