package shinhan.EggMoneyna.users.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import shinhan.EggMoneyna.account.entity.Account;
import shinhan.EggMoneyna.global.common.BaseTimeEntity;

import shinhan.EggMoneyna.monster.entity.Monster;
import shinhan.EggMoneyna.monster.entity.MonsterEncyclopedia;
import shinhan.EggMoneyna.wishbox.entity.WishBox;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor
public class Users extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "users_id")
    private Long id;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isParents;

    private String userId;

    private String password;

    private String nickName;

    private int pocketMoney;

    private int limitMoney;

    private int pocketMoneyDate;

    private String token;

    @OneToOne
    private Account account;

    @OneToMany(mappedBy = "child")
    private List<Monster> monsters = new ArrayList<>();

    private int cntMonsters;

    @OneToOne
    private MonsterEncyclopedia monsterEncyclopedia;

//    @OneToMany(mappedBy = "users")
//    private List<WishBox> wishBoxes;

    @ManyToMany
    @JoinTable(
            name = "user_relations",
            joinColumns = @JoinColumn(name = "child_id"),
            inverseJoinColumns = @JoinColumn(name = "parent_id")
    )
    @JsonManagedReference
    private List<Users> parents = new ArrayList<>();

    @ManyToMany(mappedBy = "parents")
    @JsonBackReference
    private List<Users> children = new ArrayList<>();

    private String firebaseToken;


    @Builder
    public Users(Long id, Boolean isParents, String userId, String password, String nickName, int pocketMoney, int limitMoney, int pocketMoneyDate, String token, Account account, List<Monster> monsters, int cntMonsters, MonsterEncyclopedia monsterEncyclopedia, String firebaseToken) {
        this.id = id;
        this.isParents = isParents;
        this.userId = userId;
        this.password = password;
        this.nickName = nickName;
        this.pocketMoney = pocketMoney;
        this.limitMoney = limitMoney;
        this.pocketMoneyDate = pocketMoneyDate;
        this.token = token;
        this.account = account;
        this.monsters = monsters;
        this.cntMonsters = cntMonsters;
        this.monsterEncyclopedia = monsterEncyclopedia;
        this.firebaseToken = firebaseToken;
    }

    public void setAtParent(String nickName, int pocketMoney, int pocketMoneyDate) {
        this.nickName = nickName;
        this.pocketMoney = pocketMoney;
        this.pocketMoneyDate = pocketMoneyDate;
    }


    public void setToken(String actualToken) {
        this.token = actualToken;
    }

    public void addParent(Users parent) {
        this.parents.add(parent);
        parent.getChildren().add(this);
    }

    public void addChild(Users child) {
        this.children.add(child);
        child.getParents().add(this);
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setCntMonsters(int i) {
        this.cntMonsters = i;
    }

    public void setLimitMoney(int limitMoney) {
        this.limitMoney = limitMoney;
    }
}
