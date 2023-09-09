package shinhan.EggMoneyna.account.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shinhan.EggMoneyna.users.entity.Users;
import shinhan.EggMoneyna.wishbox.entity.WishBox;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Account {

   @Id
   @GeneratedValue
   private Long id;

   private String nickName;

   private Long accountNumber;

   private int balance;

   @OneToOne(mappedBy = "account")
   @JsonIgnore
   private WishBox wishBox;

   @OneToOne
   @JoinColumn(name = "id")
   private Users users;
   @Builder
   public Account(Long id, String nickName, Long accountNumber, int balance, WishBox wishBox, Users user) {
       this.id = id;
       this.nickName = nickName;
       this.accountNumber = accountNumber;
       this.balance = balance;
       this.wishBox = wishBox;
       this.users = user;
   }

   public void setNickName(String name) {
       this.nickName = name;
   }
}
