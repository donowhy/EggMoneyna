package shinhan.EggMoneyna.wishbox.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import shinhan.EggMoneyna.account.entity.Account;
import shinhan.EggMoneyna.account.entity.BankCode;
import shinhan.EggMoneyna.account.repository.AccountRepository;
import shinhan.EggMoneyna.account.service.AccountService;
import shinhan.EggMoneyna.user.child.entity.Child;
import shinhan.EggMoneyna.user.child.repository.ChildRepository;
import shinhan.EggMoneyna.users.repository.UsersRepository;
import shinhan.EggMoneyna.wishbox.entity.WishBox;
import shinhan.EggMoneyna.wishbox.repository.WishboxRepository;
import shinhan.EggMoneyna.wishbox.service.dto.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class WishboxService {

    private final WishboxRepository wishboxRepository;
    private final ChildRepository childRepository;

    public Long create(Long id, CreateWishBoxRequestDto requestDto){
        log.info("id={}", id);
        Account account = childRepository.findById(id).orElseThrow().getAccount();
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

        WishBox wishbox = WishBox.builder()
                .price(requestDto.getPrice())
                .balance(0)
                .wishName(requestDto.getWishName())
                .account(account)
                .virtualNumber(virtualNum)
                .bankCode(BankCode.SHINHAN)
                .build();

        wishboxRepository.save(wishbox);

        return virtualNum;
    }

//    public void updateNickname(Long id, UpdateNicknameRequest request){
//        childRepository.findById(id).orElseThrow();
//
//        WishBox wishBox = wishboxRepository.findWishBoxByVirtualNumber(request.getVirtualNumber()).orElseThrow();
//        wishBox.setNickname(request.getNickname());
//    }

    public GetWishboxResponse getOneWishbox(Long id, GetWishboxRequest request){
        childRepository.findById(id).orElseThrow();
        WishBox wishBox = wishboxRepository.findWishBoxByVirtualNumber(request.getVirtualNumber()).orElseThrow();
        return GetWishboxResponse.builder()
                .wishName(wishBox.getWishName())
                .price(wishBox.getPrice())
                .balance(wishBox.getBalance())
                .virtualNumber(wishBox.getVirtualNumber())
                .build();
    }

    public List<GetWishboxResponse> getWishbox(Long id){
        Account account = childRepository.findById(id).orElseThrow().getAccount();
        List<WishBox> wishboxByAccount = wishboxRepository.findWishBoxByAccount(account);

        List<GetWishboxResponse> responses = new ArrayList<>();

        for (WishBox wishbox : wishboxByAccount) {
            responses.add(GetWishboxResponse.builder()
                    .wishName(wishbox.getWishName())
                    .price(wishbox.getPrice())
                    .balance(wishbox.getBalance())
                    .virtualNumber(wishbox.getVirtualNumber())
                    .createTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                    .build()
            );
        }
        return responses;
    }

    public void deleteWishbox(Long id, GetWishboxRequest request){
        childRepository.findById(id).orElseThrow();
        WishBox wishBox = wishboxRepository.findWishBoxByVirtualNumber(request.getVirtualNumber()).orElseThrow();
        wishboxRepository.delete(wishBox);
    }

    public SendMoneyMyAccountResponse sendMoneyMyAccount(Long id, SendMoneyMyAccountRequest request){
        Child child = childRepository.findById(id).orElseThrow();
        WishBox wishBox = wishboxRepository.findWishBoxByVirtualNumber(request.getVirtualAccountNumber()).orElseThrow();
        Account account = child.getAccount();

        if(wishBox.getBalance() < request.getMoney()){
            throw new RuntimeException();
        }

        wishBox.setBalance(wishBox.getBalance() - request.getMoney());
        account.setBalance(account.getBalance() + request.getMoney());

        return SendMoneyMyAccountResponse.builder()
                .realAccountNumber(account.getAccountNumber())
                .realAccountBalance(account.getBalance())
                .virtualAccountNumber(request.getVirtualAccountNumber())
                .virtualBalance(wishBox.getBalance())
                .build();
    }

    public SendMoneyMyAccountResponse sendMoneyMyVirtualAccount (Long id, SendMoneyMyAccountRequest request){
        Child child = childRepository.findById(id).orElseThrow();
        WishBox wishBox = wishboxRepository.findWishBoxByVirtualNumber(request.getVirtualAccountNumber()).orElseThrow();
        Account account = child.getAccount();

        if(account.getBalance() < request.getMoney()){
            throw new RuntimeException();
        }

        wishBox.setBalance(wishBox.getBalance() + request.getMoney());
        account.setBalance(account.getBalance() - request.getMoney());

        return SendMoneyMyAccountResponse.builder()
                .realAccountNumber(account.getAccountNumber())
                .realAccountBalance(account.getBalance())
                .virtualAccountNumber(request.getVirtualAccountNumber())
                .virtualBalance(wishBox.getBalance())
                .build();
    }


}
