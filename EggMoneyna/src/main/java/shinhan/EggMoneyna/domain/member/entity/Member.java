package shinhan.EggMoneyna.domain.member.entity;



import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import shinhan.EggMoneyna.domain.common.BaseEntity;
import shinhan.EggMoneyna.domain.member.constant.MemberType;
import shinhan.EggMoneyna.domain.member.constant.Role;
import shinhan.EggMoneyna.global.jwt.dto.JwtTokenDto;
import shinhan.EggMoneyna.global.util.DateTimeUtils;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false, length = 10)
//    private MemberType memberType;

    @Column(unique = true, length = 50, nullable = false)
    private String email;

    @Column(nullable = false, length = 20)
    private String memberName;

    @ColumnDefault("false")
    private Boolean isChecked;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Role role;

    @Column(length = 250)
    private String refreshToken;

    private LocalDateTime tokenExpirationTime;

    @Builder
    public Member(Long memberId, String email, String memberName, Boolean isChecked, Role role, String refreshToken, LocalDateTime tokenExpirationTime) {
        this.memberId = memberId;
//        this.memberType = memberType;
        this.email = email;
        this.memberName = memberName;
        this.isChecked = isChecked;
//        this.profile = profile;
        this.role = role;
        this.refreshToken = refreshToken;
        this.tokenExpirationTime = tokenExpirationTime;
    }

    public void updateRefreshToken(JwtTokenDto jwtTokenDto) {
        this.refreshToken = jwtTokenDto.getRefreshToken();
        this.tokenExpirationTime = DateTimeUtils.convertToLocalDateTime(jwtTokenDto.getRefreshTokenExpireTime());
    }

    public void expireRefreshToken(LocalDateTime now) {
        this.tokenExpirationTime = now;
    }

}
