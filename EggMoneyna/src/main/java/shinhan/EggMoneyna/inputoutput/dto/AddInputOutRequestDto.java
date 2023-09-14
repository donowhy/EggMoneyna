package shinhan.EggMoneyna.inputoutput.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shinhan.EggMoneyna.account.entity.Account;
import shinhan.EggMoneyna.comment.entity.Comment;
import shinhan.EggMoneyna.inputoutput.entity.InputOutput;

@Getter
@NoArgsConstructor
public class AddInputOutRequestDto {
    private String brandName;
    private int input;
    private int output;

    @Builder
    public AddInputOutRequestDto(String brandName, int input, int output) {
        this.brandName = brandName;
        this.input = input;
        this.output = output;
    }

    public InputOutput of(Account account, Comment comment, String bigCategory, String smallCategory, String brandImage) {
        return InputOutput.builder()
                .brandName(brandName)
                .brandImg(brandImage)
                .bigCategory(bigCategory)
                .smallCategory(smallCategory)
                .input(input)
                .output(output)
                .comment(comment)
                .account(account).build();
    }
}
