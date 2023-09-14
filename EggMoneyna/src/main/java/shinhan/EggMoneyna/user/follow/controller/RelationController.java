package shinhan.EggMoneyna.user.follow.controller;

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
    @PostMapping("/create/{childId}")
    public ResponseEntity<Relation> createRelation(@UserInfo UsersInfo usersInfo, @PathVariable Long childId) {
        log.info("여기까진.");
        Relation relation = relationService.createRelation(usersInfo.getId(), childId);
        return ResponseEntity.ok(relation);
    }

    // 연관 관계 읽기
    @GetMapping("/get/{relationId}")
    public ResponseEntity<Relation> getRelation(@PathVariable Long relationId) {
        try {
            Relation relation = relationService.getRelation(relationId);
            return ResponseEntity.ok(relation);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 연관 관계 업데이트
    @PostMapping("/update/{relationId}")
    public ResponseEntity<Relation> updateRelation(@PathVariable Long relationId, @RequestParam String newChildNickname) {
        try {
            Relation relation = relationService.updateRelation(relationId, newChildNickname);
            return ResponseEntity.ok(relation);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // 연관 관계 삭제
    @DeleteMapping("/delete/{relationId}")
    public ResponseEntity<Void> deleteRelation(@PathVariable Long relationId) {
        try {
            relationService.deleteRelation(relationId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

