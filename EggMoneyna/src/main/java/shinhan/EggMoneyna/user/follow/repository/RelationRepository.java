package shinhan.EggMoneyna.user.follow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shinhan.EggMoneyna.user.follow.entity.Relation;

public interface RelationRepository extends JpaRepository<Relation, Long> {
}
