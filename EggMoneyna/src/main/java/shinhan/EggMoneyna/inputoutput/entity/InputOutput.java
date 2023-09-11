package shinhan.EggMoneyna.inputoutput.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import shinhan.EggMoneyna.account.entity.Account;
import shinhan.EggMoneyna.comment.entity.Comment;
import shinhan.EggMoneyna.global.common.BaseTimeEntity;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class InputOutput extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brandName;

    private String brandImg;

    private String bigCategory;

    private String smallCategory;

    @ColumnDefault("0")
    private int input;

    @ColumnDefault("0")
    private int output;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    @JsonIgnore
    private Comment comment;

    @Builder
    public InputOutput(Long id, String brandName, String brandImg, String bigCategory, String smallCategory, int input, int output, Account account) {
        this.id = id;
        this.brandName = brandName;
        this.brandImg = brandImg;
        this.bigCategory = bigCategory;
        this.smallCategory = smallCategory;
        this.input = input;
        this.output = output;
        this.account = account;
    }
}
