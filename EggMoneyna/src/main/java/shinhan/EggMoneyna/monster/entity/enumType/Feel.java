package shinhan.EggMoneyna.monster.entity.enumType;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Feel {

    // 추가적인 기준점이 필요함
    Noting(500, "상급 챌린저"),
    Happy(0, "행복해"),
    Sad(10, "슬퍼 흑흑"),
    Angry(300,"왜 이제 와"),
    Excited(1000, "세상 제일 행복해");

    private final int key;
    private final String title;
}
