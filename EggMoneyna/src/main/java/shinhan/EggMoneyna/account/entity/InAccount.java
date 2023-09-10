package shinhan.EggMoneyna.account.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class InAccount {

    @Id
    @GeneratedValue
    private Long id;

    private String sendUser;

    private String useWhere;

    private String memo;

    private int money;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;


    @Builder
    public InAccount(Long id, String sendUser, String useWhere, String memo, int money, Account account) {
        this.id = id;
        this.sendUser = sendUser;
        this.useWhere = useWhere;
        this.memo = memo;
        this.money = money;
        this.account = account;
    }
}
