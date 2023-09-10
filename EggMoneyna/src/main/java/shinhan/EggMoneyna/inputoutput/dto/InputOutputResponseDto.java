package shinhan.EggMoneyna.inputoutput.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InputOutputResponseDto {
//    private String brandName;
//    private String brandImg;
    private String bigCategory;
    private String smallCategory;
    private int input;
    private int output;

    @Builder
    public InputOutputResponseDto(String brandName, String brandImg, String bigCategory, String smallCategory, int input, int output) {
//        this.brandName = brandName;
//        this.brandImg = brandImg;
        this.bigCategory = bigCategory;
        this.smallCategory = smallCategory;
        this.input = input;
        this.output = output;
    }
}
