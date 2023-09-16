package shinhan.EggMoneyna.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shinhan.EggMoneyna.account.entity.Account;
import shinhan.EggMoneyna.account.repository.AccountRepository;
import shinhan.EggMoneyna.comment.dto.ComplimentMonthDto;
import shinhan.EggMoneyna.comment.dto.ComplimentResponseDto;
import shinhan.EggMoneyna.comment.entity.Comment;
import shinhan.EggMoneyna.comment.entity.Compliment;
import shinhan.EggMoneyna.comment.repository.CommentRepository;
import shinhan.EggMoneyna.comment.repository.ComplimentRepository;
import shinhan.EggMoneyna.global.error.code.ErrorCode;
import shinhan.EggMoneyna.global.error.exception.BadRequestException;
import shinhan.EggMoneyna.inputoutput.entity.InputOutput;
import shinhan.EggMoneyna.inputoutput.repository.InputOutputRepository;
import shinhan.EggMoneyna.user.child.entity.Child;
import shinhan.EggMoneyna.user.child.repository.ChildRepository;
import shinhan.EggMoneyna.user.parent.entity.Parent;
import shinhan.EggMoneyna.user.parent.repository.ParentRepository;
import shinhan.EggMoneyna.users.entity.Users;
import shinhan.EggMoneyna.users.repository.UsersRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ComplimentService {
    private final InputOutputRepository inputOutputRepository;
    private final AccountRepository accountRepository;
    private final CommentRepository commentRepository;
    private final ChildRepository childRepository;
    private final ParentRepository parentRepository;
    private final ComplimentRepository complimentRepository;

    public ComplimentResponseDto switchCompliment(Long usersId, Long childId, Long inputOutputId) {
        Parent parent = parentRepository.findById(usersId)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));
        Child child = childRepository.findById(childId)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));
        InputOutput inputOutput = inputOutputRepository.findById(inputOutputId)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_INPUTOUTPUT_ID));
        Comment comment = commentRepository.findByInputOutput(inputOutput)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_COMMENT_ID));

        if (parent.getId() == null) throw new BadRequestException(ErrorCode.INVALID_PARENT);

        comment.switchCompliment(true);

        Optional<Compliment> optionalCompliment = complimentRepository.findByComplimentDate(LocalDate.now());

        if (!optionalCompliment.isPresent()) {
            Compliment compliment = Compliment.builder()
                    .complimentDate(LocalDate.now())
                    .isCompliment(true)
                    .build();

            complimentRepository.save(compliment);
        }

        return ComplimentResponseDto.builder()
                .compliment(comment.getCompliment())
                .build();
    }

//    public List<ComplimentMonthDto> getMonthCompliment(Long userId, String inputOutputDate) {
//        Child child = childRepository.findById(userId)
//                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));
//        Account account = accountRepository.findByChild(child)
//                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate localDate = LocalDate.parse(inputOutputDate + "-01", formatter);
//
//        LocalDate startOfMonth = localDate.withDayOfMonth(1);
//        LocalDate endOfMonth = localDate.withDayOfMonth(localDate.lengthOfMonth());
//
//        List<InputOutput> inputOutputs = inputOutputRepository.findByAccountAndCreateTimeBetween(account, startOfMonth.atStartOfDay(), endOfMonth.atTime(23, 59, 59));
//        List<ComplimentMonthDto> complimentMonthList = new ArrayList<>();
//
//        for (InputOutput inputOutput : inputOutputs) {
//            if (inputOutput.getComment().getCompliment()) {
//            }
//        }
//        ComplimentMonthDto.builder().build();
//    }
}
