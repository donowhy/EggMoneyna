package shinhan.EggMoneyna.account.service;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import shinhan.EggMoneyna.account.dto.Check1CertChildResponse;
import shinhan.EggMoneyna.account.dto.Check1CertParentResponse;
import shinhan.EggMoneyna.account.dto.Send1CertResponse;
import shinhan.EggMoneyna.account.entity.Account;
import shinhan.EggMoneyna.account.entity.BankCode;

import shinhan.EggMoneyna.account.repository.AccountRepository;
import shinhan.EggMoneyna.account.service.dto.Send1CertRequest;
import shinhan.EggMoneyna.global.error.code.ErrorCode;
import shinhan.EggMoneyna.global.error.exception.BadRequestException;
import shinhan.EggMoneyna.inputoutput.entity.InputOutput;
import shinhan.EggMoneyna.inputoutput.repository.InputOutputRepository;
import shinhan.EggMoneyna.jwt.JwtProvider;
import shinhan.EggMoneyna.user.child.entity.Child;
import shinhan.EggMoneyna.user.child.repository.ChildRepository;
import shinhan.EggMoneyna.user.parent.entity.Parent;
import shinhan.EggMoneyna.user.parent.repository.ParentRepository;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class AccountService {

	private final AccountRepository accountRepository;
	private final ChildRepository childRepository;
	private final ParentRepository parentRepository;
	private final InputOutputRepository inputOutputRepository;
	private final JwtProvider jwtProvider;

	// 생성
	public Long childCreate(Long id) {
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

	public Long parentCreate(Long id) {
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

	public Send1CertResponse send1Cert (Send1CertRequest request){

		Random rand = new Random();
		String randomNum = String.valueOf(rand.nextInt((999 - 101) + 1) + 101);

		Account account = accountRepository.findByAccountNumber(request.getAccountNumber()).orElseThrow();

		InputOutput inputOutput = InputOutput.builder()
				.brandName("신한은행")
				.brandImg("신한은행")
				.bigCategory("1원이체")
				.smallCategory("신한" + randomNum)
				.output(0)
				.input(1)
				.account(account)
				.build();

		inputOutputRepository.save(inputOutput);

		return Send1CertResponse.builder()
				.certNumber(Long.valueOf(randomNum))
				.build();
	}



	// 1원 보냈을 때 정확한 계좌인건지 확인 메서드
	public Check1CertParentResponse checkParentAccount(Long number, String random) {
		Account account = accountRepository.findByAccountNumber(number).orElseThrow();
		Parent parent = account.getParent();

		PageRequest pageRequest = PageRequest.of(0, 1); // Get only the first result

		List<InputOutput> shinhans = inputOutputRepository.findLatestByAccountAndSendUser(account, "신한은행");

		log.info("shinhans={}",shinhans.size() );

		if(shinhans.isEmpty()) {
			throw new NoSuchElementException("InAccount not found with the specified sendUser");
		}

		log.info("random={}", random);
		log.info("shinhans={}",shinhans.get(0).getSmallCategory());

		InputOutput inputOutput = shinhans.get(0);
		String smallCategory = inputOutput.getSmallCategory();
		String checkNumber = smallCategory.substring(smallCategory.length() - 3);
		boolean equals = random.equals(checkNumber);

		String parentToken = jwtProvider.createParentToken(parent);

		return Check1CertParentResponse.builder()
				.isRight(equals)
				.parentToken(parentToken)
				.build();

	}

	// 1원 보냈을 때 정확한 계좌인건지 확인 메서드
	public Check1CertChildResponse checkChildAccount(Long number, String random) throws RuntimeException {
		Account account = accountRepository.findByAccountNumber(number).orElseThrow();
		Child child = account.getChild();
		PageRequest pageRequest = PageRequest.of(0, 1); // Get only the first result

		List<InputOutput> shinhans = inputOutputRepository.findLatestByAccountAndSendUser(account, "신한은행");

		if(shinhans.isEmpty()) {
			throw new NoSuchElementException("InAccount not found with the specified sendUser");
		}

		InputOutput inputOutput = shinhans.get(0);

		String smallCategory = inputOutput.getSmallCategory();
		String checkNumber = smallCategory.substring(smallCategory.length() - 3);
		boolean equals = random.equals(checkNumber);


		if(!equals){
			throw new BadRequestException(ErrorCode.INVALID_CERT_CODE);
        }

		String childToken = jwtProvider.createChildToken(child);

		if(!child.getEggMoney()) {
			return Check1CertChildResponse.builder()
					.isRight(equals)
					.isAccountActivate(child.getEggMoney())
					.build();
		}else {
			return Check1CertChildResponse.builder()
					.isRight(equals)
					.childToken(childToken)
					.isAccountActivate(child.getEggMoney())
					.build();
		}


	}


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
