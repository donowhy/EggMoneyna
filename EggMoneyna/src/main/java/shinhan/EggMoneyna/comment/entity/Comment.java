package shinhan.EggMoneyna.comment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shinhan.EggMoneyna.global.common.BaseTimeEntity;
import shinhan.EggMoneyna.inputoutput.entity.InputOutput;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Boolean compliment;

    @Column(nullable = false)
    private String parentComment;

    @Column(nullable = false)
    private String childComment;

    private Boolean isParent;

    private Boolean isChild;

    private LocalDateTime childCommentCreateTime;

    private LocalDateTime parentCommentCreateTime;

    @OneToOne(mappedBy = "comment")
    @JsonIgnore
    private InputOutput inputOutput;

    @Builder
    public Comment(Long id, Boolean compliment, String parentComment, String childComment, Boolean isParent, Boolean isChild, InputOutput inputOutput, LocalDateTime childCommentCreateTime, LocalDateTime parentCommentCreateTime) {
        this.id = id;
        this.compliment = compliment;
        this.parentComment = parentComment;
        this.childComment = childComment;
        this.isParent = isParent;
        this.isChild = isChild;
        this.inputOutput = inputOutput;
        this.childCommentCreateTime = childCommentCreateTime;
        this.parentCommentCreateTime = parentCommentCreateTime;
    }

    public void addChildComment(String childComment) {
        this.childComment = childComment;
        this.childCommentCreateTime = LocalDateTime.now();
    }

    public void addParentComment(String parentComment) {
        this.parentComment = parentComment;
        this.parentCommentCreateTime = LocalDateTime.now();
    }

    public void removeChildComment() {
        this.childComment = "";
        this.childCommentCreateTime = null;
    }

    public void removeParentComment() {
        this.parentComment = "";
        this.parentCommentCreateTime = null;
    }

    public void switchCompliment(boolean tf) {
        this.compliment = tf;
    }
}
