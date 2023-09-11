package shinhan.EggMoneyna.wishbox.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import shinhan.EggMoneyna.account.dto.AccountCreateDto;
import shinhan.EggMoneyna.account.repository.AccountRepository;
import shinhan.EggMoneyna.account.service.AccountService;
import shinhan.EggMoneyna.users.entity.Users;
import shinhan.EggMoneyna.users.repository.UsersRepository;
import shinhan.EggMoneyna.wishbox.entity.WishBox;
import shinhan.EggMoneyna.wishbox.repository.WishBoxRepository;
import shinhan.EggMoneyna.wishbox.service.dto.CreateWishItemDto;

import javax.transaction.Transactional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class WishBoxService {

    private final WishBoxRepository wishBoxRepository;
    private final UsersRepository usersRepository;
    private final AccountRepository accountRepository;

    public String create (Long id, CreateWishItemDto createWishItemDto){

        Users users = usersRepository.findById(id).orElseThrow();
        String accountNumber = String.valueOf(users.getAccount().getAccountNumber());
        Long virtualAccountNumber = Long.valueOf(accountNumber + "1");

        WishBox wishBox = WishBox.builder()
                .wishName(createWishItemDto.getItemName())
                .nickName(createWishItemDto.getNickname())
                .price(createWishItemDto.getItemPrice())
                .balance(createWishItemDto.getAccountBalance())
                .virtualAccount(virtualAccountNumber)
                .build();

        wishBoxRepository.save(wishBox);

        return createWishItemDto.getItemName() + " 생성";
    }


}
