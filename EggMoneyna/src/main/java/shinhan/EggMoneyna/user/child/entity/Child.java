package shinhan.EggMoneyna.user.child.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.C;
import shinhan.EggMoneyna.account.entity.Account;
import shinhan.EggMoneyna.monster.entity.Monster;
import shinhan.EggMoneyna.monster.entity.MonsterEncyclopedia;
import shinhan.EggMoneyna.user.follow.entity.Relation;
import shinhan.EggMoneyna.users.entity.Users;
import shinhan.EggMoneyna.wishbox.entity.WishBox;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
@NoArgsConstructor
public class Child {

    @Id
    @GeneratedValue
    @Column(name = "childs_id")
    private Long id;

    private String childId;

    private String password;

    private int pocketMoney;

    private int limitMoney;

    private int pocketMoneyDate;

    @OneToOne(fetch = FetchType.LAZY)
    private Account account;

    @OneToMany(mappedBy = "user")
    private List<Monster> monsters = new ArrayList<>();

    private int cntMonsters;

    @OneToOne(fetch = FetchType.LAZY)
    private MonsterEncyclopedia monsterEncyclopedia;

    @OneToMany(mappedBy = "child")
    private List<WishBox> wishBoxes;

    private String firebaseToken;

    @OneToMany(mappedBy = "child")
    private List<Relation> relations;

    private Boolean isRelation;

    private Boolean eggMoney;

    @Builder
    public Child(Long id, String childId, String password, int pocketMoney, int limitMoney, int pocketMoneyDate, Account account, List<Monster> monsters, int cntMonsters, MonsterEncyclopedia monsterEncyclopedia, List<WishBox> wishBoxes, String firebaseToken, List<Relation> relations, Boolean isRelation, Boolean eggMoney) {
        this.id = id;
        this.childId = childId;
        this.password = password;
        this.pocketMoney = pocketMoney;
        this.limitMoney = limitMoney;
        this.pocketMoneyDate = pocketMoneyDate;
        this.account = account;
        this.monsters = monsters;
        this.cntMonsters = cntMonsters;
        this.monsterEncyclopedia = monsterEncyclopedia;
        this.wishBoxes = wishBoxes;
        this.firebaseToken = firebaseToken;
        this.relations = relations;
        this.isRelation = isRelation;
        this.eggMoney = eggMoney;
    }

    public void setLimitMoney(int limitMoney) {
        this.limitMoney = limitMoney;
    }

    public void setIsRelation(boolean isRelation) {
        this.isRelation = isRelation;
    }
}
