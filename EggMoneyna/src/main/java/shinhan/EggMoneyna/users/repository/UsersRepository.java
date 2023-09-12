package shinhan.EggMoneyna.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shinhan.EggMoneyna.users.entity.Users;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUserId(String id);

    @Query("SELECT u FROM Users u JOIN FETCH u.children WHERE u.id = :parentId")
    List<Users> findChildrenByParentId(@Param("parentId") Long parentId);

    @Query("SELECT u FROM Users u JOIN FETCH u.children c WHERE u.id = :parentId AND c.id = :childId")
    Optional<Users> findChildByParentId(@Param("parentId") Long parentId, @Param("childId") Long childId);

}
