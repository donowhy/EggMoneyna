package shinhan.EggMoneyna.users.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import shinhan.EggMoneyna.account.entity.Account;
import shinhan.EggMoneyna.global.common.BaseTimeEntity;

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

    @ManyToMany
    @JoinTable(
            name = "user_relations",
            joinColumns = @JoinColumn(name = "child_id"),
            inverseJoinColumns = @JoinColumn(name = "parent_id")
    )
    private List<Users> parents = new ArrayList<>();

    @ManyToMany(mappedBy = "parents")
    private List<Users> children = new ArrayList<>();

    private String firebaseToken;

    @Builder
    public Users(Long id, Boolean isParents, String userId, String password, String nickName, int pocketMoney,
        int limitMoney, int pocketMoneyDate, String token, Account account, String firebaseToken) {
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
        this.firebaseToken = firebaseToken;
    }

    public void update(String nickName, int limitMoney, int pocketMoney, int pocketMoneyDate) {
        this.nickName = nickName;
        this.limitMoney = limitMoney;
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
}
