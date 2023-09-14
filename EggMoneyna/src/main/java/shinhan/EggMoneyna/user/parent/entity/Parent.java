package shinhan.EggMoneyna.user.parent.entity;



import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shinhan.EggMoneyna.user.follow.entity.Relation;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Parent {

    @Id
    @GeneratedValue
    @Column(name = "parents_id")
    private Long id;

    private String parentId;

    private String password;

    // 아이 닉네임
    private String childNickname;

    // 용돈
    private int pocketMoney;

    // 용돈 주는 날
    @Column()
    private int pocketMoneyDate;

    private String firebaseToken;

    @OneToMany(mappedBy = "parent")
    private List<Relation> relations;

    private Boolean isRelation;

    private Boolean eggMoney;

    @Builder
    public Parent(Long id, String parentId, String password, String childNickname, int pocketMoney, int pocketMoneyDate, String firebaseToken, List<Relation> relations, Boolean isRelation, Boolean eggMoney) {
        this.id = id;
        this.parentId = parentId;
        this.password = password;
        this.childNickname = childNickname;
        this.pocketMoney = pocketMoney;
        this.pocketMoneyDate = pocketMoneyDate;
        this.firebaseToken = firebaseToken;
        this.relations = relations;
        this.isRelation = isRelation;
        this.eggMoney = eggMoney;
    }

    public void setNickname(String nickname) {
        this.childNickname = nickname;
    }

    public void setPocketMoneyDate(int day) {
        this.pocketMoneyDate = day;
    }

    public void setPocketMoney(int money) {
        this.pocketMoney = money;
    }

    public void setIsRelation(boolean isRelation) {
        this.isRelation = isRelation;
    }
}
