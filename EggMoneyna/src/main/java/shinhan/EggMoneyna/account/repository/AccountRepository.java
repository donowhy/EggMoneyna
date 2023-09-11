package shinhan.EggMoneyna.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shinhan.EggMoneyna.account.entity.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByAccountNumber(Long accountNumber);

}
