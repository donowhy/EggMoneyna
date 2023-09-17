package shinhan.EggMoneyna.comment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shinhan.EggMoneyna.comment.dto.CommentRequestDto;
import shinhan.EggMoneyna.comment.dto.CommentResponseDto;
import shinhan.EggMoneyna.comment.entity.Comment;
import shinhan.EggMoneyna.comment.repository.CommentRepository;
import shinhan.EggMoneyna.global.error.code.ErrorCode;
import shinhan.EggMoneyna.global.error.exception.BadRequestException;
import shinhan.EggMoneyna.inputoutput.entity.InputOutput;
import shinhan.EggMoneyna.inputoutput.repository.InputOutputRepository;
import shinhan.EggMoneyna.user.child.entity.Child;
import shinhan.EggMoneyna.user.child.repository.ChildRepository;
import shinhan.EggMoneyna.users.entity.Users;
import shinhan.EggMoneyna.users.repository.UsersRepository;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CommentService {
    private final CommentRepository commentRepository;
    private final UsersRepository usersRepository;
    private final InputOutputRepository inputOutputRepository;
    private final ChildRepository childRepository;

    public CommentResponseDto getComment(Long inputOutputId) {
        InputOutput inputOutput = inputOutputRepository.findById(inputOutputId)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_INPUTOUTPUT_ID));
        log.info(String.valueOf(inputOutput.getId()));
        Comment comment = commentRepository.findByInputOutput(inputOutput)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_COMMENT_ID));

        return CommentResponseDto.builder()
                .id(comment.getId())
                .childNickname("자녀")
                .childComment(comment.getChildComment())
                .childCommentCreateTime(comment.getChildCommentCreateTime())
                .parentNickname("엄마")
                .parentComment(comment.getParentComment())
                .parentCommentCreateTime(comment.getParentCommentCreateTime())
                .compliment(comment.getCompliment())
                .build();
    }

    public CommentResponseDto addComment(Long userId, Long inputOutputId, CommentRequestDto commentRequestDto) {
        Child child = childRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));
        InputOutput inputOutput = inputOutputRepository.findById(inputOutputId)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_INPUTOUTPUT_ID));
        Comment comment = commentRepository.findByInputOutput(inputOutput)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_COMMENT_ID));

        comment.addChildComment(commentRequestDto.getComment());

        return CommentResponseDto.builder()
                .id(comment.getId())
                .childNickname("자녀")
                .childComment(comment.getChildComment())
                .childCommentCreateTime(comment.getChildCommentCreateTime())
                .parentNickname("엄마")
                .parentComment(comment.getParentComment())
                .parentCommentCreateTime(comment.getParentCommentCreateTime())
                .compliment(comment.getCompliment())
                .build();
    }

    public CommentResponseDto updateComment(Long userId, Long inputOutputId, Long commentId, CommentRequestDto commentRequestDto) {
        Child child = childRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_COMMENT_ID));

        comment.addChildComment(commentRequestDto.getComment());

        return CommentResponseDto.builder()
                .id(comment.getId())
                .childNickname("자녀")
                .childComment(comment.getChildComment())
                .childCommentCreateTime(comment.getChildCommentCreateTime())
                .parentNickname("엄마")
                .parentComment(comment.getParentComment())
                .parentCommentCreateTime(comment.getParentCommentCreateTime())
                .compliment(comment.getCompliment())
                .build();
    }

    public CommentResponseDto deleteComment(Long userId, Long inputOutputId, Long commentId) {
        Child child = childRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_COMMENT_ID));

        comment.removeChildComment();

        return CommentResponseDto.builder()
                .id(comment.getId())
                .childNickname("자녀")
                .childComment(comment.getChildComment())
                .childCommentCreateTime(comment.getChildCommentCreateTime())
                .parentNickname("엄마")
                .parentComment(comment.getParentComment())
                .parentCommentCreateTime(comment.getParentCommentCreateTime())
                .compliment(comment.getCompliment())
                .build();
    }
}
