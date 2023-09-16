package shinhan.EggMoneyna.comment.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    @Builder
    public Compliment(Long id, LocalDate complimentDate, boolean isCompliment) {
        this.id = id;
        this.complimentDate = complimentDate;
        this.isCompliment = isCompliment;
    }
}
