package shinhan.EggMoneyna.users.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import shinhan.EggMoneyna.jwt.JwtProvider;
import shinhan.EggMoneyna.jwt.JwtService;
import shinhan.EggMoneyna.users.dto.SignUpRequestDto;
import shinhan.EggMoneyna.users.dto.UpdateRequestDto;
import shinhan.EggMoneyna.users.entity.Users;
import shinhan.EggMoneyna.users.service.UsersService;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class UsersController {

    private final UsersService usersService;
    private final JwtProvider jwtProvider;

    @PostMapping("/users/save")
    public String save (@RequestBody SignUpRequestDto signUpRequestDto) throws Exception {
        return usersService.save(signUpRequestDto);
    }

    // @PostMapping("/users/{id}")
    // public Users login (@PathVariable Long id) throws Exception {
    //     return usersService.login(id);
    // }

    @PutMapping("/users/update")
    public Users updateUser (@RequestBody UpdateRequestDto updateRequestDto, HttpServletRequest httpServletRequest) throws Exception {
        String header = httpServletRequest.getHeader("Authorization");
        log.info("header = {}",header);
        log.info("parseJwtToken = {}",jwtProvider.parseJwtToken(header));
        String id = (String) jwtProvider.parseJwtToken(header).get("sub");

        return usersService.update(id, updateRequestDto);
    }
}
