package shinhan.EggMoneyna.comment.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shinhan.EggMoneyna.comment.dto.ComplimentMonthDto;
import shinhan.EggMoneyna.comment.dto.ComplimentResponseDto;
import shinhan.EggMoneyna.comment.service.ComplimentService;
import shinhan.EggMoneyna.jwt.UserInfo;
import shinhan.EggMoneyna.jwt.UsersInfo;

import java.util.List;

@RestController
@RequestMapping("/v1/compliment")
@RequiredArgsConstructor
public class ComplimentParentController {
    private final ComplimentService complimentService;

    @Operation(summary = "부모: 칭찬 토글 기능", description = "부모일때만 사용가능한 칭찬기능입니다.", tags = { "Compliment Controller" })
    @PostMapping("/{childId}/{inputOutputId}")
    public ResponseEntity<ComplimentResponseDto> switchCompliment(@UserInfo UsersInfo usersInfo, @PathVariable Long childId, @PathVariable Long inputOutputId) {
        return ResponseEntity.ok(complimentService.switchCompliment(usersInfo.getId(), childId, inputOutputId));
    }

    @Operation(summary = "부모: 칭찬 여부 월 조회", description = "yyyy-MM 형식으로 특정 월에 대한 칭찬 여부를 조회", tags = { "Compliment Controller" })
    @PostMapping("/{childId}/month/{inputOutputDate}")
    public ResponseEntity<List<ComplimentMonthDto>> switchCompliment(@UserInfo UsersInfo usersInfo, @PathVariable Long childId, @PathVariable String inputOutputDate) {
        return ResponseEntity.ok(complimentService.getMonthComplimentParent(usersInfo.getId(), childId, inputOutputDate));
    }
}
