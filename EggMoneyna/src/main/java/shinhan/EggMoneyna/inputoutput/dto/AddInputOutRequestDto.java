package shinhan.EggMoneyna.inputoutput.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shinhan.EggMoneyna.account.entity.Account;
import shinhan.EggMoneyna.inputoutput.entity.InputOutput;

@Getter
@NoArgsConstructor
public class AddInputOutRequestDto {
    private String brandName;
    private String brandImg;
    private String bigCategory;
    private String smallCategory;
    private int input;
    private int output;

    @Builder
    public AddInputOutRequestDto(String brandName, String brandImg, String bigCategory, String smallCategory, int input, int output) {
        this.brandName = brandName;
        this.brandImg = brandImg;
        this.bigCategory = bigCategory;
        this.smallCategory = smallCategory;
        this.input = input;
        this.output = output;
    }

    public InputOutput of(Account account) {
        return InputOutput.builder()
                .brandName(brandName)
                .brandImg(brandImg)
                .bigCategory(bigCategory)
                .smallCategory(smallCategory)
                .input(input)
                .output(output)
                .account(account).build();
    }
}
