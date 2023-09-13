package shinhan.EggMoneyna.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private String childComment = "";
    private String parentComment = "";
    private boolean compliment = false;

    @Builder
    public CommentResponseDto(String childComment, String parentComment, boolean compliment) {
        this.childComment = childComment;
        this.parentComment = parentComment;
        this.compliment = compliment;
    }
}
