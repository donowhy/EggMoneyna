package shinhan.EggMoneyna.monster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shinhan.EggMoneyna.monster.entity.History;

public interface HistoryRepository extends JpaRepository<History, Long> {
}
