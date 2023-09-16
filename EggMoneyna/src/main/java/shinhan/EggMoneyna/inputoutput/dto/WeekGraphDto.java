package shinhan.EggMoneyna.inputoutput.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class WeekGraphDto {
    private LocalDate date;
    private int totalInput;
    private int totalOutput;

    @Builder
    public WeekGraphDto(LocalDate date, int totalInput, int totalOutput) {
        this.date = date;
        this.totalInput = totalInput;
        this.totalOutput = totalOutput;
    }
}
