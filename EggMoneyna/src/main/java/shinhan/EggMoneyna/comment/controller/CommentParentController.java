package shinhan.EggMoneyna.comment.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shinhan.EggMoneyna.comment.dto.CommentRequestDto;
import shinhan.EggMoneyna.comment.dto.CommentResponseDto;
import shinhan.EggMoneyna.comment.service.CommentParentService;
import shinhan.EggMoneyna.jwt.UserInfo;
import shinhan.EggMoneyna.jwt.UsersInfo;

@RestController
@RequestMapping(("/v1/comment"))
@RequiredArgsConstructor
public class CommentParentController {
    private final CommentParentService commentParentService;

    @Operation(summary = "부모: 코멘트 조회", description = "전체 코멘트 조회입니다.", tags = { "Comment Controller" })
    @GetMapping("/{childId}/{inputOutputId}")
    public ResponseEntity<CommentResponseDto> getComment(@UserInfo UsersInfo usersInfo, @PathVariable Long childId, @PathVariable Long inputOutputId) {
        return ResponseEntity.ok(commentParentService.getComment(childId, inputOutputId));
    }

    @Operation(summary = "부모: 코멘트 작성", description = "단일 코멘트 작성입니다.", tags = { "Comment Controller" })
    @PostMapping("/{childId}/{inputOutputId}")
    public ResponseEntity<CommentResponseDto> addComment(@UserInfo UsersInfo usersInfo, @PathVariable Long childId, @PathVariable Long inputOutputId, @RequestBody CommentRequestDto commentRequestDto) {
        return ResponseEntity.ok(commentParentService.addComment(usersInfo.getId(), childId, inputOutputId, commentRequestDto));
    }

    @Operation(summary = "부모: 코멘트 수정", description = "단일 코멘트 수정입니다.", tags = { "Comment Controller" })
    @PutMapping("/{childId}/{inputOutputId}/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(@UserInfo UsersInfo usersInfo, @PathVariable Long childId, @PathVariable Long inputOutputId, @PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto) {
        return ResponseEntity.ok(commentParentService.updateComment(usersInfo.getId(), childId, inputOutputId, commentId, commentRequestDto));
    }

    @Operation(summary = "부모: 코멘트 삭제", description = "단일 코멘트 삭제입니다. ", tags = { "Comment Controller" })
    @DeleteMapping("/{childId}/{inputOutputId}/{commentId}")
    public ResponseEntity<CommentResponseDto> deleteComment(@UserInfo UsersInfo usersInfo, @PathVariable Long childId, @PathVariable Long inputOutputId, @PathVariable Long commentId) {
        return ResponseEntity.ok(commentParentService.deleteComment(usersInfo.getId(), childId, inputOutputId, commentId));
    }
}
