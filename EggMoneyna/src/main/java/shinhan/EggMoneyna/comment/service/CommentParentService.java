package shinhan.EggMoneyna.comment.service;

import lombok.RequiredArgsConstructor;
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
import shinhan.EggMoneyna.user.parent.entity.Parent;
import shinhan.EggMoneyna.user.parent.repository.ParentRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentParentService {
    private final InputOutputRepository inputOutputRepository;
    private final ChildRepository childRepository;
    private final CommentRepository commentRepository;
    private final ParentRepository parentRepository;

    public CommentResponseDto getComment(Long childId, Long inputOutputId) {
        InputOutput inputOutput = inputOutputRepository.findById(inputOutputId)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_INPUTOUTPUT_ID));
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

    public CommentResponseDto addComment(Long userId, Long childId, Long inputOutputId, CommentRequestDto commentRequestDto) {
        Parent parent = parentRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));
        Child child = childRepository.findById(childId)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));
        InputOutput inputOutput = inputOutputRepository.findById(inputOutputId)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_INPUTOUTPUT_ID));
        Comment comment = commentRepository.findByInputOutput(inputOutput)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_COMMENT_ID));

        comment.addParentComment(commentRequestDto.getComment());

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

    public CommentResponseDto updateComment(Long userId, Long childId, Long inputOutputId, Long commentId, CommentRequestDto commentRequestDto) {
        Parent parent = parentRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));
        Child child = childRepository.findById(childId)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_COMMENT_ID));

        comment.addParentComment(commentRequestDto.getComment());

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

    public CommentResponseDto deleteComment(Long userId, Long childId, Long inputOutputId, Long commentId) {
        Parent parent = parentRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));
        Child child = childRepository.findById(childId)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_COMMENT_ID));

        comment.removeParentComment();

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
