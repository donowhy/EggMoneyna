package shinhan.EggMoneyna.monster.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import shinhan.EggMoneyna.monster.dto.MonsterResponseDto;
import shinhan.EggMoneyna.monster.dto.MonsterSaveRequestDto;
import shinhan.EggMoneyna.monster.dto.MonsterSaveResponseDto;
import shinhan.EggMoneyna.monster.entity.Monster;
import shinhan.EggMoneyna.monster.entity.MonsterEncyclopedia;
import shinhan.EggMoneyna.monster.entity.enumType.Feel;
import shinhan.EggMoneyna.monster.entity.enumType.MonsterStatus;
import shinhan.EggMoneyna.monster.repository.MonsterEncyclopediaRepository;
import shinhan.EggMoneyna.monster.repository.MonsterRepository;
import shinhan.EggMoneyna.user.child.entity.Child;
import shinhan.EggMoneyna.user.child.repository.ChildRepository;


import javax.persistence.EntityNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

    // CREATE
    public MonsterSaveResponseDto save(MonsterSaveRequestDto monsterSaveRequestDto, Long id) {

        Child child = childRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        log.info("name={}", Monster.getRandomMong());

        if (child.getCntMonsters() != 0) {
            throw new RuntimeException();
        }
        Monster monster = Monster.builder()
                .name(Monster.getRandomMong())
                .status(MonsterStatus.Egg)
                .benefit(monsterSaveRequestDto.getBenefitEnum())
                .child(child)
                .feel(Feel.NOTHING)
                .build();

        Monster savedMonster = monsterRepository.save(monster);

        child.setCntMonsters(child.getCntMonsters() + 1);

        return MonsterSaveResponseDto.of(savedMonster);
    }

    // READ
    public MonsterResponseDto findById(Long id) {
        Child child = childRepository.findById(id).orElseThrow();
        Monster monster = child.getMonster();

        List<Boolean> sevendays = child.getSevendays();

        if (!child.getTodayCheck()) {
            child.setTodayLogin(true);
            if (sevendays.size() == 7) {
                sevendays.remove(0);
                sevendays.add(true);
            }
        }

        int trueCount = (int) sevendays.stream()
                .filter(b -> b)
                .count();

        if (trueCount >= 5) {
            monster.setFeel(Feel.HAPPY);
        } else if (trueCount >= 3) {
            monster.setFeel(Feel.NOTHING);
        } else {
            monster.setFeel(Feel.SAD);
        }

        child.setSevenDays(sevendays);
        child.setConsecutiveceAttempt(child.getConsecutiveAttempt() + 1);
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
    public void attempt(){
        List<Child> children = childRepository.findAll();

        for (Child child : children) {
            if(child.getTodayCheck()){
                child.setConsecutiveceAttemptAndTodayCheck(child.getConsecutiveAttempt());
            }else{
                child.setConsecutiveceAttemptAndTodayCheck(0);
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Scheduled(cron = "0 0 0 1 * ?")
    public void upgradeExp(){
        List<Child> children = childRepository.findAll();

        for (Child child : children) {

            List<Boolean> month = child.getMonth();
            LocalDate date = LocalDate.now();
            int days = allMonth(date.getYear(), date.getMonthValue());

            if(month.size() > days - 5){
                child.getMonster().setMonsterExp(child.getMonster().getExp() + 500);
            }

            if(child.getMonster().getExp() == 1000){
                registerMonster(child.getMonster(), child.getMonsterEncyclopedia());
                child.setCntMonsters(child.getCntMonsters()-1);
            }

            // Month 리스트를 초기화합니다.
            month.clear();
            child.setMonth(month);

            // 변경 사항을 저장합니다.
            childRepository.save(child);

        }
    }


    // DELETE
    public void deleteById(Long id) {
        Child child = childRepository.findById(id).orElseThrow();
        Monster monster = child.getMonster();
        child.setCntMonsters(0);
        monsterRepository.delete(monster);
    }


    private String registerMonster(Monster monster, MonsterEncyclopedia monsterEncyclopedia) {
        try {
            String monsterNameKey = monster.getName().toString().toUpperCase();
            String methodName = "set" + monsterNameKey;

            Method method = MonsterEncyclopedia.class.getMethod(methodName, boolean.class);
            Method getMethod = MonsterEncyclopedia.class.getMethod("get" + monsterNameKey);

            Boolean currentStatus = (Boolean) getMethod.invoke(monsterEncyclopedia);
            if (currentStatus == null || !currentStatus) {
                method.invoke(monsterEncyclopedia, true);
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Monster registration failed", e);
        }

        return "도감 등록 성공";
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
