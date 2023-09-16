package shinhan.EggMoneyna.user.child.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import shinhan.EggMoneyna.account.service.AccountService;
import shinhan.EggMoneyna.global.error.code.ErrorCode;
import shinhan.EggMoneyna.jwt.JwtProvider;
import shinhan.EggMoneyna.monster.entity.MonsterEncyclopedia;
import shinhan.EggMoneyna.monster.service.MonsterEncyclopediaService;
import shinhan.EggMoneyna.user.child.entity.Child;
import shinhan.EggMoneyna.user.child.repository.ChildRepository;
import shinhan.EggMoneyna.user.child.service.dto.*;


import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ChildService {

    private final ChildRepository childRepository;
    private final JwtProvider jwtProvider;
    private final AccountService accountService;
    private final MonsterEncyclopediaService monsterEncyclopediaService;

    public ChildSaveResponse save(ChildSaveRequest request){
        Child child = Child.builder()
                .childName(request.getChildId())
                .password("123")
                .birthday(request.getBirthday())
                .gender(request.getIsGirl())
                .build();

        childRepository.saveAndFlush(child);

        accountService.childCreate(child.getId());

        ChildLoginRequest childLoginRequest = ChildLoginRequest.builder()
                .childId(child.getChildName())
                .build();


        monsterEncyclopediaService.setMonsterEncyclopedia(child.getId());

        return ChildSaveResponse.builder()
                .id(child.getId())
                .childName(request.getChildId())
                .isGirl(request.getIsGirl())
                .birthday(request.getBirthday())
                .build();
    }


//    private returnToken login(ChildLoginRequest request){
//
//        Child child = childRepository.checkChildPw(request.getChildId(), "123").orElseThrow();
//
//        return returnToken.builder()
//                .childToken(jwtProvider.createChildToken(child))
//                .build();
//    }

    public ChildResponse getMyInfo(Long id){

        Child child = childRepository.findByIdWithAccount(id).orElseThrow();

        return ChildResponse.builder()
                .childName(child.getChildName())
                .pocketMoney(child.getPocketMoney())
                .pocketMoneyDate(child.getPocketMoneyDate())
                .account(child.getAccount())
                .cntMonsters(child.getCntMonsters())
                .wishBoxes(child.getWishBoxes())
                .monsterEncyclopedia(child.getMonsterEncyclopedia())
                .limitMoney(child.getLimitMoney())
                .monster(child.getMonster())
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


    public boolean checkEggMoney (Long id){
        Child child = childRepository.findById(id).orElseThrow();
        return child.getEggMoney();
    };
}
