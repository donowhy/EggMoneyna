package shinhan.EggMoneyna.account.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shinhan.EggMoneyna.global.common.BaseTimeEntity;
import shinhan.EggMoneyna.inputoutput.entity.InputOutput;
import shinhan.EggMoneyna.users.entity.Users;
import shinhan.EggMoneyna.wishbox.entity.WishBox;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Account extends BaseTimeEntity {

   @Id
   @GeneratedValue
   private Long id;

   private String nickName;

   @Enumerated(EnumType.STRING)
   private BankCode bankCode;

   private Long accountNumber;

   private int balance;

   @OneToMany(mappedBy = "account")
   @JsonIgnore
   private List<WishBox> wishBox = new ArrayList<>();

   @OneToOne
   @JoinColumn(name = "users_id")
   @JsonIgnore
   private Users users;

   @Column(columnDefinition = "boolean default false")
   private Boolean autoTermination;


   @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<InputOutput> inputOutputs = new ArrayList<>();


    @Builder
    public Account(Long id, String nickName, BankCode bankCode, Long accountNumber, int balance, List<WishBox> wishBox, Users users, Boolean autoTermination, List<InputOutput> inputOutputs) {
        this.id = id;
        this.nickName = nickName;
        this.bankCode = bankCode;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.wishBox = wishBox;
        this.users = users;
        this.autoTermination = autoTermination;
        this.inputOutputs = inputOutputs;
    }

    public void setNickName(String name) {
       this.nickName = name;
   }

    public void setBalance(int i) {
        this.balance = i;
    }

    public void inBalance(int input) {
        this.balance += input;
    }

    public void outBalance(int output) {
        this.balance -= output;
    }
}
