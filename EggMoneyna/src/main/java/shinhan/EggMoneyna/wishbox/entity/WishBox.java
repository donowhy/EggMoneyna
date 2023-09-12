package shinhan.EggMoneyna.wishbox.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shinhan.EggMoneyna.account.entity.Account;
import shinhan.EggMoneyna.users.entity.Users;

import javax.persistence.*;
import java.util.List;

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

   @OneToOne
   @JoinColumn(name = "id")
   private Account account;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

   public WishBox(Long id, String nickName, String wishName, int price, int balance, Account account) {
       this.id = id;
       this.nickName = nickName;
       this.wishName = wishName;
       this.price = price;
       this.balance = balance;
       this.account = account;
   }
}
