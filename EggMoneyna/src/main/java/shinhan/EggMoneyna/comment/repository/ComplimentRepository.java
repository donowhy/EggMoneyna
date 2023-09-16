package shinhan.EggMoneyna.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shinhan.EggMoneyna.comment.entity.Compliment;
import shinhan.EggMoneyna.user.child.entity.Child;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ComplimentRepository extends JpaRepository<Compliment, Long> {
    Optional<Compliment> findByComplimentDate(LocalDate localDate);

    List<Compliment> findByChildAndComplimentDateBetween(Child child, LocalDate startOfDay, LocalDate endOfDay);
}
