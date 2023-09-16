package shinhan.EggMoneyna.user.child.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shinhan.EggMoneyna.account.entity.Account;
import shinhan.EggMoneyna.comment.entity.Compliment;
import shinhan.EggMoneyna.monster.entity.Monster;
import shinhan.EggMoneyna.monster.entity.MonsterEncyclopedia;
import shinhan.EggMoneyna.user.follow.entity.Relation;
import shinhan.EggMoneyna.wishbox.entity.WishBox;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;


@Getter
@Entity
@NoArgsConstructor
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "childs_default_id")
    private Long id;

    private String childName;

    private String password;

    private Boolean gender;

    private LocalDate birthday;

    private int pocketMoney;

    private int limitMoney;

    private int pocketMoneyDate;

    @OneToOne(fetch = FetchType.LAZY)
    private Account account;

    @JsonIgnore
    @OneToOne(mappedBy = "child", fetch = FetchType.LAZY)
    private Monster monster;


    private int cntMonsters;

    @OneToOne(fetch = FetchType.LAZY)
    private MonsterEncyclopedia monsterEncyclopedia;

    @OneToMany(mappedBy = "child")
    private List<WishBox> wishBoxes = new ArrayList<>();

    private String firebaseToken;

    @OneToMany(mappedBy = "child")
    @JsonIgnore
    private List<Relation> relations = new ArrayList<>();

    private Boolean isRelation;

    private Boolean eggMoney;

    private Boolean todayCheck;

    @ElementCollection
    private List<Boolean> sevendays = new ArrayList<>();

    @ElementCollection
    private List<Boolean> aMonth = new ArrayList<>();

    @OneToMany(mappedBy = "child")
    @JsonIgnore
    private List<Compliment> compliments = new ArrayList<>();

    private int consecutiveAttempt;


    @Builder

    public Child(Long id, String childName, String password, Boolean gender, LocalDate birthday, int pocketMoney, int limitMoney, int pocketMoneyDate, Account account, Monster monster, int cntMonsters, MonsterEncyclopedia monsterEncyclopedia, List<WishBox> wishBoxes, String firebaseToken, List<Relation> relations, Boolean isRelation, Boolean eggMoney, Boolean todayCheck, List<Boolean> sevendays, List<Boolean> aMonth, int consecutiveAttempt, List<Compliment> compliments) {

        this.id = id;
        this.childName = childName;
        this.password = password;
        this.gender = gender;
        this.birthday = birthday;
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
        this.isRelation = false;
        this.eggMoney = false;
        this.todayCheck = false;
        this.sevendays = sevendays;
        this.aMonth = aMonth;
        this.consecutiveAttempt = consecutiveAttempt;
        this.compliments = compliments;
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

    public void setAMonth(List<Boolean> month) {
        this.aMonth = month;
    }


    public void setConsecutiveceAttemptAndTodayCheck(int consecutiveAttempt) {
        this.consecutiveAttempt = consecutiveAttempt;
    }

    public void setConsecutiveceAttempt(int consecutiveAttempt) {
        this.consecutiveAttempt = consecutiveAttempt;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public void setMonsterEcyclopedia(MonsterEncyclopedia monsterEncyclopedia) {
        this.monsterEncyclopedia = monsterEncyclopedia;
    }

    public void setEggMoney(boolean eggMoney) {
        this.eggMoney = eggMoney;
    }

    public void setTodayCheck(Boolean todayCheck) {
        this.todayCheck = todayCheck;
    }
}

