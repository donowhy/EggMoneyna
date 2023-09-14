package shinhan.EggMoneyna.monster.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;
import shinhan.EggMoneyna.global.response.Response;
import shinhan.EggMoneyna.jwt.UserInfo;
import shinhan.EggMoneyna.jwt.UsersInfo;
import shinhan.EggMoneyna.monster.dto.MonsterResponseDto;
import shinhan.EggMoneyna.monster.dto.MonsterSaveRequestDto;
import shinhan.EggMoneyna.monster.dto.MonsterSaveResponseDto;
import shinhan.EggMoneyna.monster.service.MonsterService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/monsters")
//@RequestMapping
public class MonsterController {
    private final MonsterService monsterService;

    // 몬스터 생성
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "신한몽 생성", description = "신한몽이 생성됩니다.", tags = { "Monster Controller" })
    @PostMapping("")
    public Response<?> createMonster(@RequestBody MonsterSaveRequestDto requestDto, @UserInfo UsersInfo usersInfo) {
        MonsterSaveResponseDto responseDto = monsterService.save(requestDto, usersInfo.getId());
        return new Response<>("성공", "신한몽 생성", responseDto);
    }

    // READ
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "신한몽 조회", description = "신한몽 조회.", tags = { "Monster Controller" })
    @GetMapping("")
    public Response<?> getMonster(@UserInfo UsersInfo usersInfo) {
        MonsterResponseDto byId = monsterService.findById(usersInfo.getId());
        return new Response<>("성공", "신한몽 조회", byId);
    }

    // DELETE
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "신한몽 삭제", description = "신한몽이 삭제.", tags = { "Monster Controller" })
    @DeleteMapping("/{id}")
    public Response<String> deleteMonster(@UserInfo UsersInfo usersInfo) {
        monsterService.deleteById(usersInfo.getId());
        return new Response<>("성공", "삭제 성공","삭제 성공");
    }


}
