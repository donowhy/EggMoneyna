package shinhan.EggMoneyna.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shinhan.EggMoneyna.comment.entity.Comment;
import shinhan.EggMoneyna.inputoutput.entity.InputOutput;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByInputOutput(InputOutput inputOutput);
}
