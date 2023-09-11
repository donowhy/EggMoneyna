package shinhan.EggMoneyna.account.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shinhan.EggMoneyna.global.common.BaseTimeEntity;
import shinhan.EggMoneyna.users.entity.Users;
import shinhan.EggMoneyna.wishbox.entity.WishBox;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Account extends BaseTimeEntity {

   @Id
   @GeneratedValue
   @Column(name = "accountId")
   private Long id;

   private String nickName;

   @Enumerated(EnumType.STRING)
   private BankCode bankCode;

   private Long accountNumber;

   private int balance;

   @OneToMany(mappedBy = "account")
   private List<WishBox> wishBoxs;

   @OneToOne
   @JoinColumn(name = "users_id")
   @JsonIgnore
   private Users users;

   // 계좌 만기날 있는지?
   @Column(columnDefinition = "boolean default false")
   private Boolean autoTermination;


    @Builder
    public Account(Long id, String nickName, BankCode bankCode, Long accountNumber, int balance, List<WishBox> wishBoxs, Users users, Boolean autoTermination) {
        this.id = id;
        this.nickName = nickName;
        this.bankCode = bankCode;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.wishBoxs = wishBoxs;
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
