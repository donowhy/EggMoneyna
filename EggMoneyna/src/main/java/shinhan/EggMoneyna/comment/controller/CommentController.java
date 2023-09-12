package shinhan.EggMoneyna.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shinhan.EggMoneyna.comment.dto.CommentRequestDto;
import shinhan.EggMoneyna.comment.dto.CommentResponseDto;
import shinhan.EggMoneyna.comment.service.CommentService;
import shinhan.EggMoneyna.jwt.UserInfo;
import shinhan.EggMoneyna.jwt.UsersInfo;

@RestController
@RequestMapping("/v1/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{inputOutputId}")
    public ResponseEntity<CommentResponseDto> getComment(@UserInfo UsersInfo usersInfo, @PathVariable Long inputOutputId) {
        return ResponseEntity.ok(commentService.getComment(inputOutputId));
    }

    @PostMapping("/{inputOutputId}")
    public ResponseEntity<CommentResponseDto> addComment(@UserInfo UsersInfo usersInfo, @PathVariable Long inputOutputId, @RequestBody CommentRequestDto commentRequestDto) {
        return ResponseEntity.ok(commentService.addComment(usersInfo.getId(), inputOutputId, commentRequestDto));
    }
}
