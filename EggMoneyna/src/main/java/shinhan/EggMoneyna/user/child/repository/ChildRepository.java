package shinhan.EggMoneyna.user.child.repository;

import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import shinhan.EggMoneyna.user.child.entity.Child;

import java.util.Optional;

public interface ChildRepository extends JpaRepository<Child, Long> {
    Optional<Child> findChildByChildId(String childId);

    @Query("SELECT c from Child c WHERE c.childId = :childId and c.password = :password")
    Optional<Child> checkChildPw(@Param("childId") String childId, @Param("password") String password);

}


