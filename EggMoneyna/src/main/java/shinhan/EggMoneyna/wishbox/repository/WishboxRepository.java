package shinhan.EggMoneyna.wishbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shinhan.EggMoneyna.wishbox.entity.WishBox;

public interface WishboxRepository extends JpaRepository<WishBox, Long> {


    @Query("SELECT CASE WHEN COUNT(w) > 0 THEN TRUE ELSE FALSE END FROM WishBox w WHERE w.virtualNumber = :virtualNumber AND w.account.id = :accountId")
    Boolean existsByVirtualNumberAndAccountId(@Param("virtualNumber") Long virtualNumber, @Param("accountId") Long accountId);


}
