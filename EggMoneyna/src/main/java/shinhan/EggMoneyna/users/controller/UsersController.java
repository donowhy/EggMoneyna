package shinhan.EggMoneyna.users.controller;

import io.swagger.v3.oas.annotations.Operation;
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
import shinhan.EggMoneyna.users.dto.UpdateLimitMoneyRequestDto;
import shinhan.EggMoneyna.users.dto.UpdateRequestDto;
import shinhan.EggMoneyna.users.dto.returnTokenDto;
import shinhan.EggMoneyna.users.entity.Users;
import shinhan.EggMoneyna.users.service.UsersService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/users")
public class UsersController {

    private final UsersService usersService;

    @Operation(summary = "부모, 아이 생성 및 아이 계좌 생성",
            description = "기존에 sol에서 가입이 되어있고, 계좌도 있다는 가정하기 때문에 부모와 아이의 계정를 생성하고, 둘 사이의 관계를 이어준다, 또한,아이의 계좌를 생성한다. ",
            tags = { "Users Controller" })
    @PostMapping("/save")
    public returnTokenDto save (@RequestBody SignUpRequestDto signUpRequestDto) throws Exception {
        return usersService.save(signUpRequestDto);
    }

    @Operation(summary = "아이의 닉네임, 용돈, 용돈 주는 날짜를 변경", description = "아이의 닉네임, 용돈, 용돈 주는 날짜를 변경", tags = { "Users Controller" })
    @PutMapping("/atParent")
    public Users updateUser (@RequestBody UpdateRequestDto updateRequestDto, @UserInfo UsersInfo usersInfo) throws Exception {
        return usersService.update(usersInfo.getId(), updateRequestDto);
    }

    @Operation(summary = "임계치 변경", description = "아이의 입장에서 임계치를 정한다..", tags = { "Users Controller" })
    @PutMapping("/limitMoney")
    public Users updateUser (@RequestBody UpdateLimitMoneyRequestDto requestDto, @UserInfo UsersInfo usersInfo) throws Exception {
        return usersService.updateLimitMoney(usersInfo.getId(), requestDto);
    }


    @Operation(summary = "내 정보 조회", description = "내 정보를 조회한다..", tags = { "Users Controller" })
    @GetMapping("/findUser")
    public Users getUser (@UserInfo UsersInfo usersInfo) throws Exception {
        return usersService.findUser(usersInfo.getId());
    }

    @Operation(summary = "내 아이를 조회한다. (리스트 형식)", description = "내 아이들을 조회한다.", tags = { "Users Controller" })
    @GetMapping("/findMyChild")
    public List<Users> myChild(@UserInfo UsersInfo usersInfo){
        return usersService.myChilds(usersInfo.getId());
    }

    @Operation(summary = "내 아이 조회", description = "내 아이 하나에 대해 조회한다", tags = { "Users Controller" })
    @GetMapping("/findOneMyChild")
    public Users myOneChild(@UserInfo UsersInfo usersInfo, @RequestParam Long id){
        return usersService.myOneChilds(usersInfo.getId(), id);
    }

    @Operation(summary = "아이 추가 ", description = "아이가 하나에서 둘이 될 때 추가로 매핑해준다.", tags = { "Users Controller" })
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
    @Operation(summary = "부모 편입", description = "가정한다면 부모 중 한 분이 먼저 아이와 매핑이 되어있고 그 후 다른 한 분이 들어올 때", tags = { "Users Controller" })
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
