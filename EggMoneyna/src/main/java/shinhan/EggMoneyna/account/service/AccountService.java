package shinhan.EggMoneyna.account.service;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import shinhan.EggMoneyna.account.dto.*;
import shinhan.EggMoneyna.account.entity.Account;
import shinhan.EggMoneyna.account.entity.BankCode;
import shinhan.EggMoneyna.account.repository.AccountRepository;
import shinhan.EggMoneyna.users.entity.Users;
import shinhan.EggMoneyna.users.repository.UsersRepository;

import java.time.LocalDateTime;
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

//	// 1원 체크를 위한 send 내역
//	public DetailAccountResponseDto sendCheck1 (Long id){
//		Users users = usersRepository.findById(id).orElseThrow();
//		Account account = users.getAccount();
//		account.setBalance(account.getBalance()  + 1);
//		String s = generateRandomString(4);
//
//		InAccount inAccount = InAccount.builder()
//				.money(account.getBalance())
//				.memo(s)
//				.sendUser("신한은행")
//				.account(account)
//				.build();
//
//		inAccountRepository.save(inAccount);
//
//		return DetailAccountResponseDto.builder()
//				.money(account.getBalance())
//				.memo(s)
//				.sendUser("신한은행")
//				.build();
//
//	}

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

//	// 1원 보냈을 때 정확한 계좌인건지 확인 메서드
//	public Boolean checkAccount(Long id, String random) {
//		Users users = usersRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
//		Account account = accountRepository.findByAccountNumber(users.getAccount().getAccountNumber()).orElseThrow(() -> new NoSuchElementException("Account not found"));
//
//		PageRequest pageRequest = PageRequest.of(0, 1);
//		List<InAccount> inAccounts = inAccountRepository.findLatestByAccountAndSendUser(account, "신한은행");
//
//		if(inAccounts.isEmpty()) {
//			throw new NoSuchElementException();
//		}
//
//		InAccount inAccount = inAccounts.get(0);
//
//		return random.equals(inAccount.getMemo());
//	}


//	// 거래내역 상세 조회
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

	// 계좌 닉네임 변경
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

//	// 결제
//	public PaymentResponseDto payment(Long id, PaymentRequestDto requestDto){
//
//		Users users = usersRepository.findById(id).orElseThrow();
//		Account account = accountRepository.findByAccountNumber(users.getAccount().getAccountNumber()).orElseThrow();
//
//		// 결제 ( 브랜드 위치명, 회사명, 쓴 돈, 메모)
//		InAccount inAccount = InAccount.builder()
//				.account(account)
//				.useWhere(requestDto.getUseWhere())
//				.sendUser(requestDto.getSendUser())
//				.money(requestDto.getSpentMoney())
//				.memo(requestDto.getMemo())
//				.build();
//
//		// 체크카드 형식이기 때문, 카드에 돈이 없으면 결제 못함.
//		if(account.getBalance() - requestDto.getSpentMoney() >= 0) {
//			account.setBalance(account.getBalance() - requestDto.getSpentMoney());
//		}else {
//			throw new RuntimeException();
//		}
//
//		inAccountRepository.save(inAccount);
//
//		return PaymentResponseDto.builder()
//				.spentTime(inAccount.getCreateTime())
//				.modifyTime(inAccount.getUpdateTime())
//				.sendUser(requestDto.getSendUser())
//				.useWhere(requestDto.getUseWhere())
//				.spentMoney(requestDto.getSpentMoney())
//				.memo(requestDto.getMemo())
//				.build();
//	}
//
//	// 결제 취소
//	public String cancel(Long id, CancelRequestDto requestDto) {
//		Users users = usersRepository.findById(id).orElseThrow();
//		List<InAccount> inAccountList = users.getAccount().getInAccountList();
//
//		// inAccount 리스트 중 정확한 것만 찾아서 삭제한다. 추후 쿼리문으로 최적화 얘정.
//		// 총 4개의 확인 절차. 브랜드, 회사명, 취소하려는 돈, 30일이 지나지 않은 시간
//		for (InAccount inAccount : inAccountList) {
//			if (inAccount.getUseWhere().equals(requestDto.getUseWhere())
//					&& inAccount.getSendUser().equals(requestDto.getSendUser())
//					&& inAccount.getMoney() == requestDto.getMoney()) {
//
//				if (inAccount.getCreateTime().isAfter(LocalDateTime.now().minusDays(30))) {
//					int money = inAccount.getMoney();
//					inAccount.getAccount().setBalance(money + inAccount.getAccount().getBalance());
//					inAccountRepository.delete(inAccount);
//					return "성공";
//				} else {
//					return "실패, 취소 할 수 있는 기간이 지났습니다.";
//				}
//			}
//		}
//		return "실패, 해당 거래 내역을 찾을 수 없습니다.";
//	}

	public GetAccountBalanceDto getBalance (Long id) {
		Users users = usersRepository.findById(id).orElseThrow();
		Account account = accountRepository.findByAccountNumber(users.getAccount().getAccountNumber()).orElseThrow();

		return GetAccountBalanceDto.builder()
				.accountNumber(account.getAccountNumber())
				.balance(account.getBalance())
				.build();
	}

}
