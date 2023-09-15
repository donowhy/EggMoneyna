package shinhan.EggMoneyna.monster.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shinhan.EggMoneyna.monster.entity.enumType.Benefit;
import shinhan.EggMoneyna.monster.entity.enumType.Feel;
import shinhan.EggMoneyna.monster.entity.enumType.MonsterStatus;
import shinhan.EggMoneyna.monster.entity.enumType.ShinhanMong;
import shinhan.EggMoneyna.user.child.entity.Child;

import javax.persistence.*;
import java.util.Random;

@Entity
@Getter
@NoArgsConstructor
public class Monster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "monster_id")
    private Long id;

    private ShinhanMong name;

    @Enumerated(EnumType.STRING)
    private MonsterStatus status;

    @Enumerated(EnumType.STRING)
    private Feel feel;

    private int exp;

    @Enumerated(EnumType.STRING)
    private Benefit benefit;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "child_id")
    private Child child;

    @Builder
    public Monster(Long id, ShinhanMong name, MonsterStatus status, Feel feel, int exp, Benefit benefit, Child child) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.feel = feel;
        this.exp = exp;
        this.benefit = benefit;
        this.child = child;
    }

    public static ShinhanMong getRandomMong() {
        ShinhanMong[] mongs = ShinhanMong.values();
        int randomIndex = new Random().nextInt(mongs.length);
        return mongs[randomIndex];
    }

    public static MonsterStatus getMonsterStatus(String status) {
        return MonsterStatus.Egg;
    }

    public void setMonsterExp(int exp) {
        this.exp = exp;
    }

    public void setFeel(Feel feel) {
        this.feel = feel;
    }
}
