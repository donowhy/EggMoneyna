package shinhan.EggMoneyna.user.parent.repository;

import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import shinhan.EggMoneyna.user.child.entity.Child;
import shinhan.EggMoneyna.user.parent.entity.Parent;

import java.util.List;
import java.util.Optional;

public interface ParentRepository extends JpaRepository<Parent, Long> {

    @Query("SELECT p from Parent p WHERE p.parentName = :parentName and p.password = :password")
    Optional<Parent> checkParentPw(@Param("parentName") String parentName, @Param("password") String password);

    @Query("SELECT r.child from Relation r WHERE r.parent.id = :id and r.child.eggMoney = false")
    List<Child> findChildrenByParentId(@Param("id") Long id);

    @Query("SELECT r.child from Relation r WHERE r.parent.id = :id and r.child.eggMoney = true")
    List<Child> findChildrenEggMoneyNa(@Param("id") Long id);
}
