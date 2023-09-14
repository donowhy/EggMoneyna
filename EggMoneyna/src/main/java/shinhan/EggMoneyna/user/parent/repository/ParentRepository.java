package shinhan.EggMoneyna.user.parent.repository;

import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import shinhan.EggMoneyna.user.parent.entity.Parent;

import java.util.Optional;

public interface ParentRepository extends JpaRepository<Parent, Long> {

    @Query("SELECT p from Parent p WHERE p.parentId = :parentId and p.password = :password")
    Optional<Parent> checkParentPw(@Param("parentId") String parentId, @Param("password") String password);
}
