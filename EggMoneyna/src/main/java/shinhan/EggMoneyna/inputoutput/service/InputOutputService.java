package shinhan.EggMoneyna.inputoutput.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shinhan.EggMoneyna.account.entity.Account;
import shinhan.EggMoneyna.account.repository.AccountRepository;
import shinhan.EggMoneyna.comment.entity.Comment;
import shinhan.EggMoneyna.comment.repository.CommentRepository;
import shinhan.EggMoneyna.global.error.code.ErrorCode;
import shinhan.EggMoneyna.global.error.exception.BadRequestException;
import shinhan.EggMoneyna.inputoutput.dto.AddInputOutRequestDto;
import shinhan.EggMoneyna.inputoutput.dto.AddInputOutputResponseDto;
import shinhan.EggMoneyna.inputoutput.dto.InputOutputResponseDto;
import shinhan.EggMoneyna.inputoutput.entity.InputOutput;
import shinhan.EggMoneyna.inputoutput.repository.InputOutputRepository;
import shinhan.EggMoneyna.users.entity.Users;
import shinhan.EggMoneyna.users.repository.UsersRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class InputOutputService {
    private final InputOutputRepository inputOutputRepository;
    private final AccountRepository accountRepository;
    private final UsersRepository usersRepository;
    private final CommentRepository commentRepository;

    public AddInputOutputResponseDto addInput(Long usersId, AddInputOutRequestDto addInputOutRequestDto) {
        Users users = usersRepository.findById(usersId).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));
        Account account = accountRepository.findByUsers(users).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));

        Comment comment = Comment.builder().build();
        commentRepository.save(comment);

        InputOutput inputOutput = addInputOutRequestDto.of(account, comment);
        inputOutputRepository.save(inputOutput);
        account.inBalance(addInputOutRequestDto.getInput());

        return AddInputOutputResponseDto.builder()
                .bigCategory(addInputOutRequestDto.getBigCategory())
                .smallCategory(addInputOutRequestDto.getSmallCategory())
                .input(addInputOutRequestDto.getInput())
                .output(addInputOutRequestDto.getOutput())
                .build();
    }

    public AddInputOutputResponseDto addOutput(Long usersId, AddInputOutRequestDto addInputOutRequestDto) {
        Users users = usersRepository.findById(usersId).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));
        Account account = accountRepository.findByUsers(users).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));

        Comment comment = Comment.builder().build();
        commentRepository.save(comment);

        InputOutput inputOutput = addInputOutRequestDto.of(account, comment);
        inputOutputRepository.save(inputOutput);
        account.outBalance(addInputOutRequestDto.getOutput());

        return AddInputOutputResponseDto.builder()
                .bigCategory(addInputOutRequestDto.getBigCategory())
                .smallCategory(addInputOutRequestDto.getSmallCategory())
                .input(addInputOutRequestDto.getInput())
                .output(addInputOutRequestDto.getOutput())
                .build();
    }

    public InputOutputResponseDto getInput(Long usersId, String inputDate) {
        Users users = usersRepository.findById(usersId).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));
        Account account = accountRepository.findByUsers(users).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(inputDate, formatter);

        LocalDateTime startOfDay = localDate.atStartOfDay();
        LocalDateTime endOfDay = localDate.atTime(23, 59, 59, 999999);

        List<InputOutput> inputs = inputOutputRepository.findByAccountAndOutputAndCreateTimeBetween(account, 0, startOfDay, endOfDay);

        return InputOutputResponseDto.builder()
                .inputOutputs(inputs)
                .build();
    }

    public InputOutputResponseDto getOutput(Long usersId, String outputDate) {
        Users users = usersRepository.findById(usersId).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));
        Account account = accountRepository.findByUsers(users).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(outputDate, formatter);

        LocalDateTime startOfDay = localDate.atStartOfDay();
        LocalDateTime endOfDay = localDate.atTime(23, 59, 59, 999999);

        List<InputOutput> inputs = inputOutputRepository.findByAccountAndInputAndCreateTimeBetween(account, 0, startOfDay, endOfDay);

        return InputOutputResponseDto.builder()
                .inputOutputs(inputs)
                .build();
    }

    public InputOutputResponseDto getInputOutput(Long usersId, String inputOutputDate) {
        Users users = usersRepository.findById(usersId).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));
        Account account = accountRepository.findByUsers(users).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(inputOutputDate, formatter);

        LocalDateTime startOfDay = localDate.atStartOfDay();
        LocalDateTime endOfDay = localDate.atTime(23, 59, 59, 999999);

        List<InputOutput> inputs = inputOutputRepository.findByAccountAndCreateTimeBetween(account, startOfDay, endOfDay);

        return InputOutputResponseDto.builder()
                .inputOutputs(inputs)
                .build();
    }
}
