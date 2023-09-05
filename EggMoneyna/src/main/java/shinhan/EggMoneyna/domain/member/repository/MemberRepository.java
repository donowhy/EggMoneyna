package shinhan.EggMoneyna.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shinhan.EggMoneyna.domain.member.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    Optional<Member> findByRefreshToken(String refreshToken);

}
