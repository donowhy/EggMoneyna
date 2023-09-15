package shinhan.EggMoneyna.inputoutput.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MonthOutputResponseDto {
    private int totalMonthOutput;

    @Builder
    public MonthOutputResponseDto(int totalMonthOutput) {
        this.totalMonthOutput = totalMonthOutput;
    }
}
