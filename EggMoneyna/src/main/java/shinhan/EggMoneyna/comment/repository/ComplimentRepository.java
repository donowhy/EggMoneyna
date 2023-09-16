package shinhan.EggMoneyna.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shinhan.EggMoneyna.comment.entity.Compliment;

import java.time.LocalDate;
import java.util.Optional;

public interface ComplimentRepository extends JpaRepository<Compliment, Long> {
    Optional<Compliment> findByComplimentDate(LocalDate localDate);
}
