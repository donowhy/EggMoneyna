package shinhan.EggMoneyna.account.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import shinhan.EggMoneyna.account.dto.AccountCheckSelfRequestDto;
import shinhan.EggMoneyna.account.dto.AccountCheckSelfResponseDto;
import shinhan.EggMoneyna.account.dto.AccountCreateDto;
import shinhan.EggMoneyna.account.entity.Account;
import shinhan.EggMoneyna.account.repository.AccountRepository;
import shinhan.EggMoneyna.users.entity.Users;
import shinhan.EggMoneyna.users.repository.UsersRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class AccountService {

	private final AccountRepository accountRepository;
	private final UsersRepository usersRepository;

	public Long create(AccountCreateDto accountCreateDto, String id) {
		log.info("account create");
		Random random = new Random();

		ArrayList<Object> arr = new ArrayList<>();
		for(int i=0; i<12; i++){
			int randomNum = random.nextInt(10);
			arr.add(randomNum);
		}

		String joined = arr.stream().map(String::valueOf).collect(Collectors.joining(""));
		Long accountNumberr = Long.parseLong(joined);


		Users users = usersRepository.findByUserId(id).orElseThrow();

		Account build = Account.builder()
				.accountNumber(accountNumberr)
				.balance(0)
				.nickName(accountCreateDto.getNickName())
				.user(users)
				.build();

		accountRepository.save(build);

		return build.getId();
	}

	public Account getAccount(Long id) {
		Users users = usersRepository.findById(id).orElseThrow();
        return users.getAccount();
	}

	public Account updateNickName(String name, Long id) {
		Users users = usersRepository.findById(id).orElseThrow();
		Account account = users.getAccount();
		account.setNickName(name);

		return account;
	}

	public String delete(Long id) {
		Users users = usersRepository.findById(id).orElseThrow();
		Account account = users.getAccount();
		accountRepository.delete(account);
		return "성공";
	}

	// 1원 이체
	//    public AccountCheckSelfResponseDto checkSelf (AccountCheckSelfRequestDto checkSelfRequest){
	//        return AccountCheckSelfResponseDto;
	//    }
}
