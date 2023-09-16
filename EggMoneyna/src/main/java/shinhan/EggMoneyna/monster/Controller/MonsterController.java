package shinhan.EggMoneyna.monster.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;
import shinhan.EggMoneyna.global.response.Response;
import shinhan.EggMoneyna.jwt.UserInfo;
import shinhan.EggMoneyna.jwt.UsersInfo;
import shinhan.EggMoneyna.monster.dto.*;
import shinhan.EggMoneyna.monster.service.MonsterEncyclopediaService;
import shinhan.EggMoneyna.monster.service.MonsterService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/monsters")
//@RequestMapping
public class MonsterController {
    private final MonsterService monsterService;
    private final MonsterEncyclopediaService monsterEncyclopediaService;
    // 몬스터 생성
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "신한몽 생성",
            description = "신한몽이 하나도 없을 때 생성, 하나라도 있을 때는 오류 발생." +
                    "신한몽이 도감에 등록 될때도 -1로 하여 생성하도록 한다.",
            tags = { "Monster Controller" })
    @PostMapping("/save")
    public Response<?> createMonster(@RequestBody MonsterSaveRequestDto requestDto, @UserInfo UsersInfo usersInfo) {
        MonsterSaveResponseDto responseDto = monsterService.save(requestDto, usersInfo.getId());
        return new Response<>("성공", "신한몽 생성", responseDto);
    }

    // READ
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "신한몽 조회", description = "신한몽 조회시 출석체크 후 최근 일주일 출석에 따른 기분이 변하도록 함.", tags = { "Monster Controller" })
    @GetMapping("/getMyMong")
    public Response<?> getMonster(@UserInfo UsersInfo usersInfo) {
        MonsterResponseDto byId = monsterService.findById(usersInfo.getId());
        return new Response<>("성공", "신한몽 조회", byId);
    }

    // DELETE
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "신한몽 삭제", description = "신한몽이 삭제시 신한몽 카운트 -1 로 0이 된다.", tags = { "Monster Controller" })
    @DeleteMapping("")
    public Response<String> deleteMonster(@UserInfo UsersInfo usersInfo) {
        monsterService.deleteById(usersInfo.getId());
        return new Response<>("성공", "삭제 성공","삭제 성공");
    }


    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "몬스터 디테일 조회 ", description = "몬스터 디테일 조회.", tags = { "Monster Controller" })
    @GetMapping("/getMyMongDetail")
    public MonsterDetail monsterDetail (@UserInfo UsersInfo usersInfo) {
        return monsterService.monsterDetail(usersInfo.getId());
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "몬스터 도감 디테일 조회 ", description = "몬스터 도감 디테일 조회.", tags = { "Monster Controller" })
    @GetMapping("/getDogamDetail")
    public MonsterDetailResponse monsterDetail (@UserInfo UsersInfo usersInfo, @RequestBody MonsterDetailRequest request) {
        return monsterEncyclopediaService.monsterDetail(usersInfo.getId(), request);
    }
}
