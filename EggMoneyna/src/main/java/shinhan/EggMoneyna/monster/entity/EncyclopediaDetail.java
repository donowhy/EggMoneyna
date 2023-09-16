package shinhan.EggMoneyna.monster.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shinhan.EggMoneyna.global.common.BaseTimeEntity;
import shinhan.EggMoneyna.user.child.entity.Child;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class EncyclopediaDetail extends BaseTimeEntity {

    @Id
    @Column(name = "monsterDetail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isRegister;

    private String MongName;

    private String MongStory;

    private String registerDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "monsterEncyclopedia_id")
    private MonsterEncyclopedia monsterEncyclopedia;

    @Builder
    public EncyclopediaDetail(Long id, Boolean isRegister, String mongName, String mongStory, String registerDate, MonsterEncyclopedia monsterEncyclopedia) {
        this.id = id;
        this.isRegister = isRegister;
        MongName = mongName;
        MongStory = mongStory;
        this.registerDate = registerDate;
        this.monsterEncyclopedia = monsterEncyclopedia;
    }

    public void setMonsterEncyclopedia(MonsterEncyclopedia monsterEncyclopedia){
        this.monsterEncyclopedia = monsterEncyclopedia;
    }

    public void setIsRegister(Boolean isRegister) {
        this.isRegister = isRegister;
    }

    public void setRegisterTime(LocalDate now) {
        this.registerDate = String.valueOf(now);
    }
}
