package shinhan.EggMoneyna.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shinhan.EggMoneyna.account.entity.Account;
import shinhan.EggMoneyna.user.child.entity.Child;
import shinhan.EggMoneyna.users.entity.Users;

import java.util.Optional;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findAccountByAccountNumber(Long accountNumber);

    @Query("SELECT a FROM Account a where a.accountNumber = :accountNumber")
    Optional<Account> findByAccountNumber(@Param("accountNumber") Long accountNumber);

    Optional<Account> findByUsers(Users users);

    Optional<Account> findByChild(Child child);
}
