package shinhan.EggMoneyna.wishbox.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import shinhan.EggMoneyna.account.entity.Account;
import shinhan.EggMoneyna.account.entity.BankCode;
import shinhan.EggMoneyna.account.repository.AccountRepository;
import shinhan.EggMoneyna.account.service.AccountService;
import shinhan.EggMoneyna.users.repository.UsersRepository;
import shinhan.EggMoneyna.wishbox.entity.Wishbox;
import shinhan.EggMoneyna.wishbox.repository.WishboxRepository;
import shinhan.EggMoneyna.wishbox.service.dto.CreateWishBoxRequestDto;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class WishboxService {

    private final WishboxRepository wishboxRepository;
    private final AccountRepository accountRepository;
    private final AccountService accountService;
    private final UsersRepository usersRepository;

    public String create(Long id, CreateWishBoxRequestDto requestDto){
        log.info("id={}", id);
        Account account = usersRepository.findById(id).orElseThrow().getAccount();
        Long accountNumber = account.getAccountNumber();
        Long accountId = account.getId();
        String temp = String.valueOf(accountNumber);

        Long virtualNum = null;

        for(int i=0; i< 10; i++){
            String s = String.valueOf(i);
            String check = temp + s;
            virtualNum = Long.valueOf(check);

            if(!wishboxRepository.existsByVirtualNumberAndAccountId(virtualNum, accountId)){
                break;
            }
        }

        Wishbox wishbox = Wishbox.builder()
                .nickName(requestDto.getNickName())
                .price(requestDto.getPrice())
                .balance(0)
                .wishName(requestDto.getWishName())
                .account(account)
                .virtualNumber(virtualNum)
                .bankCode(BankCode.Shinhan)
                .build();

        wishboxRepository.save(wishbox);

        return "생성 성공";

    }
}
