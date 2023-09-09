package shinhan.EggMoneyna.users.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import shinhan.EggMoneyna.jwt.JwtProvider;
import shinhan.EggMoneyna.jwt.UsersInfo;
import shinhan.EggMoneyna.jwt.UserInfo;
import shinhan.EggMoneyna.users.dto.SignUpRequestDto;
import shinhan.EggMoneyna.users.dto.UpdateRequestDto;
import shinhan.EggMoneyna.users.dto.returnTokenDto;
import shinhan.EggMoneyna.users.entity.Users;
import shinhan.EggMoneyna.users.service.UsersService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class UsersController {

    private final UsersService usersService;

    @PostMapping("/users/save")
    public returnTokenDto save (@RequestBody SignUpRequestDto signUpRequestDto) throws Exception {
        return usersService.save(signUpRequestDto);
    }

    @PutMapping("/users/update")
    public Users updateUser (@RequestBody UpdateRequestDto updateRequestDto, @UserInfo UsersInfo usersInfo) throws Exception {
        return usersService.update(usersInfo.getId(), updateRequestDto);
    }

    @GetMapping("/users/findUser")
    public Users getUser (@UserInfo UsersInfo usersInfo) throws Exception {
        return usersService.findUser(usersInfo.getId());
    }
}
