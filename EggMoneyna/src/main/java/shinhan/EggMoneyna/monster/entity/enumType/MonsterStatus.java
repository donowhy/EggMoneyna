package shinhan.EggMoneyna.monster.entity.enumType;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MonsterStatus {

    Egg(0, "알"),
    Adult(10, "어른"),
    register(300,"등록");

    private final int key;
    private final String title;

    public static MonsterStatus getMonsterStatus(int exp) {
        MonsterStatus[] monsterStatuses = MonsterStatus.values();
        MonsterStatus result = MonsterStatus.Egg;

        for (MonsterStatus monsterStatus : monsterStatuses) {
            if (exp >= monsterStatus.getKey()) {
                result = monsterStatus;
            } else {
                break;
            }
        }
        return result;
    }

}
