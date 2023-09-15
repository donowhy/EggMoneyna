package shinhan.EggMoneyna.user.follow.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shinhan.EggMoneyna.user.follow.entity.Relation;

public interface RelationRepository extends JpaRepository<Relation, Long> {

	@Query("SELECT r FROM Relation r WHERE r.child.id = :childId AND r.parent.id = :parentId")
	Optional<Relation> findRelationByParentIdAndChildId(@Param("childId") Long childId, @Param("parentId") Long parentId);
}
