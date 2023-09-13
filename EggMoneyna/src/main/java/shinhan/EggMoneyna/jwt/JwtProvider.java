package shinhan.EggMoneyna.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import shinhan.EggMoneyna.user.child.entity.Child;
import shinhan.EggMoneyna.user.parent.entity.Parent;
import shinhan.EggMoneyna.users.entity.Users;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;


@Component
@RequiredArgsConstructor
public class JwtProvider {

    @Value("${jwt.password}")
    private String secretKey;

    private static final long TOKEN_VALID_TIME = 24 * 60 * 60 * 1000L;
    private static final long REFRESH_TOKEN_VALID_TIME = 30 * 24 * 60 * 60 * 1000L;

    //==토큰 생성 메소드==//
    public String createChildToken(Child child) {
        Date now = new Date();
        // 해커톤을 위해 7일로 설정
        Date expiration = new Date(now.getTime() + Duration.ofDays(7).toMillis()); // 만료기간 7일

        Claims claims = Jwts.claims();
        claims.put("id", child.getId());
        claims.put("username", child.getChildId());


        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // (1)
                .setClaims(claims)
//                .setIssuer("test") // 토큰발급자(iss)
                .setIssuedAt(now) // 발급시간(iat)
                .setExpiration(expiration) // 만료시간(exp)
//                .setSubject(subject) //  토큰 제목(subject)
                .signWith(
//                        SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secretKey.getBytes())
                        Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)),
                        SignatureAlgorithm.HS256
                        ) // 알고리즘, 시크릿 키
                .compact();
    }

    public String createParentToken(Parent member) {
        Date now = new Date();
        // 해커톤을 위해 7일로 설정
        Date expiration = new Date(now.getTime() + Duration.ofDays(7).toMillis()); // 만료기간 1일

        Claims claims = Jwts.claims();
        claims.put("id", member.getId());
        claims.put("parentName", member.getParentId());


        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // (1)
                .setClaims(claims)
                .setIssuedAt(now) // 발급시간(iat)
                .setExpiration(expiration) // 만료시간(exp)
                .signWith(
                        Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)),
                        SignatureAlgorithm.HS256
                ) // 알고리즘, 시크릿 키
                .compact();
    }

    //==Jwt 토큰의 유효성 체크 메소드==//
//    public Claims parseJwtToken(String token) {
//        token = BearerRemove(token); // Bearer 제거
//        return Jwts.parser()
////                .setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes()))
//                .setSigningKey(secretKey)
//                .parseClaimsJws(token)
//                .getBody();
//    }

    //==토큰 앞 부분('Bearer') 제거 메소드==//
    private String BearerRemove(String token) {
        return token.substring("Bearer ".length());
    }

    //토큰검증
    public boolean validateToken(String token) {
        try {
            return !Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseClaimsJws(BearerRemove(token))
                    .getBody()
                    .getExpiration().before(new Date());

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("토큰 검증 실패");
        }
    }

    public UsersInfo getClaim(String token) {
        Claims claimsBody = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(removeBearer(token))
                .getBody();

        return UsersInfo.builder()
                .id(Long.valueOf((Integer) claimsBody.getOrDefault("id", 0L)))
                .username(claimsBody.getOrDefault("username", "").toString())
                .build();
    }

    private String removeBearer(String token) {
        return token.replace("Bearer", "").trim();
    }
}