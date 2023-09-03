package shinhan.EggMoneyna.wishbox.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shinhan.EggMoneyna.account.entity.Account;

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

    @OneToOne(mappedBy = "wishBox")
    private Account account;
}
