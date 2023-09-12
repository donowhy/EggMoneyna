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

    public CommentResponseDto getComment(Long inputOutputId) {
        InputOutput inputOutput = inputOutputRepository.findById(inputOutputId)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_INPUTOUTPUT_ID));
        Comment comment = commentRepository.findByInputOutput(inputOutput)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_COMMENT_ID));

        if (comment.getChildComment() == null && comment.getParentComment() == null) {
            return CommentResponseDto.builder().build();
        } else if (comment.getIsChild() == null) {
            return CommentResponseDto.builder()
                    .parentComment(comment.getParentComment())
                    .build();
        } else if (comment.getIsParent() == null) {
            return CommentResponseDto.builder()
                    .childComment(comment.getChildComment())
                    .build();
        }

        return CommentResponseDto.builder()
                .childComment(comment.getChildComment())
                .parentComment(comment.getParentComment())
                .compliment(comment.getCompliment())
                .build();
    }

    public CommentResponseDto addComment(Long userId, Long inputOutputId, CommentRequestDto commentRequestDto) {
        Users users = usersRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));
        InputOutput inputOutput = inputOutputRepository.findById(inputOutputId)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_INPUTOUTPUT_ID));
        Comment comment = commentRepository.findByInputOutput(inputOutput)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_COMMENT_ID));


        if (users.getIsParents()) {
            comment.addParentComment(commentRequestDto.getComment());
            return CommentResponseDto.builder()
                    .parentComment(commentRequestDto.getComment())
                    .build();
        }
        comment.addChildComment(commentRequestDto.getComment());
        return CommentResponseDto.builder()
                .childComment(comment.getChildComment())
                .build();
    }

    public CommentResponseDto updateComment(Long userId, Long inputOutputId, Long commentId, CommentRequestDto commentRequestDto) {
        Users users = usersRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_COMMENT_ID));

        if (users.getIsParents()) {
            comment.addParentComment(commentRequestDto.getComment());
            return CommentResponseDto.builder()
                    .parentComment(commentRequestDto.getComment())
                    .build();
        }
        comment.addChildComment(commentRequestDto.getComment());
        return CommentResponseDto.builder()
                .childComment(comment.getChildComment())
                .build();
    }

    public CommentResponseDto deleteComment(Long userId, Long inputOutputId, Long commentId) {
        Users users = usersRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_COMMENT_ID));

        if (users.getIsParents()) {
            comment.removeParentComment();
        } else {
            comment.removeChildComment();
        }

        return CommentResponseDto.builder().build();
    }
}
