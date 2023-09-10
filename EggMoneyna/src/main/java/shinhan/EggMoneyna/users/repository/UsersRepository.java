package shinhan.EggMoneyna.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shinhan.EggMoneyna.users.entity.Users;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUserId(String id);
}
