package shinhan.EggMoneyna.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/token/none")
    public String none() {
        log.info("token none");
        return "success";
    }

    @GetMapping("/token/required")
    public String required(
            @UserInfo UsersInfo usersInfo
    ) {
        log.info("token payload : {}", usersInfo);
        return "success";
    }
}
