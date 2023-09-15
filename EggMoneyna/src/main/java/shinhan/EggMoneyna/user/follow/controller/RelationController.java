package shinhan.EggMoneyna.user.follow.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shinhan.EggMoneyna.jwt.UserInfo;
import shinhan.EggMoneyna.jwt.UsersInfo;
import shinhan.EggMoneyna.user.follow.entity.Relation;
import shinhan.EggMoneyna.user.follow.service.RelationService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/relations")
public class RelationController {

    private final RelationService relationService;

    // 연관 관계 생성
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "부모가 아이 가족관계증명서를 제출한 경우 연관",
        description = "부모 Authorize, 아이의 pk값",
        tags = { "Relation Controller" })
    @PostMapping("/createRelation/{childId}")
    public ResponseEntity<Relation> createRelation(@UserInfo UsersInfo usersInfo, @PathVariable Long childId) {
        Relation relation = relationService.createRelation(usersInfo.getId(), childId);
        return ResponseEntity.ok(relation);
    }
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "아이 중 한명에게 EggMoney나",
        description = "부모 Authorize, 아이의 pk값",
        tags = { "Relation Controller" })
    @PostMapping("/createEggMoney/{childId}")
    public ResponseEntity<Relation> createEggMoneyRelation(@UserInfo UsersInfo usersInfo, @PathVariable Long childId) {
        Relation relation = relationService.createRelation(usersInfo.getId(), childId);
        return ResponseEntity.ok(relation);
    }

    // 연관 관계 읽기
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "관계되어 있는지 조회",
        description = "Authorize만 되면 정보 조회",
        tags = { "Relation Controller" })
    @GetMapping("/get/{relationId}")
    public ResponseEntity<Relation> getRelation(@UserInfo UsersInfo usersInfo) {
        Relation relation = relationService.getRelation(usersInfo.getId());
        return ResponseEntity.ok(relation);
    }


    // 연관 관계 삭제
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "부모 정보 조회",
        description = "Authorize만 되면 정보 조회",
        tags = { "Parent Controller" })
    @DeleteMapping("/delete/{childId}")
    public void deleteRelation(@UserInfo UsersInfo usersInfo, @PathVariable Long childId) {
        relationService.deleteRelation(usersInfo.getId(), childId);
    }
}

