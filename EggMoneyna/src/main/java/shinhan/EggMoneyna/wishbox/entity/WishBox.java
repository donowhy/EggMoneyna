package shinhan.EggMoneyna.wishbox.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shinhan.EggMoneyna.account.entity.Account;
import shinhan.EggMoneyna.account.entity.BankCode;
import shinhan.EggMoneyna.global.common.BaseTimeEntity;
import shinhan.EggMoneyna.user.child.entity.Child;
import shinhan.EggMoneyna.users.entity.Users;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class WishBox extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String nickName;

    private String wishName;

    private int price;

    private int balance;

    private Long virtualNumber;

    private BankCode bankCode;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "childs_id")
    private Child child;


    @Builder
    public WishBox(Long id, String nickName, String wishName, int price, int balance, Long virtualNumber, BankCode bankCode, Account account, Child child) {
        this.id = id;
        this.nickName = nickName;
        this.wishName = wishName;
        this.price = price;
        this.balance = balance;
        this.virtualNumber = virtualNumber;
        this.bankCode = bankCode;
        this.account = account;
        this.child = child;
    }

    public void setNickname(String nickname) {
        this.nickName = nickname;
    }
}
