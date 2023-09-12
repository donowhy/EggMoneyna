package shinhan.EggMoneyna.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shinhan.EggMoneyna.account.entity.Account;
import shinhan.EggMoneyna.users.entity.Users;

import java.util.Optional;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByAccountNumber(Long accountNumber);

    Optional<Account> findByUsers(Users users);
}
