package shinhan.EggMoneyna.monster.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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
import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        Monster monster = Monster.builder()
                .name(Monster.getRandomMong())
                .status(MonsterStatus.Egg)
                .benefit(monsterSaveRequestDto.getBenefitEnum())
                .child(child)
                .feel(Feel.Noting)
                .build();

        Monster savedMonster = monsterRepository.save(monster);

        child.setCntMonsters(child.getCntMonsters() + 1);

        return MonsterSaveResponseDto.of(savedMonster);
    }

    // READ
    public MonsterResponseDto findById(Long id) {
        Child child = childRepository.findById(id).orElseThrow();
        Monster monster = child.getMonster();

        return MonsterResponseDto.builder()
            .name(String.valueOf(monster.getName()))
            .status(String.valueOf(monster.getStatus()))
            .benefit(String.valueOf(monster.getBenefit()))
            .exp(monster.getExp())
            .point(monster.getPoint())
            .build();
    }


    // DELETE
    public void deleteById(Long id) {
        monsterRepository.deleteById(id);
    }

    private void registration(Long id, String name) {
        Child child = childRepository.findById(id).orElseThrow();

        Monster monster = child.getMonster();
        if(monster.getExp() == 1000) {
            registerMonster(monster, child.getMonsterEncyclopedia());
        }
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

}
