package shinhan.EggMoneyna.users.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shinhan.EggMoneyna.account.entity.Account;
import shinhan.EggMoneyna.domain.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor
public class Users extends BaseEntity {

    @Id
    @GeneratedValue
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

    // @OneToMany
    // private List<Users> parents;
    //
    // @OneToMany
    // private List<Users> child;

    @Builder
    public Users(Long id, Boolean isParents, String userId, String password, String nickName, int pocketMoney,
        int limitMoney, int pocketMoneyDate, String token, Account account) {
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
}
