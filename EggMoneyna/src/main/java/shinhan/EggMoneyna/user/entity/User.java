package shinhan.EggMoneyna.user.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shinhan.EggMoneyna.account.entity.Account;
import shinhan.EggMoneyna.monster.entity.Monster;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String userId;

    private String password;

    private String nickName;

    private Boolean isParent;

    private int pocketMoney;

    private int pocketMoneyDate;

    @OneToMany(mappedBy = "user")
    private List<Monster> monsterList;

    @OneToOne(mappedBy = "user")
    private Account account;

    @Builder
    public User(Long id, String userId, String password, String nickName, Boolean isParent, int pocketMoney, int pocketMoneyDate, List<Monster> monsterList, Account account) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.nickName = nickName;
        this.isParent = isParent;
        this.pocketMoney = pocketMoney;
        this.pocketMoneyDate = pocketMoneyDate;
        this.monsterList = monsterList;
        this.account = account;
    }

    public String setNickName(String nickname) {
        this.nickName = nickname;
        return this.nickName;
    }
}

