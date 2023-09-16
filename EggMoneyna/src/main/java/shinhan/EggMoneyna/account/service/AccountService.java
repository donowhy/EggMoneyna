package shinhan.EggMoneyna.account.service;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import shinhan.EggMoneyna.account.dto.AccountCreateDto;
import shinhan.EggMoneyna.account.entity.Account;
import shinhan.EggMoneyna.account.entity.BankCode;

import shinhan.EggMoneyna.account.repository.AccountRepository;
import shinhan.EggMoneyna.user.child.entity.Child;
import shinhan.EggMoneyna.user.child.repository.ChildRepository;
import shinhan.EggMoneyna.user.parent.entity.Parent;
import shinhan.EggMoneyna.user.parent.repository.ParentRepository;
import shinhan.EggMoneyna.users.entity.Users;
import shinhan.EggMoneyna.users.repository.UsersRepository;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class AccountService {

	private final AccountRepository accountRepository;
	private final ChildRepository childRepository;
	private final ParentRepository parentRepository;

	// 생성
	public Long create(Long id) {
		log.info("account create");
		Random random = new Random();

		ArrayList<Object> arr = new ArrayList<>();
		for(int i=0; i<12; i++){
			int randomNum = random.nextInt(10);
			arr.add(randomNum);
		}

		String joined = arr.stream().map(String::valueOf).collect(Collectors.joining(""));
		Long accountNumber = Long.parseLong(joined);

		Child child = childRepository.findById(id).orElseThrow();
		Account account = Account.builder()
				.nickName("에그머니나")
				.bankCode(BankCode.SHINHAN)
				.accountNumber(accountNumber)
				.balance(0)
				.child(child)
				.build();
		child.setAccount(account);

		accountRepository.save(account);

		return account.getId();
	}

	public Long childCreate(Long id) {
		log.info("parent Account create");
		Random random = new Random();

		ArrayList<Object> arr = new ArrayList<>();
		for(int i=0; i<12; i++){
			int randomNum = random.nextInt(10);
			arr.add(randomNum);
		}

		String joined = arr.stream().map(String::valueOf).collect(Collectors.joining(""));
		Long accountNumber = Long.parseLong(joined);

		Parent parent = parentRepository.findById(id).orElseThrow();

		Account account = Account.builder()
				.nickName("에그머니나")
				.bankCode(BankCode.SHINHAN)
				.accountNumber(accountNumber)
				.balance(5000000)
				.parent(parent)
				.build();

		parent.setAccount(account);


		accountRepository.save(account);

		return account.getId();
	}
	// 계좌 조회
	public Account getAccount(Long id) {
		Child child = childRepository.findById(id).orElseThrow();
		Account account = child.getAccount();
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

	// 계좌 별명 변경
	public Account updateNickName(String name, Long id) {
		Child child = childRepository.findById(id).orElseThrow();
		Account account = child.getAccount();
		account.setNickName(name);

		return account;
	}

	// 계좌 삭제
	public String delete(Long id) {
		Child child = childRepository.findById(id).orElseThrow();
		Account account = child.getAccount();
		accountRepository.delete(account);
		return "성공";
	}
}
