package shinhan.EggMoneyna.comment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private String childNickname = "자녀";
    private String childComment = "";
    private LocalDateTime childCommentCreateTime;
    private String parentNickname = "엄마";
    private String parentComment = "";
    private LocalDateTime parentCommentCreateTime;
    private boolean compliment = false;

    @Builder
    public CommentResponseDto(String childNickname, String childComment, LocalDateTime childCommentCreateTime, String parentNickname, String parentComment, LocalDateTime parentCommentCreateTime, boolean compliment) {
        this.childNickname = childNickname;
        this.childComment = childComment;
        this.childCommentCreateTime = childCommentCreateTime;
        this.parentNickname = parentNickname;
        this.parentComment = parentComment;
        this.parentCommentCreateTime = parentCommentCreateTime;
        this.compliment = compliment;
    }
}
