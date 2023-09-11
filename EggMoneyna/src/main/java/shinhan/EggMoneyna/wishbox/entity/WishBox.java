package shinhan.EggMoneyna.wishbox.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shinhan.EggMoneyna.account.entity.Account;
import shinhan.EggMoneyna.account.entity.BankCode;
import shinhan.EggMoneyna.users.entity.Users;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class WishBox {

   @Id
   @GeneratedValue
   private Long id;

   private String nickName;

   private String wishName;

   private int price;

   private int balance;

   @Enumerated(EnumType.STRING)
   private BankCode bankCode;

   private Long virtualAccount;

   @ManyToOne(fetch = FetchType.LAZY)
   private Account account;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;


    @Builder
    public WishBox(Long id, String nickName, String wishName, int price, int balance, BankCode bankCode, Long virtualAccount, Account account, Users users) {
        this.id = id;
        this.nickName = nickName;
        this.wishName = wishName;
        this.price = price;
        this.balance = balance;
        this.bankCode = BankCode.Shinhan;
        this.virtualAccount = virtualAccount;
        this.account = account;
        this.users = users;
    }
}
