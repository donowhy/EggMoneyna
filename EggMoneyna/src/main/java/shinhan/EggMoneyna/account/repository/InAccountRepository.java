package shinhan.EggMoneyna.account.repository;

import com.google.api.gax.paging.Page;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shinhan.EggMoneyna.account.entity.Account;
import shinhan.EggMoneyna.account.entity.InAccount;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface InAccountRepository extends JpaRepository<InAccount, Long> {

    List<InAccount> findAllByAccount(Account account);

    @Query("SELECT ia FROM InAccount ia JOIN FETCH ia.account acc WHERE acc = :account AND ia.sendUser = :sendUser ORDER BY ia.createTime DESC")
    List<InAccount> findLatestByAccountAndSendUser(@Param("account") Account account, @Param("sendUser") String sendUser);
}
