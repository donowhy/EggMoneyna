package shinhan.EggMoneyna.comment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shinhan.EggMoneyna.global.common.BaseTimeEntity;
import shinhan.EggMoneyna.inputoutput.entity.InputOutput;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean compliment;

    private String comment;

    private Boolean isParent;

    private Boolean isChild;

    @OneToOne(mappedBy = "comment")
    @JsonIgnore
    private InputOutput inputOutput;
}
