package shinhan.EggMoneyna.account.service;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import shinhan.EggMoneyna.account.dto.AccountCreateDto;
import shinhan.EggMoneyna.account.entity.Account;
import shinhan.EggMoneyna.account.entity.BankCode;

import shinhan.EggMoneyna.account.repository.AccountRepository;
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
				.nickName("에그머니나, " + accountCreateDto.getNickName())
				.bankCode(BankCode.Shinhan)
				.accountNumber(accountNumber)
				.balance(0)
				.nickName(accountCreateDto.getNickName())
				.users(users)
				.build();
		accountRepository.save(build);


		return build.getId();
	}

	// 계좌 조회
	public Account getAccount(Long id) {
		Users users = usersRepository.findById(id).orElseThrow();
		Account account = users.getAccount();
		log.info("account = {}", account);
		return account;

	}



	// 1원 보냈을 때 정확한 계좌인건지 확인 메서드
//	public Boolean checkAccount(Long id, String random) {
//		Users users = usersRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
//		Account account = accountRepository.findByAccountNumber(users.getAccount().getAccountNumber()).orElseThrow(() -> new NoSuchElementException("Account not found"));
//
//		PageRequest pageRequest = PageRequest.of(0, 1); // Get only the first result
//		List<InAccount> inAccounts = inAccountRepository.findLatestByAccountAndSendUser(account, "신한은행", null);
//
//		if(inAccounts.isEmpty()) {
//			throw new NoSuchElementException("InAccount not found with the specified sendUser");
//		}
//
//		InAccount inAccount = inAccounts.get(0);
//
//		return random.equals(inAccount.getMemo());
//	}
//
//
//	public List<DetailAccountResponseDto> getAccountDetail(Long id){
//
//		Users users = usersRepository.findById(id).orElseThrow();
//		Account account = accountRepository.findByAccountNumber(users.getAccount().getAccountNumber()).orElseThrow();
//		List<InAccount> inAccounts = inAccountRepository.findAllByAccount(account);
//
//		List<DetailAccountResponseDto> detailAccountResponseDtos = new ArrayList<>();
//
//		for(InAccount inAccount : inAccounts) {
//			DetailAccountResponseDto detailAccountResponseDto = DetailAccountResponseDto.builder()
//					.memo(inAccount.getMemo())
//					.sendUser(inAccount.getSendUser())
//					.money(inAccount.getMoney())
//					.build();
//
//			detailAccountResponseDtos.add(detailAccountResponseDto);
//		}
//
//		return detailAccountResponseDtos;
//	}

	public Account updateNickName(String name, Long id) {
		Users users = usersRepository.findById(id).orElseThrow();
		Account account = users.getAccount();
		account.setNickName(name);

		return account;
	}

	// 계좌 삭제
	public String delete(Long id) {
		Users users = usersRepository.findById(id).orElseThrow();
		Account account = users.getAccount();
		accountRepository.delete(account);
		return "성공";
	}
}
