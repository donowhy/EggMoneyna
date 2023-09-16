package shinhan.EggMoneyna.comment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shinhan.EggMoneyna.user.child.entity.Child;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class Compliment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate complimentDate;

    private boolean isCompliment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "childs_default_id")
    private Child child;

    @Builder
    public Compliment(Long id, LocalDate complimentDate, boolean isCompliment, Child child) {
        this.id = id;
        this.complimentDate = complimentDate;
        this.isCompliment = isCompliment;
        this.child = child;
    }
}
