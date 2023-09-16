package shinhan.EggMoneyna.comment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ComplimentMonthDto {
    private LocalDate localDate;
    private boolean compliment;

    @Builder
    public ComplimentMonthDto(LocalDate localDate, boolean compliment) {
        this.localDate = localDate;
        this.compliment = compliment;
    }
}
