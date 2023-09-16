package shinhan.EggMoneyna.user.child.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import shinhan.EggMoneyna.account.service.AccountService;
import shinhan.EggMoneyna.global.error.code.ErrorCode;
import shinhan.EggMoneyna.inputoutput.entity.InputOutput;
import shinhan.EggMoneyna.inputoutput.repository.InputOutputRepository;
import shinhan.EggMoneyna.jwt.JwtProvider;
import shinhan.EggMoneyna.monster.entity.MonsterEncyclopedia;
import shinhan.EggMoneyna.monster.repository.MonsterRepository;
import shinhan.EggMoneyna.monster.service.MonsterEncyclopediaService;
import shinhan.EggMoneyna.monster.service.MonsterService;
import shinhan.EggMoneyna.user.child.entity.Child;
import shinhan.EggMoneyna.user.child.repository.ChildRepository;
import shinhan.EggMoneyna.user.child.service.dto.*;
import shinhan.EggMoneyna.wishbox.entity.WishBox;


import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ChildService {

    private final ChildRepository childRepository;
    private final AccountService accountService;
    private final InputOutputRepository inputOutputRepository;


    public ChildSaveResponse save(ChildSaveRequest request){
        Child child = Child.builder()
                .childName(request.getChildId())
                .password("123")
                .birthday(request.getBirthday())
                .gender(request.getIsGirl())
                .build();

        childRepository.saveAndFlush(child);

        log.info(String.valueOf(child.getId()));
        accountService.childCreate(child.getId());
        
        ChildLoginRequest childLoginRequest = ChildLoginRequest.builder()
                .childId(child.getChildName())
                .build();

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


    /**
     *     private String childName;
     *     private String shinhanMongDate;
     *     private int balance;
     *     private int limitMoney;
     *     private int leftMoneyToLimit;
     *     private int attemptDate;
     *     private int compliment;
     *     private Boolean HaveWishbox;
     *     private Long wishboxNumber;
     * @param id
     * @return
     */
    public GetChildHomeResponse getChildHome(Long id, String inputDate){

        Child child = childRepository.findByIdWithAccount(id).orElseThrow();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(inputDate, formatter);

        LocalDateTime startOfDay = localDate.atStartOfDay();
        LocalDateTime endOfDay = localDate.atTime(23, 59, 59, 999999);

        List<InputOutput> inputs = inputOutputRepository.findByAccountAndOutputAndCreateTimeBetween(child.getAccount(), 0, startOfDay, endOfDay);


        List<Boolean> sevendays = child.getSevendays();

        List<Boolean> aMonth = child.getAMonth();
        if (!child.getTodayCheck()) {
            child.setTodayLogin(true);
            if (sevendays.size() < 7) {
                sevendays.add(true);
            } else {
                sevendays.remove(0);
                sevendays.add(true);
            }

            aMonth.add(true);
        }

        int temp = 0;

        for (InputOutput input : inputs) {
            int output = input.getOutput();
            temp += output;
        }

        List<WishBox> wishBoxes = child.getWishBoxes();

        boolean HaveWishbox = false;

        if(!wishBoxes.isEmpty()){
            HaveWishbox = true;
        }

        Long virtualNumber = 0L;

        for (WishBox wishBox : wishBoxes) {
            Long virtualNumber1 = wishBox.getVirtualNumber();
            virtualNumber = virtualNumber1;
        }
        log.info("time ={}",child.getMonster().getCreateTime());
        return GetChildHomeResponse.builder()
                .childName(child.getChildName())
                .shinhanMongDate(child.getMonster().getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .balance(child.getAccount().getBalance())
                .limitMoney(child.getLimitMoney())
                .leftMoneyToLimit(child.getLimitMoney() - temp)
                .attemptDate(aMonth.size() + " / " + (LocalDate.now().getDayOfMonth()))
                .compliment(child.getCompliments().size())
                .HaveWishbox(HaveWishbox)
                .wishboxNumber(virtualNumber)
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
