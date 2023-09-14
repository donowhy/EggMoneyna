package shinhan.EggMoneyna.user.parent.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import shinhan.EggMoneyna.account.entity.Account;
import shinhan.EggMoneyna.account.service.AccountService;
import shinhan.EggMoneyna.jwt.JwtProvider;
import shinhan.EggMoneyna.user.child.entity.Child;
import shinhan.EggMoneyna.user.child.service.dto.ChildLoginRequest;
import shinhan.EggMoneyna.user.child.service.dto.returnToken;
import shinhan.EggMoneyna.user.parent.entity.Parent;
import shinhan.EggMoneyna.user.parent.repository.ParentRepository;
import shinhan.EggMoneyna.user.parent.service.dto.*;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ParentService {

    private final ParentRepository parentRepository;
    private final JwtProvider jwtProvider;
    private final AccountService accountService;

    public ParentSaveResponse save(ParentSaveRequest request){

        Parent parent = Parent.builder()
                .parentId(request.getParentId())
                .password("123")
                .build();

        parentRepository.saveAndFlush(parent);

        accountService.create(parent.getId());

        ParentLoginRequest parentLoginRequest = ParentLoginRequest.builder()
                .parentId(parent.getParentId())
                .build();

        returnParentToken login = login(parentLoginRequest);

        return ParentSaveResponse.builder()
                .parentId(parent.getParentId())
                .parentToken(login.getParentToken())
                .build();
    }

    private returnParentToken login(ParentLoginRequest request){

        Parent parent = parentRepository.checkParentPw(request.getParentId(), "123").orElseThrow();

        return returnParentToken.builder()
                .parentToken(jwtProvider.createParentToken(parent))
                .build();
    }


    public void delete (Long id){
        Parent parent = parentRepository.findById(id).orElseThrow();
        parentRepository.delete(parent);
    }

    public void updateNickname(Long id, String nickname){
        Parent parent = parentRepository.findById(id).orElseThrow();
        parent.setNickname(nickname);
    }

    public void updatePocketMoneyDate(Long id, int day){
        Parent parent = parentRepository.findById(id).orElseThrow();
        parent.setPocketMoneyDate(day);
    }

    public void updatePocketMoney(Long id, int money){
        Parent parent = parentRepository.findById(id).orElseThrow();
        parent.setPocketMoney(money);
    }

    public ParentResponse getMyInfo(Long id){
        Parent parent = parentRepository.findById(id).orElseThrow();
        return ParentResponse.builder()
                .parentId(parent.getParentId())
                .childNickname(parent.getChildNickname())
                .pocketMoneyDate(parent.getPocketMoneyDate())
                .pocketMoney(parent.getPocketMoney())
                .build();
    }
}
