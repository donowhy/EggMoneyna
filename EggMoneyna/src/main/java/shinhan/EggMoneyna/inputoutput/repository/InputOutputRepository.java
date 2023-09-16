package shinhan.EggMoneyna.inputoutput.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shinhan.EggMoneyna.account.entity.Account;
import shinhan.EggMoneyna.inputoutput.entity.InputOutput;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface InputOutputRepository extends JpaRepository<InputOutput, Long> {
    //    /**
//    ** 1원 계좌 조회
//     */
    @Query("SELECT ia FROM InputOutput ia JOIN FETCH ia.account acc WHERE acc = :account AND ia.brandName = :brandName ORDER BY ia.createTime DESC")
    List<InputOutput> findLatestByAccountAndSendUser(@Param("account") Account account, @Param("brandName") String brandName);
//    /**
//     ** 기간에 입출금 내역 조회
//     */
//    @Query("SELECT ia FROM InputOutput ia JOIN FETCH ia.account acc WHERE acc = :account AND ia.createTime BETWEEN :startDate AND :endDate")
//    Page<InAccount> findByAccountAndInAccount(@Param("account") Account account, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, Pageable pageable);

    List<InputOutput> findByAccountAndOutputAndCreateTimeBetween(Account account, int output, LocalDateTime startOfDay, LocalDateTime endOfDay);

    List<InputOutput> findByAccountAndInputAndCreateTimeBetween(Account account, int input, LocalDateTime startOfDay, LocalDateTime endOfDay);

    List<InputOutput> findByAccountAndCreateTimeBetween(Account account, LocalDateTime startOfDay, LocalDateTime endOfDay);

    @Query("SELECT io FROM InputOutput io WHERE io.account = :account AND io.createTime BETWEEN :startOfMonth AND :now ORDER BY io.createTime DESC")
    List<InputOutput> findInputOutputsByTime(@Param("account") Account account, @Param("startOfMonth") LocalDate startOfMonth, @Param("now") LocalDate now);


}