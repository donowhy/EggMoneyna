package shinhan.EggMoneyna.comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import shinhan.EggMoneyna.comment.entity.Comment;
import shinhan.EggMoneyna.inputoutput.entity.InputOutput;
import shinhan.EggMoneyna.users.entity.Users;

@Getter
@NoArgsConstructor
public class CommentRequestDto {
    private String comment;

    public Comment of(InputOutput inputOutput) {
        return Comment.builder()
                .childComment(comment)
                .isChild(true)
                .inputOutput(inputOutput)
                .build();
    }
}
