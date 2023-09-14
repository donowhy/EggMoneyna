package shinhan.EggMoneyna.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shinhan.EggMoneyna.comment.dto.ComplimentResponseDto;
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
public class ComplimentService {
    private final UsersRepository usersRepository;
    private final InputOutputRepository inputOutputRepository;
    private final CommentRepository commentRepository;

    public ComplimentResponseDto switchCompliment(Long usersId, Long inputOutputId) {
        Users users = usersRepository.findById(usersId)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));
        InputOutput inputOutput = inputOutputRepository.findById(inputOutputId)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_INPUTOUTPUT_ID));
        Comment comment = commentRepository.findByInputOutput(inputOutput)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_COMMENT_ID));

        if (!users.getIsParents()) throw new BadRequestException(ErrorCode.INVALID_PARENT);

        comment.switchCompliment(!comment.getCompliment());

        return ComplimentResponseDto.builder()
                .compliment(comment.getCompliment())
                .build();
    }
}
