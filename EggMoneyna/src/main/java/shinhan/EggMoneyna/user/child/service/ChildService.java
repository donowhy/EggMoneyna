package shinhan.EggMoneyna.user.child.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import shinhan.EggMoneyna.account.service.AccountService;
import shinhan.EggMoneyna.jwt.JwtProvider;
import shinhan.EggMoneyna.user.child.entity.Child;
import shinhan.EggMoneyna.user.child.repository.ChildRepository;
import shinhan.EggMoneyna.user.child.service.dto.*;


import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ChildService {

    private final ChildRepository childRepository;
    private final JwtProvider jwtProvider;
    private final AccountService accountService;

    public ChildSaveResponse save(ChildSaveRequest request){
        Child child = Child.builder()
                .childId(request.getChildId())
                .password("123")
                .build();
        childRepository.save(child);

        return ChildSaveResponse.builder()
                .childId(request.getChildId())
                .build();
    }


    public returnToken login(ChildLoginRequest request){

        Child child = childRepository.checkChildPw(request.getChildId(), "123").orElseThrow();

        return returnToken.builder()
                .childToken(jwtProvider.createChildToken(child))
                .build();
    }

    public ChildResponse getMyInfo(Long id){

        Child child = childRepository.findById(id).orElseThrow();

        return ChildResponse.builder()
                .childId(child.getChildId())
                .pocketMoney(child.getPocketMoney())
                .pocketMoneyDate(child.getPocketMoneyDate())
                .account(child.getAccount())
                .cntMonsters(child.getCntMonsters())
                .wishBoxes(child.getWishBoxes())
                .monsterEncyclopedia(child.getMonsterEncyclopedia())
                .limitMoney(child.getLimitMoney())
                .firebaseToken(child.getFirebaseToken())
                .monsters(child.getMonsters())
                .build();
    }

    public void updateLimitMoney(Long id, UpdateLimitMoneyRequest request){
        Child child = childRepository.findById(id).orElseThrow();
        child.setLimitMoney(request.getLimitMoney());
    }

    public void deleteChild (Long id){
        Child child = childRepository.findById(id).orElseThrow();
        childRepository.delete(child);
    }
}
