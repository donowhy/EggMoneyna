package shinhan.EggMoneyna.user.child.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shinhan.EggMoneyna.account.entity.Account;
import shinhan.EggMoneyna.monster.entity.Monster;
import shinhan.EggMoneyna.monster.entity.MonsterEncyclopedia;
import shinhan.EggMoneyna.user.follow.entity.Relation;
import shinhan.EggMoneyna.wishbox.entity.WishBox;

import javax.persistence.*;
import java.util.*;


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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "monster_id")
    private Monster monster;

    private int cntMonsters;

    @OneToOne(fetch = FetchType.LAZY)
    private MonsterEncyclopedia monsterEncyclopedia;

    @OneToMany(mappedBy = "child")
    private List<WishBox> wishBoxes;

    private String firebaseToken;

    @OneToMany(mappedBy = "child")
    @JsonIgnore
    private List<Relation> relations;

    private Boolean isRelation = false;

    private Boolean eggMoney = false;

    private Boolean todayCheck = false;

    @ElementCollection
    private List<Boolean> sevendays = new ArrayList<>();

    @ElementCollection
    private List<Boolean> month = new ArrayList<>();

    private int compliment;

    private int consecutiveAttempt;
    @Builder
    public Child(Long id, String childId, String password, int pocketMoney, int limitMoney, int pocketMoneyDate, Account account, Monster monster, int cntMonsters, MonsterEncyclopedia monsterEncyclopedia, List<WishBox> wishBoxes, String firebaseToken, List<Relation> relations, Boolean isRelation, Boolean eggMoney, Boolean todayCheck, List<Boolean> sevendays, List<Boolean> month,  int compliment, int consecutiveAttempt) {
        this.id = id;
        this.childId = childId;
        this.password = password;
        this.pocketMoney = pocketMoney;
        this.limitMoney = limitMoney;
        this.pocketMoneyDate = pocketMoneyDate;
        this.account = account;
        this.monster = monster;
        this.cntMonsters = cntMonsters;
        this.monsterEncyclopedia = monsterEncyclopedia;
        this.wishBoxes = wishBoxes;
        this.firebaseToken = firebaseToken;
        this.relations = relations;
        this.isRelation = isRelation;
        this.eggMoney = eggMoney;
        this.todayCheck = todayCheck;
        this.sevendays = sevendays;
        this.month = month;
        this.compliment = compliment;
        this.consecutiveAttempt = consecutiveAttempt;
    }

    public void setLimitMoney(int limitMoney) {
        this.limitMoney = limitMoney;
    }

    public void setIsRelation(boolean isRelation) {
        this.isRelation = isRelation;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setCntMonsters(int cntMonsters) {
        this.cntMonsters = cntMonsters;
    }

    public void setTodayLogin(Boolean todayCheck) {
        this.todayCheck = todayCheck;
    }

    public void setSevenDays(List<Boolean> sevendays) {
        this.sevendays = sevendays;
    }

    public void setMonth(List<Boolean> month) {
        this.month = month;
    }


    public void setConsecutiveceAttemptAndTodayCheck(int consecutiveAttempt) {
        this.consecutiveAttempt = consecutiveAttempt;
        this.todayCheck = false;
    }

    public void setConsecutiveceAttempt(int consecutiveAttempt) {
        this.consecutiveAttempt = consecutiveAttempt;
    }
}
