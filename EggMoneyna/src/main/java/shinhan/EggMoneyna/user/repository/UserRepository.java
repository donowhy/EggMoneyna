package shinhan.EggMoneyna.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shinhan.EggMoneyna.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {


}
