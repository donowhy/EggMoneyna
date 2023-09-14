package shinhan.EggMoneyna.wishbox.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import shinhan.EggMoneyna.account.entity.Account;
import shinhan.EggMoneyna.account.entity.BankCode;
import shinhan.EggMoneyna.account.repository.AccountRepository;
import shinhan.EggMoneyna.account.service.AccountService;
import shinhan.EggMoneyna.user.child.repository.ChildRepository;
import shinhan.EggMoneyna.users.repository.UsersRepository;
import shinhan.EggMoneyna.wishbox.entity.WishBox;
import shinhan.EggMoneyna.wishbox.repository.WishboxRepository;
import shinhan.EggMoneyna.wishbox.service.dto.CreateWishBoxRequestDto;
import shinhan.EggMoneyna.wishbox.service.dto.GetWishboxRequest;
import shinhan.EggMoneyna.wishbox.service.dto.GetWishboxResponse;
import shinhan.EggMoneyna.wishbox.service.dto.UpdateNicknameRequest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class WishboxService {

    private final WishboxRepository wishboxRepository;
    private final ChildRepository childRepository;

    public String create(Long id, CreateWishBoxRequestDto requestDto){
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

    public void updateNickname(Long id, UpdateNicknameRequest request){
        childRepository.findById(id).orElseThrow();

        WishBox wishBox = wishboxRepository.findWishBoxByVirtualNumber(request.getVirtualNumber()).orElseThrow();
        wishBox.setNickname(request.getNickname());
    }

    public GetWishboxResponse getOneWishbox(Long id, GetWishboxRequest request){
        childRepository.findById(id).orElseThrow();
        WishBox wishBox = wishboxRepository.findWishBoxByVirtualNumber(request.getVirtualNumber()).orElseThrow();
        return GetWishboxResponse.builder()
                .nickName(wishBox.getNickName())
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
                    .nickName(wishbox.getNickName())
                    .wishName(wishbox.getWishName())
                    .price(wishbox.getPrice())
                    .balance(wishbox.getBalance())
                    .virtualNumber(wishbox.getVirtualNumber())
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
}
