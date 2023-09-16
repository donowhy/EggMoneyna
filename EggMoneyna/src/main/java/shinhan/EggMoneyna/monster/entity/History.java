package shinhan.EggMoneyna.monster.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shinhan.EggMoneyna.global.common.BaseTimeEntity;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class History extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "history_id")
    private Long id;

    private String content;

    private int exp;

    private int prefix;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "monster_id")
    private Monster monster;

    @Builder
    public History(Long id, String content, int exp, int prefix, Monster monster) {
        this.id = id;
        this.content = content;
        this.exp = exp;
        this.prefix = prefix;
        this.monster = monster;
    }
}

