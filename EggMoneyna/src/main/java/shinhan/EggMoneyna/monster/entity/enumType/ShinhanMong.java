package shinhan.EggMoneyna.monster.entity.enumType;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ShinhanMong {
    SOL("sol", "쏠"),
    MOLI("moli", "몰리"),
    RINO("rino", "리노"),
    SHOO("shoo", "슈"),
    DOREMI("doremi", "도레미"),
    LULULALA("lululala", "룰루랄라"),
    PLI("pli", "플리"),
    LAY("lay", "레이");

    private final String key;
    private final String title;
}

