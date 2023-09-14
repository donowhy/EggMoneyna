package shinhan.EggMoneyna.comment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ComplimentResponseDto {
    private boolean compliment;

    @Builder
    public ComplimentResponseDto(boolean compliment) {
        this.compliment = compliment;
    }
}
