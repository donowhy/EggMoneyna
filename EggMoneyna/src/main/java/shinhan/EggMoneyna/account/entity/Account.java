package shinhan.EggMoneyna.account.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shinhan.EggMoneyna.wishbox.entity.WishBox;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
}
