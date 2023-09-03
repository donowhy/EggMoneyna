package shinhan.EggMoneyna.monster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shinhan.EggMoneyna.monster.entity.Monster;

public interface MonsterRepository extends JpaRepository<Monster, Long> {
}
