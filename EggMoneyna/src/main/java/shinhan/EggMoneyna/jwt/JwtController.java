package shinhan.EggMoneyna.jwt;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JwtController {

    private final JwtService jwtService;
    private final JwtProvider jwtProvider;

    @GetMapping(value = "/checkToken")
    public JwtService.TokenResponseNoData checkToken(@RequestHeader(value = "Authorization") String token) throws Exception {
        Claims claims = jwtProvider.parseJwtToken(token);

        JwtService.TokenResponseNoData tokenResponseNoData = new JwtService.TokenResponseNoData("200", "success");
        return tokenResponseNoData;
    }
}
