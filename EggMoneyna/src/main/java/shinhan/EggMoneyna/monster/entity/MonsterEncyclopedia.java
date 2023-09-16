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

    @JsonIgnore
    @OneToMany(mappedBy = "monsterEncyclopedia")
    private List<EncyclopediaDetail> encyclopediaDetails;

    @OneToOne
    @JoinColumn(name = "childs_id")
    @JsonIgnore
    private Child child;

    @Builder
    public MonsterEncyclopedia(Long id, String worldView, List<EncyclopediaDetail> encyclopediaDetails, Child child) {
        this.id = id;
        this.worldView = worldView;
        this.encyclopediaDetails = encyclopediaDetails;
        this.child = child;
    }


    public void EncyclopediaDetail(List<EncyclopediaDetail> save) {
        this.encyclopediaDetails = save;
    }
}
