package shinhan.EggMoneyna.user.parent.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import shinhan.EggMoneyna.account.entity.Account;
import shinhan.EggMoneyna.account.service.AccountService;
import shinhan.EggMoneyna.global.error.code.ErrorCode;
import shinhan.EggMoneyna.global.error.exception.BadRequestException;
import shinhan.EggMoneyna.inputoutput.entity.InputOutput;
import shinhan.EggMoneyna.jwt.JwtProvider;
import shinhan.EggMoneyna.user.child.entity.Child;
import shinhan.EggMoneyna.user.child.service.dto.ChildLoginRequest;
import shinhan.EggMoneyna.user.child.service.dto.ChildResponse;
import shinhan.EggMoneyna.user.child.service.dto.returnToken;
import shinhan.EggMoneyna.user.follow.entity.Relation;
import shinhan.EggMoneyna.user.follow.repository.RelationRepository;
import shinhan.EggMoneyna.user.parent.entity.Parent;
import shinhan.EggMoneyna.user.parent.repository.ParentRepository;
import shinhan.EggMoneyna.user.parent.service.dto.*;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ParentService {

    private final ParentRepository parentRepository;
    private final JwtProvider jwtProvider;
    private final AccountService accountService;
    private final RelationRepository relationRepository;

    public ParentSaveResponse save(ParentSaveRequest request){


        Parent parent = Parent.builder()
                .parentName(request.getParentId())
                .password("123")
                .gender(request.getRole())
                .build();

        parentRepository.saveAndFlush(parent);

        accountService.parentCreate(parent.getId());

        ParentLoginRequest parentLoginRequest = ParentLoginRequest.builder()
                .parentId(parent.getParentName())
                .build();

        returnParentToken login = login(parentLoginRequest);

        String isMom = "엄마";

        if(request.getRole() == false){
            isMom = "아빠";
        }

        return ParentSaveResponse.builder()
                .id(parent.getId())
                .parentId(parent.getParentName())
                .accountNumber(parent.getAccount().getAccountNumber())
                .Role(isMom)
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

        List<Child> childrenEggMoneyNa = parentRepository.findChildrenEggMoneyNa(id);

        List<String> eggMoneyNa = childrenEggMoneyNa.stream()
                .map(Child::getChildName)
                .collect(Collectors.toList());



        return ParentResponse.builder()
                .parentId(parent.getParentName())
                .eggMoneynaChild(eggMoneyNa)
                .pocketMoneyDate(parent.getPocketMoneyDate())
                .pocketMoney(parent.getPocketMoney())
                .build();
    }

    public List<MyChildsResponse> findMyChilds(Long id){
        List<Relation> childList= relationRepository.findRelationAllByParentId(id);

        log.info("childList = {}", childList);
        List<MyChildsResponse> myChildsResponse = new ArrayList<>();

        LocalDate currentDate = LocalDate.now();


        for (Relation relation : childList) {
            Child child = relation.getChild();
            int age = Period.between(child.getBirthday(), currentDate).getYears();
            log.info("age={}", age);
            log.info("child = {}",child.getChildName());
            MyChildsResponse build = MyChildsResponse.builder()
                    .id(child.getId())
                    .childName(child.getChildName())
                    .age(age)
                    .gender(child.getGender())
                    .build();

            myChildsResponse.add(build);
        }

        return myChildsResponse;
    }

    public ChildResponse findMyOnechild(Long id, Long childId){
        Relation relation = relationRepository.findRelationEggMoneyByParentIdAndChildId(id, childId).orElseThrow(
                () -> new BadRequestException(ErrorCode.NOT_EXISTS_EGGMONEY_RELATION)
        );

        Child child = relation.getChild();

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


    // 에그머니나 되어있는 조건
    public List<MyChildsEggList> findMyEggMoneyChilds(Long id){

        List<Relation> childList= relationRepository.findRelationEggMoneyAllByParentId(id);

        log.info("childList = {}", childList);
        List<MyChildsEggList> myChildsEggList = new ArrayList<>();

        LocalDate currentDate = LocalDate.now();


        for (Relation relation : childList) {
            Child child = relation.getChild();
            int age = Period.between(child.getBirthday(), currentDate).getYears();
            log.info("age={}", age);
            log.info("child = {}",child.getChildName());
            log.info("id = {}", child.getId());
            MyChildsEggList build = MyChildsEggList.builder()
                    .childId(child.getId())
                    .childName(child.getChildName())
                    .balance(child.getAccount().getBalance())
                    .build();

            myChildsEggList.add(build);
        }

        return myChildsEggList;
    }
}
