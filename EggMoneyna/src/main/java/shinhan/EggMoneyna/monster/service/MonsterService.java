package shinhan.EggMoneyna.monster.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import shinhan.EggMoneyna.monster.dto.*;
import shinhan.EggMoneyna.monster.entity.EncyclopediaDetail;
import shinhan.EggMoneyna.monster.entity.History;
import shinhan.EggMoneyna.monster.entity.Monster;
import shinhan.EggMoneyna.monster.entity.MonsterEncyclopedia;
import shinhan.EggMoneyna.monster.entity.enumType.Feel;
import shinhan.EggMoneyna.monster.entity.enumType.MonsterStatus;
import shinhan.EggMoneyna.monster.repository.EncyclopediaDetailRepository;
import shinhan.EggMoneyna.monster.repository.MonsterEncyclopediaRepository;
import shinhan.EggMoneyna.monster.repository.MonsterRepository;
import shinhan.EggMoneyna.user.child.entity.Child;
import shinhan.EggMoneyna.user.child.repository.ChildRepository;


import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class MonsterService {

    private final ChildRepository childRepository;
    private final MonsterRepository monsterRepository;
    private final MonsterEncyclopediaRepository monsterEncyclopediaRepository;
    private final HistoryService historyService;
    private final MonsterEncyclopediaService monsterEncyclopediaService;
    private final EncyclopediaDetailRepository encyclopediaDetailRepository;

    // CREATE
    public MonsterSaveResponseDto save(MonsterSaveRequestDto monsterSaveRequestDto, Long id) {

        Child child = childRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));


        monsterEncyclopediaService.save(child.getId());

        log.info("name={}", Monster.getRandomMong());

        if (child.getCntMonsters() != 0) {
            throw new RuntimeException();
        }
        Monster monster = Monster.builder()
                .name(Monster.getRandomMong())
                .status(MonsterStatus.Egg)
                .exp(0)
                .benefit(monsterSaveRequestDto.getBenefit())
                .child(child)
                .feel(Feel.NOMAL)
                .build();

        Monster savedMonster = monsterRepository.saveAndFlush(monster);

        child.setMonster(monster);
        child.setCntMonsters(child.getCntMonsters() + 1);

        return MonsterSaveResponseDto.of(savedMonster);
    }

    // READ
    public MonsterResponseDto findById(Long id) {
        Child child = childRepository.findById(id).orElseThrow();
        MonsterEncyclopedia monsterEncyclopedia = child.getMonsterEncyclopedia();
        Monster monster = child.getMonster();

        List<Boolean> sevendays = child.getSevendays();

        boolean checkOne = child.getTodayCheck();

        List<Boolean> aMonth = child.getAMonth();
        if (!child.getTodayCheck()) {
            child.setTodayLogin(true);
            child.setConsecutiveceAttemptAndTodayCheck(child.getConsecutiveAttempt() + 1);
            HistoryRequest request = HistoryRequest.builder()
                    .number(0)
                    .build();

            HistoryResponse save = historyService.save(id, request);

            monster.setExp(monster.getExp() + save.getExp());

            if (sevendays.size() < 7) {
                sevendays.add(true);
            } else {
                sevendays.remove(0);
                sevendays.add(true);
            }

            aMonth.add(true);
        }

        if (sevendays.size() > 5) {
            int trueCount = (int) sevendays.stream()
                .filter(b -> b)
                .count();

            if (trueCount >= 5) {
                monster.setFeel(Feel.HAPPY);
            } else if (trueCount >= 3) {
                monster.setFeel(Feel.NOMAL);
            } else {
                monster.setFeel(Feel.SAD);
            }
        }

        child.setSevenDays(sevendays);
        child.setAMonth(aMonth);

        if(checkOne != child.getTodayCheck()) {
            child.setConsecutiveceAttempt(child.getConsecutiveAttempt() + 1);
        }

        if(child.getMonster().getExp() > 4) {
            registerMonster(child);
        }

        return MonsterResponseDto.builder()
                .name(String.valueOf(monster.getName()))
                .status(String.valueOf(monster.getStatus()))
                .feel(monster.getFeel().getTitle())
                .benefit(String.valueOf(monster.getBenefit()))
                .exp(monster.getExp())
                .build();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Scheduled(cron = "0 0 0 * * ?")
//    @Scheduled(cron = "0 * * * * ?")
    public void attempt(){
        log.info("출석 실행 ");
        List<Child> children = childRepository.findAll();

        for (Child child : children) {
            if(!child.getTodayCheck()){
                child.setConsecutiveceAttemptAndTodayCheck(0);
            }
            child.setTodayCheck(false);
        }
    }


    // DELETE
    public void deleteById(Long id) {
        Child child = childRepository.findById(id).orElseThrow();
        Monster monster = child.getMonster();
        child.setCntMonsters(0);
        monsterRepository.delete(monster);
    }


    private String registerMonster(Child child) {
        Monster monster = child.getMonster();
        MonsterEncyclopedia monsterEncyclopedia = child.getMonsterEncyclopedia();

        String monsterNameKey = monster.getName().toString().toUpperCase();

        /**
         *         this.SOL = SOL;
         *         this.MOLI = MOLI;
         *         this.RINO = RINO;
         *         this.SHOO = SHOO;
         *         this.DOREMI = DOREMI;
         *         this.LULULALA = LULULALA;
         *         this.PLI = PLI;
         *         this.LAY = LAY;
         */

        if(monsterNameKey.equals("SOL")){
            EncyclopediaDetail encyclopediaDetail = monsterEncyclopedia.getEncyclopediaDetails().get(0);
            Boolean isRegister = encyclopediaDetail.getIsRegister();
            if(isRegister) {
                return "이미 완료";
            }
            isRegister = true;
            encyclopediaDetail.setIsRegister(isRegister);
            encyclopediaDetailRepository.save(encyclopediaDetail);

        }

        if(monsterNameKey.equals("MOLI")){
            EncyclopediaDetail encyclopediaDetail = monsterEncyclopedia.getEncyclopediaDetails().get(1);
            Boolean isRegister = encyclopediaDetail.getIsRegister();
            if(isRegister) {
                return "이미 완료";
            }
            isRegister = true;
            encyclopediaDetail.setIsRegister(isRegister);
            encyclopediaDetailRepository.save(encyclopediaDetail);

        }

        if(monsterNameKey.equals("RINO")){
            EncyclopediaDetail encyclopediaDetail = monsterEncyclopedia.getEncyclopediaDetails().get(2);
            Boolean isRegister = encyclopediaDetail.getIsRegister();
            if(isRegister) {
                return "이미 완료";
            }
            isRegister = true;
            encyclopediaDetail.setIsRegister(isRegister);
            encyclopediaDetail.setRegisterTime(LocalDate.now());

            encyclopediaDetailRepository.save(encyclopediaDetail);
        }

        if(monsterNameKey.equals("SHOO")){
            EncyclopediaDetail encyclopediaDetail = monsterEncyclopedia.getEncyclopediaDetails().get(3);
            Boolean isRegister = encyclopediaDetail.getIsRegister();
            if(isRegister) {
                return "이미 완료";
            }
            isRegister = true;
            encyclopediaDetail.setIsRegister(isRegister);
            encyclopediaDetail.setRegisterTime(LocalDate.now());
            encyclopediaDetailRepository.save(encyclopediaDetail);
        }
        if(monsterNameKey.equals("DOREMI")){
            EncyclopediaDetail encyclopediaDetail = monsterEncyclopedia.getEncyclopediaDetails().get(4);
            Boolean isRegister = encyclopediaDetail.getIsRegister();
            if(isRegister) {
                return "이미 완료";
            }
            isRegister = true;
            encyclopediaDetail.setIsRegister(isRegister);
            encyclopediaDetail.setRegisterTime(LocalDate.now());
            encyclopediaDetailRepository.save(encyclopediaDetail);
        }

        if(monsterNameKey.equals("LULULALA")){
            EncyclopediaDetail encyclopediaDetail = monsterEncyclopedia.getEncyclopediaDetails().get(5);
            Boolean isRegister = encyclopediaDetail.getIsRegister();
            if(isRegister) {
                return "이미 완료";
            }
            isRegister = true;
            encyclopediaDetail.setIsRegister(isRegister);
            encyclopediaDetail.setRegisterTime(LocalDate.now());
            encyclopediaDetailRepository.save(encyclopediaDetail);
        }

        if(monsterNameKey.equals("PLI")){
            EncyclopediaDetail encyclopediaDetail = monsterEncyclopedia.getEncyclopediaDetails().get(6);
            Boolean isRegister = encyclopediaDetail.getIsRegister();
            if(isRegister == true) {
                return "이미 완료";
            }
            isRegister = true;
            encyclopediaDetail.setIsRegister(isRegister);
            encyclopediaDetail.setRegisterTime(LocalDate.now());
            encyclopediaDetailRepository.save(encyclopediaDetail);
        }

        if(monsterNameKey.equals("LAY")){
            EncyclopediaDetail encyclopediaDetail = monsterEncyclopedia.getEncyclopediaDetails().get(7);
            Boolean isRegister = encyclopediaDetail.getIsRegister();
            if(isRegister == true) {
                return "이미 완료";
            }
            isRegister = true;
            encyclopediaDetail.setIsRegister(isRegister);
            encyclopediaDetail.setRegisterTime(LocalDate.now());
            encyclopediaDetailRepository.save(encyclopediaDetail);
        }



        return "도감 등록 성공";
    }

    public MonsterDetail monsterDetail (Long id){
        Child child = childRepository.findById(id).orElseThrow();
        Monster monster = child.getMonster();

        List<History> histories = monster.getHistories();

        return MonsterDetail.builder()
                .exp(monster.getExp())
                .status(monster.getStatus())
                .feel(monster.getFeel())
                .historyList(histories)
                .build();
    }

    private int allMonth(int year, int month) {
        HashMap<Integer, List<Integer>> daysInMonth = new HashMap<>();

        daysInMonth.put(1, Arrays.asList(31));
        daysInMonth.put(2, Arrays.asList(28, 29));
        daysInMonth.put(3, Arrays.asList(31));
        daysInMonth.put(4, Arrays.asList(30));
        daysInMonth.put(5, Arrays.asList(31));
        daysInMonth.put(6, Arrays.asList(30));
        daysInMonth.put(7, Arrays.asList(31));
        daysInMonth.put(8, Arrays.asList(31));
        daysInMonth.put(9, Arrays.asList(30));
        daysInMonth.put(10, Arrays.asList(31));
        daysInMonth.put(11, Arrays.asList(30));
        daysInMonth.put(12, Arrays.asList(31));

        boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        List<Integer> days = daysInMonth.get(month);

        if (month == 2 && isLeapYear) {
            return days.get(1);
        }
        return days.get(0);
    }
}
