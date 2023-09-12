package shinhan.EggMoneyna.comment.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shinhan.EggMoneyna.comment.dto.ComplimentResponseDto;
import shinhan.EggMoneyna.comment.service.ComplimentService;
import shinhan.EggMoneyna.jwt.UserInfo;
import shinhan.EggMoneyna.jwt.UsersInfo;

@RestController
@RequestMapping("/v1/compliment")
@RequiredArgsConstructor
public class ComplimentController {
    private final ComplimentService complimentService;

    @Operation(summary = "칭찬 토글 기능", description = "부모일때만 사용가능한 칭찬기능입니다.", tags = { "Compliment Controller" })
    @PostMapping("/{inputOutputId}")
    public ResponseEntity<ComplimentResponseDto> switchCompliment(@UserInfo UsersInfo usersInfo, @PathVariable Long inputOutputId) {
        return ResponseEntity.ok(complimentService.switchCompliment(usersInfo.getId(), inputOutputId));
    }

}
