package shinhan.EggMoneyna.wishbox.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

}
