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
@RequestMapping(("/v1/comment/parent"))
@RequiredArgsConstructor
public class CommentParentController {

    private final CommentParentService commentParentService;

    @Operation(summary = "코멘트 조회(부모)", description = "전체 코멘트 조회입니다.", tags = { "Comment Controller" })
    @GetMapping("/{inputOutputId}")
    public ResponseEntity<CommentResponseDto> getComment(@UserInfo UsersInfo usersInfo, @PathVariable Long inputOutputId) {
        return ResponseEntity.ok(commentService.getComment(inputOutputId));
    }

    @Operation(summary = "코멘트 작성(부모)", description = "단일 코멘트 작성입니다.", tags = { "Comment Controller" })
    @PostMapping("/{inputOutputId}")
    public ResponseEntity<CommentResponseDto> addComment(@UserInfo UsersInfo usersInfo, @PathVariable Long inputOutputId, @RequestBody CommentRequestDto commentRequestDto) {
        return ResponseEntity.ok(commentService.addComment(usersInfo.getId(), inputOutputId, commentRequestDto));
    }

    @Operation(summary = "코멘트 수정(부모)", description = "단일 코멘트 수정입니다.", tags = { "Comment Controller" })
    @PutMapping("/{inputOutputId}/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(@UserInfo UsersInfo usersInfo, @PathVariable Long inputOutputId, @PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto) {
        return ResponseEntity.ok(commentService.updateComment(usersInfo.getId(), inputOutputId, commentId, commentRequestDto));
    }

    @Operation(summary = "코멘트 삭제(부모)", description = "단일 코멘트 삭제입니다. ", tags = { "Comment Controller" })
    @DeleteMapping("/{inputOutputId}/{commentId}")
    public ResponseEntity<CommentResponseDto> deleteComment(@UserInfo UsersInfo usersInfo, @PathVariable Long inputOutputId, @PathVariable Long commentId) {
        return ResponseEntity.ok(commentService.deleteComment(usersInfo.getId(), inputOutputId, commentId));
    }
}
