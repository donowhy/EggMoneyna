package shinhan.EggMoneyna.users.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shinhan.EggMoneyna.jwt.JwtProvider;
import shinhan.EggMoneyna.jwt.UsersInfo;
import shinhan.EggMoneyna.jwt.UserInfo;
import shinhan.EggMoneyna.users.dto.SignUpRequestDto;
import shinhan.EggMoneyna.users.dto.UpdateRequestDto;
import shinhan.EggMoneyna.users.dto.returnTokenDto;
import shinhan.EggMoneyna.users.entity.Users;
import shinhan.EggMoneyna.users.service.UsersService;

import java.util.List;

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

    @GetMapping("/users/findMyChild")
    public List<Users> myChild(@UserInfo UsersInfo usersInfo){
        return usersService.myChilds(usersInfo.getId());
    }

    @GetMapping("/users/findOneMyChild")
    public Users myOneChild(@UserInfo UsersInfo usersInfo, @RequestParam Long id){
        return usersService.myOneChilds(usersInfo.getId(), id);
    }

    @PostMapping("/add-child")
    public ResponseEntity<String> addChildToParent(@UserInfo UsersInfo usersInfo, @RequestBody SignUpRequestDto signUpRequestDto) {
        try {
            usersService.addChild(usersInfo.getId(), signUpRequestDto);
            return ResponseEntity.ok("Child has been added to parent successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // 한 부모가 이미 등록된 여러 아이들에게 추가로 연결되는 경우
    @PostMapping("/link-children")
    public ResponseEntity<String> linkChildrenToParent(@RequestParam Long parentId, @RequestParam List<Long> childrenIds) {
        try {
            usersService.linkChildrenToParent(parentId, childrenIds);
            return ResponseEntity.ok("Children have been linked to parent successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
