package shinhan.EggMoneyna.inputoutput.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shinhan.EggMoneyna.inputoutput.entity.InputOutput;

import java.util.List;

@Getter
@NoArgsConstructor
public class InputOutputResponseDto {
    private List<InputOutput> inputOutputs;

    @Builder
    public InputOutputResponseDto(List<InputOutput> inputOutputs) {
        this.inputOutputs = inputOutputs;
    }
}
