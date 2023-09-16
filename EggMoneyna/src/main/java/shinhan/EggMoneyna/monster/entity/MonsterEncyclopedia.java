package shinhan.EggMoneyna.monster.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shinhan.EggMoneyna.global.common.BaseTimeEntity;
import shinhan.EggMoneyna.user.child.entity.Child;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
// 생성 시간, 각 몬스터가 등록될 때의 시간, 몬스터 정보
public class MonsterEncyclopedia extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "monsterEncyclopedia_id")
    private Long id;

    private String worldView;

    @ElementCollection
    private List<ShinhanMong> SOL = new ArrayList<>();

    @ElementCollection
    private List<ShinhanMong> MOLI = new ArrayList<>();

    @ElementCollection
    private List<ShinhanMong> RINO = new ArrayList<>();

    @ElementCollection
    private List<ShinhanMong> SHOO = new ArrayList<>();

    @ElementCollection
    private List<ShinhanMong> DOREMI = new ArrayList<>();

    @ElementCollection
    private List<ShinhanMong> LULULALA = new ArrayList<>();

    @ElementCollection
    private List<ShinhanMong> PLI = new ArrayList<>();

    @ElementCollection
    private List<ShinhanMong> LAY = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "childs_id")
    @JsonIgnore
    private Child child;


    @Embeddable
    public static class ShinhanMong{
        Boolean bl;
        String name;
        String story;
        LocalDateTime registerDate;

        public ShinhanMong(Boolean bl, String name, String story, LocalDateTime registerDate) {
            this.bl = bl;
            this.name = name;
            this.story = story;
            this.registerDate = LocalDateTime.now();
        }

        public ShinhanMong() {

        }
    }

    @Builder
    public MonsterEncyclopedia(Long id, String worldView, List<ShinhanMong> SOL, List<ShinhanMong> MOLI, List<ShinhanMong> RINO, List<ShinhanMong> SHOO, List<ShinhanMong> DOREMI, List<ShinhanMong> LULULALA, List<ShinhanMong> PLI, List<ShinhanMong> LAY, Child child) {
        this.id = id;
        this.worldView = worldView;
        this.SOL = SOL;
        this.MOLI = MOLI;
        this.RINO = RINO;
        this.SHOO = SHOO;
        this.DOREMI = DOREMI;
        this.LULULALA = LULULALA;
        this.PLI = PLI;
        this.LAY = LAY;
        this.child = child;
    }
}
