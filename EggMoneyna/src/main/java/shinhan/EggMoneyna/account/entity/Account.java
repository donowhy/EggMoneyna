package shinhan.EggMoneyna.account.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shinhan.EggMoneyna.global.common.BaseEntity;
import shinhan.EggMoneyna.users.entity.Users;
import shinhan.EggMoneyna.wishbox.entity.WishBox;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Account extends BaseEntity {

   @Id
   @GeneratedValue
   private Long id;

   private String nickName;

//   @Enumerated(EnumType.STRING)
//   private Code bankCode;

   private Long accountNumber;

   private int balance;

   @OneToOne(mappedBy = "account")
   @JsonIgnore
   private WishBox wishBox;

   @OneToMany(mappedBy = "account")
   private List<InAccount> inAccountList;

   @OneToOne
   @JoinColumn(name = "users_id")
   @JsonIgnore
   private Users users;

   @Column(columnDefinition = "boolean default false")
   private Boolean autoTermination;


   @Builder
    public Account(Long id, String nickName, Long accountNumber, int balance, WishBox wishBox, List<InAccount> inAccountList, Users users, Boolean autoTermination) {
        this.id = id;
        this.nickName = nickName;
//        this.bankCode = bankCode;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.wishBox = wishBox;
        this.inAccountList = inAccountList;
        this.users = users;
        this.autoTermination = autoTermination;
    }

    public void setNickName(String name) {
       this.nickName = name;
   }

    public void setBalance(int i) {
        this.balance = i;
    }
}
