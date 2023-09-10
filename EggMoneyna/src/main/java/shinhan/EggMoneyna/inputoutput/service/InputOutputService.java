package shinhan.EggMoneyna.inputoutput.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shinhan.EggMoneyna.account.entity.Account;
import shinhan.EggMoneyna.account.repository.AccountRepository;
import shinhan.EggMoneyna.global.error.code.ErrorCode;
import shinhan.EggMoneyna.global.error.exception.BadRequestException;
import shinhan.EggMoneyna.inputoutput.dto.InputOutRequestDto;
import shinhan.EggMoneyna.inputoutput.dto.InputOutputResponseDto;
import shinhan.EggMoneyna.inputoutput.entity.InputOutput;
import shinhan.EggMoneyna.inputoutput.repository.InputOutputRepository;
import shinhan.EggMoneyna.jwt.UsersInfo;
import shinhan.EggMoneyna.users.entity.Users;
import shinhan.EggMoneyna.users.repository.UsersRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class InputOutputService {
    private final InputOutputRepository inputOutputRepository;
    private final AccountRepository accountRepository;
    private final UsersRepository usersRepository;

    public InputOutputResponseDto getInput(Long usersId, InputOutRequestDto inputOutRequestDto) {
        Users users = usersRepository.findById(usersId).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));
        Account account = accountRepository.findByUsers(users).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));

        InputOutput inputOutput = inputOutRequestDto.of(account);
        inputOutputRepository.save(inputOutput);
        account.inBalance(inputOutRequestDto.getInput());

        return InputOutputResponseDto.builder()
                .input(inputOutRequestDto.getInput())
                .output(inputOutRequestDto.getOutput())
                .build();
    }

    public InputOutputResponseDto getOutput(Long usersId, InputOutRequestDto inputOutRequestDto) {
        Users users = usersRepository.findById(usersId).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));
        Account account = accountRepository.findByUsers(users).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));

        InputOutput inputOutput = inputOutRequestDto.of(account);
        inputOutputRepository.save(inputOutput);
        account.outBalance(inputOutRequestDto.getOutput());

        return InputOutputResponseDto.builder()
                .input(inputOutRequestDto.getInput())
                .output(inputOutRequestDto.getOutput())
                .build();
    }
}
