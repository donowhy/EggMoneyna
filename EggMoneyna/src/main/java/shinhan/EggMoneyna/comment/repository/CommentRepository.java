package shinhan.EggMoneyna.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shinhan.EggMoneyna.comment.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
