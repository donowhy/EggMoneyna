package shinhan.EggMoneyna.user.child.repository;

import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import shinhan.EggMoneyna.user.child.entity.Child;

import java.util.Optional;

public interface ChildRepository extends JpaRepository<Child, Long> {
    Optional<Child> findChildByChildName(String childName);

    @Query("SELECT c from Child c WHERE c.childName = :childName and c.password = :password")
    Optional<Child> checkChildPw(@Param("childName") String childName, @Param("password") String password);

    @Query("SELECT c FROM Child c JOIN FETCH c.account WHERE c.id = :id")
    Optional<Child> findByIdWithAccount(@Param("id") Long id);

}


