package shinhan.EggMoneyna.account.service;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import shinhan.EggMoneyna.account.dto.AccountCheckSelfRequestDto;
import shinhan.EggMoneyna.account.dto.AccountCheckSelfResponseDto;
import shinhan.EggMoneyna.account.dto.AccountCreateDto;
import shinhan.EggMoneyna.account.dto.DetailAccountResponseDto;
import shinhan.EggMoneyna.account.entity.Account;
import shinhan.EggMoneyna.account.entity.InAccount;
import shinhan.EggMoneyna.account.repository.AccountRepository;
import shinhan.EggMoneyna.account.repository.InAccountRepository;
import shinhan.EggMoneyna.users.entity.Users;
import shinhan.EggMoneyna.users.repository.UsersRepository;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class AccountService {

	private final AccountRepository accountRepository;
	private final UsersRepository usersRepository;
	private final InAccountRepository inAccountRepository;

	// 생성
	public Long create(AccountCreateDto accountCreateDto, Long id) {
		log.info("account create");
		Random random = new Random();

		ArrayList<Object> arr = new ArrayList<>();
		for(int i=0; i<12; i++){
			int randomNum = random.nextInt(10);
			arr.add(randomNum);
		}

		String joined = arr.stream().map(String::valueOf).collect(Collectors.joining(""));
		Long accountNumber = Long.parseLong(joined);


		Users users = usersRepository.findById(id).orElseThrow();

		Account build = Account.builder()
				.accountNumber(accountNumber)
				.balance(0)
				.nickName(accountCreateDto.getNickName())
				.users(users)
				.build();
		users.setAccount(build);
		accountRepository.save(build);
		usersRepository.save(users);
		return build.getId();
	}

	// 계좌 조회
	public Account getAccount(Long id) {
		Users users = usersRepository.findById(id).orElseThrow();
		Account account = users.getAccount();
		log.info("account = {}", account);
		return account;

	}

	// 1원 체크를 위한 send 내역
	public DetailAccountResponseDto sendCheck1 (Long id){
		Users users = usersRepository.findById(id).orElseThrow();
		Account account = users.getAccount();
		account.setBalance(account.getBalance()  + 1);
		String s = generateRandomString(4);

		InAccount inAccount = InAccount.builder()
				.money(account.getBalance())
				.memo(s)
				.sendUser("신한은행")
				.account(account)
				.build();

		inAccountRepository.save(inAccount);

		return DetailAccountResponseDto.builder()
				.money(account.getBalance())
				.memo(s)
				.sendUser("신한은행")
				.build();

	}

	// 랜덤 4글자를 위한 메서드
	private String generateRandomString(int length) {
		Random random = new Random();
		StringBuilder stringBuilder = new StringBuilder(length);

		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

		for (int i = 0; i < length; i++) {
			int index = random.nextInt(characters.length());
			stringBuilder.append(characters.charAt(index));
		}
		return stringBuilder.toString();
	}

	// 1원 보냈을 때 정확한 계좌인건지 확인 메서드
	public Boolean checkAccount(Long id, String random) {
		Users users = usersRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
		Account account = accountRepository.findByAccountNumber(users.getAccount().getAccountNumber()).orElseThrow(() -> new NoSuchElementException("Account not found"));

		PageRequest pageRequest = PageRequest.of(0, 1); // Get only the first result
		List<InAccount> inAccounts = inAccountRepository.findLatestByAccountAndSendUser(account, "신한은행", pageRequest);

		if(inAccounts.isEmpty()) {
			throw new NoSuchElementException("InAccount not found with the specified sendUser");
		}

		InAccount inAccount = inAccounts.get(0);

		return random.equals(inAccount.getMemo());
	}


	public List<DetailAccountResponseDto> getAccountDetail(Long id){

		Users users = usersRepository.findById(id).orElseThrow();
		Account account = accountRepository.findByAccountNumber(users.getAccount().getAccountNumber()).orElseThrow();
		List<InAccount> inAccounts = inAccountRepository.findAllByAccount(account);

		List<DetailAccountResponseDto> detailAccountResponseDtos = new ArrayList<>();

		for(InAccount inAccount : inAccounts) {
			DetailAccountResponseDto detailAccountResponseDto = DetailAccountResponseDto.builder()
					.memo(inAccount.getMemo())
					.sendUser(inAccount.getSendUser())
					.money(inAccount.getMoney())
					.build();

			detailAccountResponseDtos.add(detailAccountResponseDto);
		}

		return detailAccountResponseDtos;
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

	public InAccount payment(Long id, InAccount inAccount){

		Users users = usersRepository.findById(id).orElseThrow();
		Account account = accountRepository.findByAccountNumber(users.getAccount().getAccountNumber()).orElseThrow();

		InAccount inAccount1 = InAccount.builder()
				.account(account)
				.useWhere(inAccount.getUseWhere())
				.sendUser(inAccount.getSendUser())
				.money(inAccount.getMoney())
				.build();
		inAccountRepository.save(inAccount1);

		return inAccount1;
	}
}
