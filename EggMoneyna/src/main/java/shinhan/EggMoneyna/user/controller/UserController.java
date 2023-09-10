package shinhan.EggMoneyna.user.controller;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shinhan.EggMoneyna.user.dto.*;
import shinhan.EggMoneyna.user.service.UserService;

@RestController
@RequestMapping("/v1/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원 가입", description = "회원 가입은 공통으로 진행", tags = { "User Controller" })
    @PostMapping("signup")
    public ResponseEntity<UserSaveResponseDto> createUser(@RequestBody UserSaveRequestDto userSaveRequestDto) {
        return ResponseEntity.ok(userService.save(userSaveRequestDto));
    }

    @Operation(summary = "부모 -> 아이, 아이 -> 카드 별명 수정", description = "가입할 때 생긴 별명 변경 가능.", tags = { "User Controller" })
    @PutMapping("/{id}/nickname")
    public ResponseEntity<UserUpdateResponseDto> updateNickname(@PathVariable Long id, @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        return ResponseEntity.ok(userService.nickNameUpdate(id, userUpdateRequestDto));
    }

    @Operation(summary = "회원 정보 조회", description = "나의 정보 조회", tags = { "User Controller" })
    @GetMapping("/{id}/user")
    public ResponseEntity<UserResponseDto> getUserInfo(@PathVariable Long id) {
        return ResponseEntity.ok(userService.userRead(id));
    }
}

