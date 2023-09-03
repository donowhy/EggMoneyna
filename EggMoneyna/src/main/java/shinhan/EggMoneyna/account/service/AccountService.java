package shinhan.EggMoneyna.account.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shinhan.EggMoneyna.account.dto.AccountCreateDto;
import shinhan.EggMoneyna.account.entity.Account;
import shinhan.EggMoneyna.account.repository.AccountRepository;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public Long create (AccountCreateDto accountCreateDto){
        Long i = Long.valueOf(UUID.randomUUID().toString());

        Account build = Account.builder()
                .accountNumber(i)
                .balance(0)
                .nickName(accountCreateDto.getNickName())
                .build();

        accountRepository.save(build);

        return build.getId();
    }

    public Account getAccount (Long id){
        Account account = accountRepository.findById(id).orElseThrow(
                RuntimeException::new
        );

        return account;
    }

    public Account updateNickName (Long id, String name){
        Account account = accountRepository.findById(id).orElseThrow(RuntimeException::new);

        account.setNickName(name);

        return account;
    }

    public String delete (Long id){
        accountRepository.deleteById(id);
        return "성공";
    }
}
