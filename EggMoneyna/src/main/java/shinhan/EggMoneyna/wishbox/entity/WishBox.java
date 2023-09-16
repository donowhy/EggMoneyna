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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishbox_id")
    private Long id;

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
    public WishBox(Long id, String wishName, int price, int balance, Long virtualNumber, BankCode bankCode, Account account, Child child) {
        this.id = id;
        this.wishName = wishName;
        this.price = price;
        this.balance = balance;
        this.virtualNumber = virtualNumber;
        this.bankCode = bankCode;
        this.account = account;
        this.child = child;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
