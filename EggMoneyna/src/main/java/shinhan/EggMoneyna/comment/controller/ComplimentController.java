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
public class ComplimentController {
    private final ComplimentService complimentService;

    @Operation(summary = "칭찬 여부 월 조회", description = "yyyy-MM 형식으로 특정 월에 대한 칭찬 여부를 조회", tags = { "Compliment Controller" })
    @PostMapping("month/{inputOutputDate}")
    public ResponseEntity<List<ComplimentMonthDto>> switchCompliment(@UserInfo UsersInfo usersInfo, @PathVariable String inputOutputDate) {
        return ResponseEntity.ok(complimentService.getMonthCompliment(usersInfo.getId(), inputOutputDate));
    }
}
