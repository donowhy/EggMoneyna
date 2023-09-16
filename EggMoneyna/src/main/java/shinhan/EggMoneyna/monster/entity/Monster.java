package shinhan.EggMoneyna.monster.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shinhan.EggMoneyna.global.common.BaseTimeEntity;
import shinhan.EggMoneyna.monster.entity.enumType.Benefit;
import shinhan.EggMoneyna.monster.entity.enumType.Feel;
import shinhan.EggMoneyna.monster.entity.enumType.MonsterStatus;
import shinhan.EggMoneyna.monster.entity.enumType.ShinhanMong;
import shinhan.EggMoneyna.user.child.entity.Child;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@Getter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Monster extends BaseTimeEntity {

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

    @OneToMany(mappedBy = "monster")
    @JsonIgnore
    private List<History> histories = new ArrayList<>();

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "childs_default_id")
    private Child child;

    @Builder
    public Monster(Long id, ShinhanMong name, MonsterStatus status, Feel feel, int exp, Benefit benefit, List<History> histories, Child child) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.feel = feel;
        this.exp = exp;
        this.benefit = benefit;
        this.histories = histories;
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

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setStatus(MonsterStatus monsterStatus) {
        this.status = monsterStatus;
    }
}
