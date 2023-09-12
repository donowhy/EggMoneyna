package shinhan.EggMoneyna.inputoutput.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import shinhan.EggMoneyna.account.entity.Account;
import shinhan.EggMoneyna.inputoutput.entity.InputOutput;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;

public interface InputOutputRepository extends JpaRepository<InputOutput, Long> {
//    /**
//    ** 1원 계좌 조회
//     */
//    @Query("SELECT ia FROM InputOutput ia JOIN FETCH ia.account acc WHERE acc = :account AND ia.brandName = :sendUser ORDER BY ia.createTime DESC")
//    List<InAccount> findLatestByAccountAndSendUser(@Param("account") Account account, @Param("sendUser") String sendUser, Pageable pageable);
//    /**
//     ** 기간에 입출금 내역 조회
//     */
//    @Query("SELECT ia FROM InputOutput ia JOIN FETCH ia.account acc WHERE acc = :account AND ia.createTime BETWEEN :startDate AND :endDate")
//    Page<InAccount> findByAccountAndInAccount(@Param("account") Account account, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, Pageable pageable);

    List<InputOutput> findByAccountAndOutputAndCreateTimeBetween(Account account, int output, LocalDateTime startOfDay, LocalDateTime endOfDay);

    List<InputOutput> findByAccountAndInputAndCreateTimeBetween(Account account, int input, LocalDateTime startOfDay, LocalDateTime endOfDay);

    List<InputOutput> findByAccountAndCreateTimeBetween(Account account, LocalDateTime startOfDay, LocalDateTime endOfDay);
}
