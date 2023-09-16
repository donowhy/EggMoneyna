package shinhan.EggMoneyna.inputoutput.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shinhan.EggMoneyna.account.entity.Account;
import shinhan.EggMoneyna.account.repository.AccountRepository;
import shinhan.EggMoneyna.comment.repository.CommentRepository;
import shinhan.EggMoneyna.global.error.code.ErrorCode;
import shinhan.EggMoneyna.global.error.exception.BadRequestException;
import shinhan.EggMoneyna.inputoutput.dto.InputOutputResponseDto;
import shinhan.EggMoneyna.inputoutput.dto.MonthOutputResponseDto;
import shinhan.EggMoneyna.inputoutput.entity.InputOutput;
import shinhan.EggMoneyna.inputoutput.repository.InputOutputRepository;
import shinhan.EggMoneyna.user.child.entity.Child;
import shinhan.EggMoneyna.user.child.repository.ChildRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class InputOutputParentService {
    private final InputOutputRepository inputOutputRepository;
    private final AccountRepository accountRepository;
    private final ChildRepository childRepository;

    public InputOutputResponseDto getInput(Long usersId, Long childId, String inputDate) {
        Child child = childRepository.findById(childId).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));
        Account account = accountRepository.findByChild(child).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(inputDate, formatter);

        LocalDateTime startOfDay = localDate.atStartOfDay();
        LocalDateTime endOfDay = localDate.atTime(23, 59, 59, 999999);

        List<InputOutput> inputs = inputOutputRepository.findByAccountAndOutputAndCreateTimeBetween(account, 0, startOfDay, endOfDay);

        return InputOutputResponseDto.builder()
                .inputOutputs(inputs)
                .build();
    }

    public InputOutputResponseDto getOutput(Long usersId, Long childId, String outputDate) {
        Child child = childRepository.findById(childId).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));
        Account account = accountRepository.findByChild(child).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(outputDate, formatter);

        LocalDateTime startOfDay = localDate.atStartOfDay();
        LocalDateTime endOfDay = localDate.atTime(23, 59, 59, 999999);

        List<InputOutput> outputs = inputOutputRepository.findByAccountAndInputAndCreateTimeBetween(account, 0, startOfDay, endOfDay);

        return InputOutputResponseDto.builder()
                .inputOutputs(outputs)
                .build();
    }

    public InputOutputResponseDto getInputOutput(Long usersId, Long childId, String inputOutputDate) {
        Child child = childRepository.findById(childId).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));
        Account account = accountRepository.findByChild(child).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(inputOutputDate, formatter);

        LocalDateTime startOfDay = localDate.atStartOfDay();
        LocalDateTime endOfDay = localDate.atTime(23, 59, 59, 999999);

        List<InputOutput> inputOutputs = inputOutputRepository.findByAccountAndCreateTimeBetween(account, startOfDay, endOfDay);

        return InputOutputResponseDto.builder()
                .inputOutputs(inputOutputs)
                .build();
    }

    public MonthOutputResponseDto getTotalMonthOutput(Long usersId, Long childId, String inputOutputDate) {
        Child child = childRepository.findById(childId).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));
        Account account = accountRepository.findByChild(child).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(inputOutputDate + "-01", formatter); // 월의 첫 날로 설정

        LocalDate startOfMonth = localDate.withDayOfMonth(1);
        LocalDate endOfMonth = localDate.withDayOfMonth(localDate.lengthOfMonth());

        List<InputOutput> inputOutputs = inputOutputRepository.findByAccountAndCreateTimeBetween(account, startOfMonth.atStartOfDay(), endOfMonth.atTime(23, 59, 59));

        int totalOutputs = inputOutputs.stream()
                .mapToInt(InputOutput::getOutput)
                .sum();

        return MonthOutputResponseDto.builder()
                .totalMonthOutput(totalOutputs)
                .build();
    }

    public MonthOutputResponseDto getTotalMonthInput(Long usersId, Long childId, String inputOutputDate) {
        Child child = childRepository.findById(childId).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));
        Account account = accountRepository.findByChild(child).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(inputOutputDate + "-01", formatter); // 월의 첫 날로 설정

        LocalDate startOfMonth = localDate.withDayOfMonth(1);
        LocalDate endOfMonth = localDate.withDayOfMonth(localDate.lengthOfMonth());

        List<InputOutput> inputOutputs = inputOutputRepository.findByAccountAndCreateTimeBetween(account, startOfMonth.atStartOfDay(), endOfMonth.atTime(23, 59, 59));

        int totalInputs = inputOutputs.stream()
                .mapToInt(InputOutput::getInput)
                .sum();

        return MonthOutputResponseDto.builder()
                .totalMonthOutput(totalInputs)
                .build();
    }
}
