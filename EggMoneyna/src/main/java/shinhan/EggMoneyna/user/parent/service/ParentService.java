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
                .id(parent.getId())
                .parentId(parent.getParentId())
                .parentToken("Bearer " + login.getParentToken())
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

        List<Child> childrenByParentId = parentRepository.findChildrenByParentId(id);

        List<String> childNames = childrenByParentId.stream()
                .map(Child::getChildId)
                .collect(Collectors.toList());

        List<Child> childrenEggMoneyNa = parentRepository.findChildrenEggMoneyNa(id);

        List<String> eggMoneyNa = childrenEggMoneyNa.stream()
                .map(Child::getChildId)
                .collect(Collectors.toList());


        return ParentResponse.builder()
                .parentId(parent.getParentId())
                .childNicknames(childNames)
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
            log.info("child = {}",child.getChildId());
            MyChildsResponse build = MyChildsResponse.builder()
                    .childId(child.getChildId())
                    .isGirl(child.getGender())
                    .age(age)
                    .build();

            myChildsResponse.add(build);
        }

        return myChildsResponse;
    }

    public MyChildResponse findMyOnechild(Long id){
        Relation relation = relationRepository.findRelationEggMoneyByParentIdOneChild(id).orElseThrow(
                () -> new BadRequestException(ErrorCode.NOT_EXISTS_EGGMONEY_RELATION)
        );

        Child child = relation.getChild();

        LocalDate currentDate = LocalDate.now();

        return MyChildResponse.builder()
                .childId(child.getChildId())
                .birthday(child.getBirthday())
                .age(currentDate.getYear() - child.getBirthday().getYear())
                .inputOutput(child.getAccount().getInputOutputs())
                .pocketMoney(child.getPocketMoney())
                .pocketMoneyDate(child.getPocketMoneyDate())
                .account(child.getAccount())
                .build();
    }


    // 에그머니나 되어있는 조건
    public List<MyChildsResponse> findMyEggMoneyChilds(Long id){

        List<Relation> childList= relationRepository.findRelationEggMoneyAllByParentId(id);

        log.info("childList = {}", childList);
        List<MyChildsResponse> myChildsResponse = new ArrayList<>();

        LocalDate currentDate = LocalDate.now();


        for (Relation relation : childList) {
            Child child = relation.getChild();
            int age = Period.between(child.getBirthday(), currentDate).getYears();
            log.info("age={}", age);
            log.info("child = {}",child.getChildId());
            MyChildsResponse build = MyChildsResponse.builder()
                    .childId(child.getChildId())
                    .isGirl(child.getGender())
                    .age(age)
                    .build();

            myChildsResponse.add(build);
        }

        return myChildsResponse;
    }
}
