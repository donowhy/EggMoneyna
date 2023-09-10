package shinhan.EggMoneyna.inputoutput.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import shinhan.EggMoneyna.account.entity.Account;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class InputOutput {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brandName;

    private String brandImg;

    private String bigCategory;

    private String smallCategory;

    private int input;

    private int output;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;
}
